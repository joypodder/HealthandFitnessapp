package com.joyp.healthandfitnessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Constants for SharedPreferences
    private static final String PREFS_NAME = "HealthAppPrefs";
    private static final String IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check login status using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN, false);

        if (isLoggedIn) {
            // If user is logged in, redirect to HomeActivity
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity
        } else {
            // If user is not logged in, redirect to LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity
        }
    }
}
