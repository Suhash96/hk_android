package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DetailsRooms extends AppCompatActivity {
    EditText buildingEditText, floorEditText;
    Spinner roomTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_rooms);

        // Initialize EditText fields
        buildingEditText = findViewById(R.id.building);
        floorEditText = findViewById(R.id.floor);

        // Initialize Spinner
        roomTypeSpinner = findViewById(R.id.roomtype_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.room_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomTypeSpinner.setAdapter(adapter);

        // Example of how to send data when a button is clicked (assuming you have a button with the id add_button)
        findViewById(R.id.addButton).setOnClickListener(v -> sendDataToServer());
    }

    private void sendDataToServer() {
        // Get the values from EditText fields
        String building = buildingEditText.getText().toString();
        String floor = floorEditText.getText().toString();
        String roomType = roomTypeSpinner.getSelectedItem().toString();

        // Send room data to the server
        new SendRoomDataToServer(building, floor, roomType).execute();
    }

    private class SendRoomDataToServer extends AsyncTask<Void, Void, Void> {
        private String building;
        private String floor;
        private String roomType;
        private String errorMessage;

        public SendRoomDataToServer(String building, String floor, String roomType) {
            this.building = building;
            this.floor = floor;
            this.roomType = roomType;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Encode fields for URL
                String encodedBuilding = URLEncoder.encode(building, "UTF-8");
                String encodedFloor = URLEncoder.encode(floor, "UTF-8");
                String encodedRoomType = URLEncoder.encode(roomType, "UTF-8");

                // Create the URL with parameters
                String urlString = "https://house546.000webhostapp.com/Rooms.php" +
                        "?building=" + encodedBuilding +
                        "&floor=" + encodedFloor +
                        "&room_type=" + encodedRoomType;
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Set up the connection properties
                urlConnection.setRequestMethod("GET");

                // Check the server response code
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Successful response
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DetailsRooms.this, "Data sent successfully", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    // Handle other response codes
                    errorMessage = "Server returned non-OK response: " + responseCode;
                    Log.e("SendRoomDataToServer", errorMessage);
                }

                urlConnection.disconnect();
            } catch (IOException e) {
                errorMessage = "Error sending room data to server";
                Log.e("SendRoomDataToServer", errorMessage, e);
            }

            return null;
        }
    }
}
