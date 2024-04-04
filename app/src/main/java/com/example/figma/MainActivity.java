package com.example.figma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openAdminLogin(View view) {
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
    public void openManagerLogin(View view) {
        Intent intent = new Intent(this, ManagerLogin.class);
        startActivity(intent);
    }

    public void openSupervisorLogin(View view) {
        Intent intent = new Intent(this, SupervisorLogin.class);
        startActivity(intent);
    }
    public void openStoreKeeperLogin(View view) {
        Intent intent = new Intent(this, StoreKeeperLogin.class);
        startActivity(intent);
    }

}