package com.example.figma;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoreKeeperLogin extends AppCompatActivity {

    EditText editTextId, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_keeper_login);

        editTextId = findViewById(R.id.managerid);
        editTextPassword = findViewById(R.id.managerPassword);
    }

    public void onLoginButtonClick(View view) {
        String id = editTextId.getText().toString();
        String password = editTextPassword.getText().toString();

        // Fetch credentials from the server and compare them with user-entered credentials
        new FetchCredentialsTask().execute(id, password);
    }

    private class FetchCredentialsTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String id = params[0];
            String password = params[1];
            String urlString = "https://house546.000webhostapp.com/fetchStoreKeeperCredentials.php";

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
                String response = stringBuilder.toString();

                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String serverId = jsonArray.getJSONObject(i).getString("bio_id");
                    String serverPassword = jsonArray.getJSONObject(i).getString("password");
                    if (id.equals(serverId) && password.equals(serverPassword)) {
                        return true;
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                // Credentials are correct, navigate to StoreKeeperHome activity
                Intent intent = new Intent(StoreKeeperLogin.this, StoreKeeperHome.class);
                intent.putExtra("storeKeeperId", editTextId.getText().toString()); // Pass the storekeeper ID to StoreKeeperHome activity
                startActivity(intent);
                finish(); // Finish the current activity to prevent user from going back
            } else {
                // Credentials are incorrect, show a toast message
                Toast.makeText(StoreKeeperLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
