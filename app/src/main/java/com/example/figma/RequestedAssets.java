package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.figma.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestedAssets extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_assets);

        listView = findViewById(R.id.listView);

        // Execute AsyncTask to fetch and populate data
        new FetchDataTask().execute("https://house546.000webhostapp.com/fetchRequestedItems.php");
    }

    private class FetchDataTask extends AsyncTask<String, Void, HashMap<String, ArrayList<String>>> {

        @Override
        protected HashMap<String, ArrayList<String>> doInBackground(String... strings) {
            HashMap<String, ArrayList<String>> supervisorItemsMap = new HashMap<>();

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String supervisorId = jsonObject.getString("supervisorId");
                    String item = jsonObject.getString("item");
                    String count = jsonObject.getString("count");
                    String requestAcceptance = jsonObject.getString("request_acceptance");

                    String formattedData = "Item: " + item + " Count: " + count;

                    // Update the formattedData if request_acceptance is "Accepted"
                    if (requestAcceptance.equals("Accepted")) {
                        formattedData += " Completed";
                    }

                    if (!supervisorItemsMap.containsKey(supervisorId)) {
                        ArrayList<String> itemList = new ArrayList<>();
                        itemList.add(formattedData);
                        supervisorItemsMap.put(supervisorId, itemList);
                    } else {
                        ArrayList<String> itemList = supervisorItemsMap.get(supervisorId);
                        itemList.add(formattedData);
                    }
                }

                connection.disconnect();
            } catch (IOException | JSONException e) {
                Log.e("FetchDataTask", "Error fetching data: " + e.getMessage());
            }

            return supervisorItemsMap;
        }

        @Override
        protected void onPostExecute(HashMap<String, ArrayList<String>> supervisorItemsMap) {
            ArrayList<Map.Entry<String, ArrayList<String>>> entries = new ArrayList<>(supervisorItemsMap.entrySet());
            CustomAdapter adapter = new CustomAdapter(RequestedAssets.this, entries);
            listView.setAdapter(adapter);
        }
    }
}
