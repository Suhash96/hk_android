package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SupervisorJobId extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_job_id);

        // Retrieve the job_id passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String jobId = extras.getString("job_id");
            // Find the TextView in the layout
            TextView jobIdTextView = findViewById(R.id.jobIdTextView);
            // Set the job_id value to the TextView
            jobIdTextView.setText(jobId);
        }
    }
}
