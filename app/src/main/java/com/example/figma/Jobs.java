package com.example.figma;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Jobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        // Retrieve supervisor ID from intent extras
        String supervisorId = getIntent().getStringExtra("supervisor_id");

        // Toast the supervisor ID
        Toast.makeText(this, "Supervisor ID: " + supervisorId, Toast.LENGTH_SHORT).show();

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Create adapter with supervisor ID
        JobsPagerAdapter adapter = new JobsPagerAdapter(getSupportFragmentManager(), supervisorId);
        viewPager.setAdapter(adapter);

        // Connect the tab layout with the view pager.
        tabLayout.setupWithViewPager(viewPager);
    }
}
