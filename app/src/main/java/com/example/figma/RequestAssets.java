package com.example.figma;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RequestAssets extends AppCompatActivity {
    private String supervisorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_assets);
        supervisorId = getIntent().getStringExtra("supervisor_id");

        ListView listView = findViewById(R.id.listView);
        ArrayList<String> assets = new ArrayList<>();
        assets.add("Mop");
        assets.add("Bucket");
        assets.add("Scrub Brush");
        assets.add("Broom");
        assets.add("Toilet Brush");
        assets.add("Dust Pan");
        assets.add("Gloves");
        assets.add("Acid");
        assets.add("Phenol");
        assets.add("Vacuum");

        final AssetAdapter adapter = new AssetAdapter(this, assets);
        listView.setAdapter(adapter);

        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iterate through the assets to insert/update each item
                for (int i = 0; i < adapter.getCount(); i++) {
                    String asset = assets.get(i);
                    int count = adapter.getCountForItem(asset);
                    // Check if count is greater than 0 before sending to database
                    if (count > 0) {
                        insertOrUpdateItem(asset, count);
                    }
                }
            }
        });
    }

    private void insertOrUpdateItem(String asset, int count) {
        // Construct the URL with parameters
        String url = "https://house546.000webhostapp.com/items.php" +
                "?supervisorId=" + supervisorId +
                "&item=" + asset +
                "&count=" + count;

        // Perform the GET request
        new InsertDataAsyncTask().execute(url);
    }

    private class InsertDataAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                String urlString = params[0];
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                // Read the response if required
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                // Convert the InputStream into a string if required

                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Show toast message after the request has been sent
            Toast.makeText(RequestAssets.this, "Assets Requested is sent", Toast.LENGTH_SHORT).show();
        }
    }
}
