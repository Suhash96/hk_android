package com.example.figma;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AssignWorkDates extends AppCompatActivity {

    EditText fromDateEditText, toDateEditText;
    Button submitButton;
    Spinner supervisorsSpinner;
    Calendar fromDateCalendar, toDateCalendar;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_work_dates);

        fromDateEditText = findViewById(R.id.fromDateEditText);
        toDateEditText = findViewById(R.id.toDateEditText);
        submitButton = findViewById(R.id.submitButton);
        supervisorsSpinner = findViewById(R.id.supervisorsSpinner);
        fromDateCalendar = Calendar.getInstance();
        toDateCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        fromDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fromDateEditText, fromDateCalendar);
            }
        });

        toDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(toDateEditText, toDateCalendar);
            }
        });

        // Fetch assigned supervisors from the server
        new FetchAssignedSupervisorsTask().execute("https://house546.000webhostapp.com/fetchAssignWorkSupervisors.php");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromDate = dateFormat.format(fromDateCalendar.getTime());
                String toDate = dateFormat.format(toDateCalendar.getTime());
                String selectedSupervisor = supervisorsSpinner.getSelectedItem().toString();

                // Perform submission
                new SubmitAssignmentTask().execute(fromDate, toDate, selectedSupervisor);
            }
        });
    }

    private void showDatePickerDialog(final EditText dateEditText, final Calendar calendar) {
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateEditText.setText(dateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(AssignWorkDates.this, dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private class FetchAssignedSupervisorsTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... urls) {
            ArrayList<String> supervisors = new ArrayList<>();
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    supervisors.add(jsonArray.getString(i));
                }
                return supervisors;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> supervisors) {
            if (supervisors != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AssignWorkDates.this, android.R.layout.simple_spinner_item, supervisors);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                supervisorsSpinner.setAdapter(adapter);
            } else {
                Toast.makeText(AssignWorkDates.this, "Failed to fetch supervisors", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class SubmitAssignmentTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String fromDate = params[0];
            String toDate = params[1];
            String supervisor = params[2];

            HttpURLConnection urlConnection = null;
            try {
                // Construct URL with parameters
                URL url = new URL("https://house546.000webhostapp.com/insertDates.php?" +
                        "from_date=" + fromDate + "&to_date=" + toDate + "&supervisor=" + supervisor);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                // Get response from server
                int responseCode = urlConnection.getResponseCode();
                return responseCode == HttpURLConnection.HTTP_OK;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(AssignWorkDates.this, "Assignment submitted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AssignWorkDates.this, "Failed to submit assignment", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
