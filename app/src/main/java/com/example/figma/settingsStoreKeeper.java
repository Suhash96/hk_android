package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class settingsStoreKeeper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_store_keeper);
        String supervisor_id = getIntent().getStringExtra("storeKeeperId");
        Toast.makeText(settingsStoreKeeper.this, "storeKeeperId: " + supervisor_id, Toast.LENGTH_SHORT).show();


    }
    public void profile(View view) {
        Intent intent = new Intent(this, StoreKeeperProfile.class);
        intent.putExtra("storeKeeperId", getIntent().getStringExtra("storeKeeperId"));
        startActivity(intent);
    }
    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}