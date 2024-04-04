package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Retrieve manager ID from intent extras
        String managerId = getIntent().getStringExtra("managerId");

        // Display manager ID in a toast message
      //  Toast.makeText(settings.this, "Manager ID: " + managerId, Toast.LENGTH_SHORT).show();

    }
    public void profile(View view) {
        Intent intent = new Intent(this, ManagerProfile.class);
        intent.putExtra("managerId", getIntent().getStringExtra("managerId"));
        startActivity(intent);
    }
    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}