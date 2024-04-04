package com.example.figma;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class CustomAdapter extends ArrayAdapter<Map.Entry<String, ArrayList<String>>> {

    private Context mContext;
    private ArrayList<Map.Entry<String, ArrayList<String>>> mEntries;

    public CustomAdapter(Context context, ArrayList<Map.Entry<String, ArrayList<String>>> entries) {
        super(context, 0, entries);
        mContext = context;
        mEntries = entries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_list_item, parent, false);
        }

        Map.Entry<String, ArrayList<String>> entry = mEntries.get(position);

        TextView supervisorIdTextView = listItem.findViewById(R.id.supervisorIdTextView);
        supervisorIdTextView.setText("Supervisor ID: " + entry.getKey());

        TextView itemsTextView = listItem.findViewById(R.id.itemsTextView);
        ArrayList<String> items = entry.getValue();
        StringBuilder itemsText = new StringBuilder();
        for (String item : items) {
            itemsText.append(item).append("\n");
        }
        itemsTextView.setText(itemsText.toString());

        Button acceptButton = listItem.findViewById(R.id.acceptButton);
        TextView completedTextView = listItem.findViewById(R.id.completedTextView);

        if (isAccepted(entry.getKey())) {
            acceptButton.setVisibility(View.GONE);
            completedTextView.setVisibility(View.VISIBLE);
            Log.d("CustomAdapter", "Showing completedTextView for supervisor ID: " + entry.getKey());
        } else {
            acceptButton.setVisibility(View.VISIBLE);
            completedTextView.setVisibility(View.GONE);
            Log.d("CustomAdapter", "Hiding completedTextView for supervisor ID: " + entry.getKey());

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the supervisorId associated with the current item
                    String supervisorId = entry.getKey();

                    // Send a GET request to the server to update request_acceptance
                    String url = "https://house546.000webhostapp.com/accept.php?supervisorId=" + supervisorId + "&action=accept";
                    new UpdateAcceptanceTask(acceptButton, completedTextView).execute(url);
                }
            });
        }

        return listItem;
    }

    private boolean isAccepted(String supervisorId) {
        // Implement logic to check if request_acceptance is "Accepted" for the given supervisorId
        for (Map.Entry<String, ArrayList<String>> entry : mEntries) {
            if (entry.getKey().equals(supervisorId)) {
                ArrayList<String> values = entry.getValue();
                for (String value : values) {
                    if (value.contains("Accepted")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private class UpdateAcceptanceTask extends AsyncTask<String, Void, Boolean> {

        private Button acceptButton;
        private TextView completedTextView;

        public UpdateAcceptanceTask(Button acceptButton, TextView completedTextView) {
            this.acceptButton = acceptButton;
            this.completedTextView = completedTextView;
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                // Send a GET request to the server
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                // Handle response if needed
                int responseCode = urlConnection.getResponseCode();
                return responseCode == HttpURLConnection.HTTP_OK;

            } catch (IOException e) {
                Log.e("UpdateAcceptanceTask", "Error updating acceptance: " + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(mContext, "Acceptance updated successfully", Toast.LENGTH_SHORT).show();
                acceptButton.setVisibility(View.GONE); // Hide the accept button upon successful update
                completedTextView.setVisibility(View.VISIBLE); // Show the completedTextView
            } else {
                Toast.makeText(mContext, "Failed to update acceptance", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
