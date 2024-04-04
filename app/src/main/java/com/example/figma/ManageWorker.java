package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManageWorker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    startActivity(new Intent(ManageWorker.this, Jobs.class));
                }
                else if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(ManageWorker.this, AdminHome.class));
                }
                else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(ManageWorker.this, settings.class));
                }
                return true;
            }
        });

    }
    public void AddWorker(View view) {
        Intent intent = new Intent(this, DetailsWorker.class);
        startActivity(intent);
    }
    public void RemoveWorker(View view) {
        Intent intent = new Intent(this, RemoveWorker.class);
        startActivity(intent);
    }

}