package com.example.figma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.figma.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StoreKeeperHome extends AppCompatActivity {
    private String storeKeeperId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_keeper_home);
        storeKeeperId = getIntent().getStringExtra("storeKeeperId");
        Toast.makeText(StoreKeeperHome.this, "storeKeeper ID: " + storeKeeperId, Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.job) {
                    startActivity(new Intent(StoreKeeperHome.this, Jobs.class));
                } else if (item.getItemId() == R.id.settings) {
                    // Pass the managerId to the Settings activity
                    Intent intent = new Intent(StoreKeeperHome.this,settingsStoreKeeper.class);
                    intent.putExtra("storeKeeperId", storeKeeperId);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    public void requestedAssets(View view) {
        Intent intent = new Intent(this, RequestedAssets.class);
        startActivity(intent);
    }

    public void assetsHistory(View view) {
        Intent intent = new Intent(this, AssetsHistory.class);
        startActivity(intent);
    }
}
