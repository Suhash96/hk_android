package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ViewRatingsSupervisors extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ratings_supervisors);

        listView = findViewById(R.id.listView);

        // Execute AsyncTask to fetch data from the server
        new FetchSupervisorRatingsTask().execute();
    }

    // AsyncTask to fetch supervisor ratings from the server
    private class FetchSupervisorRatingsTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> supervisorRatings = new ArrayList<>();

            try {
                // Define the URL for fetching data
                URL url = new URL("https://house546.000webhostapp.com/fetchSupervisorRatings.php");

                // Establish connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Get the response from the server
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String jsonString = stringBuilder.toString();

                // Parse JSON response
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray ratingsArray = jsonObject.getJSONArray("ratings");

                // Extract supervisor names and ratings
                for (int i = 0; i < ratingsArray.length(); i++) {
                    JSONObject ratingObject = ratingsArray.getJSONObject(i);
                    String supervisorName = ratingObject.getString("supervisor_name");
                    int rating = ratingObject.getInt("rating");
                    String ratingStars = getRatingStars(rating);
                    supervisorRatings.add(supervisorName + ": " + ratingStars);
                }

                // Close connections
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return supervisorRatings;
        }

        @Override
        protected void onPostExecute(ArrayList<String> supervisorRatings) {
            // Update UI with supervisor ratings
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewRatingsSupervisors.this, android.R.layout.simple_list_item_1, supervisorRatings);
            listView.setAdapter(adapter);
        }
    }

    // Method to convert rating to star representation
    private String getRatingStars(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append(" ★"); // Unicode character for a star
        }
        return stars.toString();
    }
}
