package com.example.figma;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
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
import java.util.ArrayList;

public class RateSupervisor extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Supervisor> supervisorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_supervisor);

        listView = findViewById(R.id.listView);
        Button submitButton = findViewById(R.id.submitButton);

        // If supervisor ratings were previously fetched and stored, use them
        if (savedInstanceState != null && savedInstanceState.containsKey("supervisorsList")) {
            supervisorsList = savedInstanceState.getParcelableArrayList("supervisorsList");
            // Update the ListView with stored ratings
            updateListView();
        } else {
            // Fetch the list of supervisors
            new FetchSupervisorsTask().execute();
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iterate through the list of supervisors
                for (Supervisor supervisor : supervisorsList) {
                    // Get supervisor name and rating
                    String supervisorName = supervisor.getName();
                    float rating = supervisor.getRating();
                    // Check if rating is greater than zero
                    if (rating > 0) {
                        // Insert into database
                        new InsertRatingTask().execute(supervisorName, String.valueOf(rating));
                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save supervisor ratings to handle configuration changes
        outState.putParcelableArrayList("supervisorsList", supervisorsList);
        super.onSaveInstanceState(outState);
    }

    private void updateListView() {
        SupervisorAdapter adapter = new SupervisorAdapter(RateSupervisor.this, supervisorsList);
        listView.setAdapter(adapter);
    }

    private class FetchSupervisorsTask extends AsyncTask<Void, Void, ArrayList<Supervisor>> {

        @Override
        protected ArrayList<Supervisor> doInBackground(Void... voids) {
            ArrayList<Supervisor> supervisors = new ArrayList<>();
            try {
                // URL of the PHP script to fetch supervisors
                String urlString = "https://house546.000webhostapp.com/AssignedSupervisors.php";
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Read the response from the server
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String response = stringBuilder.toString();

                // Parse the JSON response
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("supervisors");
                for (int i = 0; i < jsonArray.length(); i++) {
                    String name = jsonArray.getString(i);
                    supervisors.add(new Supervisor(name, 0.0f)); // Default rating is 0.0
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return supervisors;
        }

        @Override
        protected void onPostExecute(ArrayList<Supervisor> supervisors) {
            // Store the fetched supervisors
            supervisorsList.clear();
            supervisorsList.addAll(supervisors);
            // Update the ListView with the list of supervisors
            updateListView();
        }
    }

    private static class Supervisor implements Parcelable {
        private String name;
        private float rating;

        public Supervisor(String name, float rating) {
            this.name = name;
            this.rating = rating;
        }

        protected Supervisor(Parcel in) {
            name = in.readString();
            rating = in.readFloat();
        }

        public static final Creator<Supervisor> CREATOR = new Creator<Supervisor>() {
            @Override
            public Supervisor createFromParcel(Parcel in) {
                return new Supervisor(in);
            }

            @Override
            public Supervisor[] newArray(int size) {
                return new Supervisor[size];
            }
        };

        public String getName() {
            return name;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeFloat(rating);
        }
    }

    private static class SupervisorAdapter extends ArrayAdapter<Supervisor> {

        public SupervisorAdapter(Context context, ArrayList<Supervisor> supervisors) {
            super(context, 0, supervisors);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final Supervisor supervisor = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_supervisor, parent, false);
            }

            // Lookup view for data population
            TextView supervisorNameTextView = convertView.findViewById(R.id.supervisorNameTextView);
            RatingBar supervisorRatingBar = convertView.findViewById(R.id.supervisorRatingBar);

            // Populate the data into the template view using the data object
            supervisorNameTextView.setText(supervisor.getName());
            supervisorRatingBar.setRating(supervisor.getRating());

            // Update the supervisor's rating when the user interacts with the rating bar
            supervisorRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    // Update the supervisor's rating
                    supervisor.setRating(rating);
                }
            });

            // Return the completed view to render on screen
            return convertView;
        }
    }

    private class InsertRatingTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            // Extract supervisor name and rating from params
            String supervisorName = params[0];
            String rating = params[1];

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                // Construct the URL with parameters
                String urlString = "https://house546.000webhostapp.com/RateSupervisors.php" + "?supervisor_name=" + supervisorName + "&rating=" + rating;
                URL url = new URL(urlString);

                // Open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    // Nothing to do.
                    return false;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return false;
                }
                // Data sent successfully
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                // Error occurred while sending data
                return false;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            String toastMessage;
            if (success) {
                toastMessage = "Supervisor ratings submitted successfully";
            } else {
                toastMessage = "Failed to submit supervisor ratings";
            }

            // Log the toast message
            Log.d("Ratings", toastMessage);

            // Log the supervisor names and ratings
            SupervisorAdapter adapter = (SupervisorAdapter) listView.getAdapter();
            StringBuilder ratingsLog = new StringBuilder();
            for (int i = 0; i < adapter.getCount(); i++) {
                Supervisor supervisor = adapter.getItem(i);
                String supervisorName = supervisor.getName();
                float rating = supervisor.getRating();
                ratingsLog.append("Supervisor: ").append(supervisorName).append(", Rating: ").append(rating).append("\n");
            }
            Log.d("Ratings", "Supervisor ratings: \n" + ratingsLog.toString());

            // Display the toast message
            Toast.makeText(RateSupervisor.this, toastMessage, Toast.LENGTH_SHORT).show();
        }

    }
}
