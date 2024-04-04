package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    startActivity(new Intent(AdminHome.this, AssignWorkDates.class));
                } else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(AdminHome.this, settings.class));
                }
                return true;
            }
        });
    }
    public void Employee(View view) {
        Intent intent = new Intent(this, ManageEmployee.class);
        startActivity(intent);
    }
    public void room(View view) {
        Intent intent = new Intent(this, DetailsRooms.class);
        startActivity(intent);
    }
}