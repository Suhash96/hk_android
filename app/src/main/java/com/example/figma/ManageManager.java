package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManageManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_manager);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    startActivity(new Intent(ManageManager.this, Jobs.class));
                }
                else if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(ManageManager.this, AdminHome.class));
                }
                else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(ManageManager.this, settings.class));
                }
                return true;
            }
        });

    }
    public void AddManager(View view) {
        Intent intent = new Intent(this, DetailsManager.class);
        startActivity(intent);
    }
    public void RemoveManager(View view) {
        Intent intent = new Intent(this, RemoveManager.class);
        startActivity(intent);
    }
}