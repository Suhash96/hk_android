package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoreKeeperProfile extends AppCompatActivity {

    // Declare TextViews
    private TextView bioIdTextView;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView experienceTextView;
    private TextView dojTextView;
    private TextView qualificationTextView;
    private TextView phoneNoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_keeper_profile);

        // Initialize TextViews
        bioIdTextView = findViewById(R.id.bioIdTextView);
        nameTextView = findViewById(R.id.nameTextView);
        ageTextView = findViewById(R.id.ageTextView);
        experienceTextView = findViewById(R.id.experienceTextView);
        dojTextView = findViewById(R.id.dojTextView);
        qualificationTextView = findViewById(R.id.qualificationTextView);
        phoneNoTextView = findViewById(R.id.phoneNoTextView);

        // Get managerId from the intent
        String storeKeeperId = getIntent().getStringExtra("storeKeeperId");

        // Make a request to the server to fetch data for the managerId
        new GetDataTask().execute(storeKeeperId);
    }

    private class GetDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String managerId = params[0];
            String result = "";
            try {
                URL url = new URL("https://house546.000webhostapp.com/fetchStoreKeeperProfile.php?storeKeeperId=" + managerId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                result = response.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Parse JSON response
            try {
                JSONObject jsonObject = new JSONObject(result);

                // Extract data from JSON object
                String bioId = jsonObject.getString("bio_id");
                String name = jsonObject.getString("name");
                int age = jsonObject.getInt("age");
                int experience = jsonObject.getInt("experience");
                String doj = jsonObject.getString("doj");
                String qualification = jsonObject.getString("qualification");
                String phoneNo = jsonObject.getString("phone_no");

                // Update TextViews with retrieved data
                bioIdTextView.setText(bioId);
                nameTextView.setText(name);
                ageTextView.setText(String.valueOf(age));
                experienceTextView.setText(String.valueOf(experience));
                dojTextView.setText(doj);
                qualificationTextView.setText(qualification);
                phoneNoTextView.setText(phoneNo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
