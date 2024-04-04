package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManagerHome extends AppCompatActivity {

    private String managerId; // Declaring managerId as a class variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

        // Retrieve manager ID from intent extras
        managerId = getIntent().getStringExtra("managerId");

        // Display manager ID in a toast message
        Toast.makeText(ManagerHome.this, "Manager ID: " + managerId, Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    startActivity(new Intent(ManagerHome.this, Jobs.class));
                }
                else if (item.getItemId() == R.id.settings) {
                    // Pass the managerId to the Settings activity
                    Intent intent = new Intent(ManagerHome.this, settings.class);
                    intent.putExtra("managerId", managerId);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    public void openAssignWork(View view) {
        Intent intent = new Intent(this, AssignWork.class);
        startActivity(intent);
    }

    public void ratings(View view) {
        Intent intent = new Intent(this, RateSupervisor.class);
        startActivity(intent);
    }

    public void viewRatings(View view) {
        Intent intent = new Intent(this, ViewRatingsSupervisors.class);
        startActivity(intent);
    }
}
