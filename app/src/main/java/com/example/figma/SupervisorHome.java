package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SupervisorHome extends AppCompatActivity {
    private String supervisor_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_home);
        // Retrieve manager ID from intent extras
        supervisor_id = getIntent().getStringExtra("supervisor_id");

        // Display manager ID in a toast message
        Toast.makeText(SupervisorHome.this, "supervisor ID: " + supervisor_id, Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    Intent intent = new Intent(SupervisorHome.this, Jobs.class);
                    intent.putExtra("supervisor_id", supervisor_id);
                    startActivity(intent);                }

                else if (item.getItemId() == R.id.settings) {
                    Intent intent = new Intent(SupervisorHome.this, settingsSupervisor.class);
                    intent.putExtra("supervisor_id", supervisor_id);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    public void openJob(View view) {
        Intent intent = new Intent(this, AssignWorkWorkers.class);
        startActivity(intent);
    }
    public void requestAssets(View view) {
        Intent intent = new Intent(this, RequestAssets.class);
        startActivity(intent);
    }
}