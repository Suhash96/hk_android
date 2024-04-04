package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class settingsSupervisor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_supervisor);
        String supervisor_id = getIntent().getStringExtra("supervisor_id");
        Toast.makeText(settingsSupervisor.this, "supervisor ID: " + supervisor_id, Toast.LENGTH_SHORT).show();

    }
    public void profile(View view) {
        Intent intent = new Intent(this, SupervisorProfile.class);
        intent.putExtra("supervisor_id", getIntent().getStringExtra("supervisor_id"));
        startActivity(intent);
    }
    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}