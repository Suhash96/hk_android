package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManageSupervisor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_supervisor);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    startActivity(new Intent(ManageSupervisor.this, Jobs.class));
                }
                else if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(ManageSupervisor.this, AdminHome.class));
                }
                else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(ManageSupervisor.this, settings.class));
                }
                return true;
            }
        });
    }
    public void AddSupervisor(View view) {
        Intent intent = new Intent(this, DetailsSupervisor.class);
        startActivity(intent);
    }
    public void RemoveSupervisor(View view) {
        Intent intent = new Intent(this, RemoveSupervisor.class);
        startActivity(intent);
    }
}