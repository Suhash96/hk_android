package com.example.figma;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpcomingJobsFragment extends Fragment {

    private TextView textViewJobId, textViewWeek, textViewBuilding, textViewFloor, textViewRoomType, textViewAssignedSupervisors, textViewFromDate, textViewToDate, textViewStatus;
    private String supervisorId;

    public UpcomingJobsFragment() {
        // Required empty public constructor
    }

    public static UpcomingJobsFragment newInstance(String supervisorId) {
        UpcomingJobsFragment fragment = new UpcomingJobsFragment();
        Bundle args = new Bundle();
        args.putString("supervisor_id", supervisorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            supervisorId = getArguments().getString("supervisor_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_jobs, container, false);

        textViewJobId = view.findViewById(R.id.textViewJobId);
        textViewWeek = view.findViewById(R.id.textViewWeek);
        textViewBuilding = view.findViewById(R.id.textViewBuilding);
        textViewFloor = view.findViewById(R.id.textViewFloor);
        textViewRoomType = view.findViewById(R.id.textViewRoomType);
        textViewAssignedSupervisors = view.findViewById(R.id.textViewAssignedSupervisors);
        textViewFromDate = view.findViewById(R.id.textViewFromDate);
        textViewToDate = view.findViewById(R.id.textViewToDate);
        textViewStatus = view.findViewById(R.id.textViewStatus);

        // Fetch data from server
        new FetchUpcomingJobsTask().execute();

        // Display toast message with supervisor ID
        Toast.makeText(getContext(), "Supervisor ID: " + supervisorId, Toast.LENGTH_SHORT).show();

        return view;
    }

    private class FetchUpcomingJobsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String urlString = "https://house546.000webhostapp.com/?bio_id=4321";

            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    // Assuming there is only one upcoming job
                    String jobId = jsonArray.getJSONObject(0).getString("job_id");
                    String week = jsonArray.getJSONObject(0).getString("week");
                    String building = jsonArray.getJSONObject(0).getString("building");
                    String floor = jsonArray.getJSONObject(0).getString("floor");
                    String roomType = jsonArray.getJSONObject(0).getString("room_type");
                    String assignedSupervisors = jsonArray.getJSONObject(0).getString("assigned_supervisors");
                    String fromDate = jsonArray.getJSONObject(0).getString("from_date");
                    String toDate = jsonArray.getJSONObject(0).getString("to_date");
                    String status = jsonArray.getJSONObject(0).getString("status");

                    // Update UI with fetched data
                    textViewJobId.setText(jobId);
                    textViewWeek.setText(week);
                    textViewBuilding.setText(building);
                    textViewFloor.setText(floor);
                    textViewRoomType.setText(roomType);
                    textViewAssignedSupervisors.setText(assignedSupervisors);
                    textViewFromDate.setText(fromDate);
                    textViewToDate.setText(toDate);
                    textViewStatus.setText(status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}