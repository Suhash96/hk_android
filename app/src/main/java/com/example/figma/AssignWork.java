package com.example.figma;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AssignWork extends AppCompatActivity {

    private Spinner buildingSpinner, floorSpinner, roomTypeSpinner;
    private Button submitButton;
    private ListView supervisorListView;
    private ArrayAdapter<String> supervisorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_work);

        buildingSpinner = findViewById(R.id.buildingSpinner);
        floorSpinner = findViewById(R.id.floorSpinner);
        roomTypeSpinner = findViewById(R.id.roomTypeSpinner);
        submitButton = findViewById(R.id.submitButton);
        supervisorListView = findViewById(R.id.supervisorListView);

        supervisorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, new ArrayList<>());
        supervisorListView.setAdapter(supervisorAdapter);
        supervisorListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit button click event
                String selectedBuilding = buildingSpinner.getSelectedItem().toString();
                String selectedFloor = floorSpinner.getSelectedItem().toString();
                String selectedRoomType = roomTypeSpinner.getSelectedItem().toString();

                // Get selected supervisors
                StringBuilder selectedSupervisors = new StringBuilder();
                SparseBooleanArray checked = supervisorListView.getCheckedItemPositions();
                for (int i = 0; i < supervisorListView.getCount(); i++) {
                    if (checked.get(i)) {
                        selectedSupervisors.append(supervisorAdapter.getItem(i)).append(", ");
                    }
                }

                if (selectedSupervisors.length() > 0) {
                    selectedSupervisors.setLength(selectedSupervisors.length() - 2); // Remove the trailing comma and space

                    // Send data to server
                    new InsertAssignWorkTask().execute("Week1", selectedBuilding, selectedFloor, selectedRoomType, selectedSupervisors.toString());
                } else {
                    Toast.makeText(AssignWork.this, "No supervisors selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Fetch data from the server
        new FetchDataTask().execute();
        // Fetch supervisor names from the server
        new FetchSupervisorNamesTask().execute();
    }

    private class FetchDataTask extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... voids) {
            JSONObject data = null;
            String urlString = "https://house546.000webhostapp.com/fetchData.php";
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    String response = stringBuilder.toString();
                    data = new JSONObject(response);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(JSONObject data) {
            if (data != null) {
                try {
                    JSONArray buildingsArray = data.getJSONArray("buildings");
                    JSONArray floorsArray = data.getJSONArray("floors");
                    JSONArray roomTypesArray = data.getJSONArray("roomTypes");

                    // Populate building spinner
                    ArrayList<String> buildingNames = new ArrayList<>();
                    for (int i = 0; i < buildingsArray.length(); i++) {
                        buildingNames.add(buildingsArray.getString(i));
                    }
                    ArrayAdapter<String> buildingAdapter = new ArrayAdapter<>(AssignWork.this, android.R.layout.simple_spinner_item, buildingNames);
                    buildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    buildingSpinner.setAdapter(buildingAdapter);

                    // Populate floor spinner
                    ArrayList<String> floorNumbers = new ArrayList<>();
                    for (int i = 0; i < floorsArray.length(); i++) {
                        floorNumbers.add(floorsArray.getString(i));
                    }
                    ArrayAdapter<String> floorAdapter = new ArrayAdapter<>(AssignWork.this, android.R.layout.simple_spinner_item, floorNumbers);
                    floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    floorSpinner.setAdapter(floorAdapter);

                    // Populate room type spinner
                    ArrayList<String> roomTypes = new ArrayList<>();
                    for (int i = 0; i < roomTypesArray.length(); i++) {
                        roomTypes.add(roomTypesArray.getString(i));
                    }
                    ArrayAdapter<String> roomTypeAdapter = new ArrayAdapter<>(AssignWork.this, android.R.layout.simple_spinner_item, roomTypes);
                    roomTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    roomTypeSpinner.setAdapter(roomTypeAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // Show error message if data fetching failed
                Toast.makeText(AssignWork.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class FetchSupervisorNamesTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://house546.000webhostapp.com/fetchSupervisor.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String name = jsonArray.getString(i);
                        supervisorAdapter.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class InsertAssignWorkTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String week = params[0];
                String selectedBuilding = params[1];
                String selectedFloor = params[2];
                String selectedRoomType = params[3];
                String selectedSupervisors = params[4];

                // Construct the URL for the insert operation
                String urlString = "https://house546.000webhostapp.com/AssignWork.php" +
                        "?week=" + URLEncoder.encode(week, "UTF-8") +
                        "&building=" + URLEncoder.encode(selectedBuilding, "UTF-8") +
                        "&floor=" + URLEncoder.encode(selectedFloor, "UTF-8") +
                        "&room_type=" + URLEncoder.encode(selectedRoomType, "UTF-8") +
                        "&assigned_supervisors=" + URLEncoder.encode(selectedSupervisors, "UTF-8");

                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Retrieve job_id from the response
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jobId) {
            if (jobId != null) {
                // Pass jobId to the next activity
                Intent intent = new Intent(AssignWork.this, SupervisorJobId.class);
                intent.putExtra("job_id", jobId);
                startActivity(intent);
            } else {
                Toast.makeText(AssignWork.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
