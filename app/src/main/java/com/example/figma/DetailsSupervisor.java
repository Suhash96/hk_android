package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DetailsSupervisor extends AppCompatActivity {
    EditText bioIdEditText, nameEditText, ageEditText, experienceEditText, dojEditText, qualificationEditText, phoneNoEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_supervisor);

        // Initialize EditText fields
        bioIdEditText = findViewById(R.id.bio_id);
        nameEditText = findViewById(R.id.name);
        ageEditText = findViewById(R.id.age);
        experienceEditText = findViewById(R.id.experience);
        dojEditText = findViewById(R.id.doj);
        qualificationEditText = findViewById(R.id.qualification);
        phoneNoEditText = findViewById(R.id.phone_no);
        passwordEditText = findViewById(R.id.password);

        // Example of how to send data when a button is clicked (assuming you have a button with the id send_button)
        findViewById(R.id.addButton).setOnClickListener(v -> sendDataToServer());
    }

    private void sendDataToServer() {
        // Get the values from EditText fields
        String bioId = bioIdEditText.getText().toString();
        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        int experience = Integer.parseInt(experienceEditText.getText().toString());
        String doj = dojEditText.getText().toString();
        String qualification = qualificationEditText.getText().toString();
        String phoneNo = phoneNoEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Send user data to the server
        new SendUserDataToServer(bioId, name, age, experience, doj, qualification, phoneNo, password).execute();
    }

    private class SendUserDataToServer extends AsyncTask<Void, Void, Void> {
        private String bioId;
        private String name;
        private int age;
        private int experience;
        private String doj;
        private String qualification;
        private String phoneNo;
        private String password;
        private String errorMessage;

        public SendUserDataToServer(String bioId, String name, int age, int experience, String doj, String qualification, String phoneNo, String password) {
            this.bioId = bioId;
            this.name = name;
            this.age = age;
            this.experience = experience;
            this.doj = doj;
            this.qualification = qualification;
            this.phoneNo = phoneNo;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Encode fields for URL
                String encodedBioId = URLEncoder.encode(bioId, "UTF-8");
                String encodedName = URLEncoder.encode(name, "UTF-8");
                String encodedDoj = URLEncoder.encode(doj, "UTF-8");
                String encodedQualification = URLEncoder.encode(qualification, "UTF-8");
                String encodedPhoneNo = URLEncoder.encode(phoneNo, "UTF-8");
                String encodedPassword = URLEncoder.encode(password, "UTF-8");

                // Create the URL with parameters
                String urlString = "https://house546.000webhostapp.com/DetailsSupervisor.php" +
                        "?bio_id=" + encodedBioId +
                        "&name=" + encodedName +
                        "&age=" + age +
                        "&experience=" + experience +
                        "&doj=" + encodedDoj +
                        "&qualification=" + encodedQualification +
                        "&phone_no=" + encodedPhoneNo +
                        "&password=" + encodedPassword;
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Set up the connection properties
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                // Check the server response code
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Successful response
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DetailsSupervisor.this, "Data sent successfully", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    // Handle other response codes
                    errorMessage = "Server returned non-OK response: " + responseCode;
                    Log.e("SendUserDataToServer", errorMessage);
                }
                urlConnection.disconnect();
            } catch (Exception e) {
                errorMessage = "Error sending user data to server";
                Log.e("SendUserDataToServer", errorMessage, e);
            }
            return null;
        }
    }
}
