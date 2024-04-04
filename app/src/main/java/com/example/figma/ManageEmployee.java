package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kotlinx.coroutines.Job;

public class ManageEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employee);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    startActivity(new Intent(ManageEmployee.this, Jobs.class));
                }
                else if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(ManageEmployee.this, AdminHome.class));
                }
                else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(ManageEmployee.this, settings.class));
                }
                return true;
            }
        });
    }

    public void supervisor(View view) {
        Intent intent = new Intent(this, ManageSupervisor.class);
        startActivity(intent);
    }
    public void worker(View view) {
        Intent intent = new Intent(this, ManageWorker.class);
        startActivity(intent);
    }
    public void manager(View view) {
        Intent intent = new Intent(this, ManageManager.class);
        startActivity(intent);
    }
}