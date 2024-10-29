package com.joyp.healthandfitnessapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {

    private TextView progressTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        // Initialize UI elements
        progressTextView = findViewById(R.id.progress_text_view);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Load user progress data
        loadProgressData();
    }

    // Method to load and display progress data
    private void loadProgressData() {
        Cursor cursor = dbHelper.getProfile();
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            double weight = cursor.getDouble(cursor.getColumnIndexOrThrow("weight"));
            double height = cursor.getDouble(cursor.getColumnIndexOrThrow("height"));

            // Display progress in TextView
            String progressText = "Name: " + name + "\n" +
                    "Age: " + age + "\n" +
                    "Weight: " + weight + " kg\n" +
                    "Height: " + height + " cm";
            progressTextView.setText(progressText);
        } else {
            progressTextView.setText("No data found.");
        }
        cursor.close();
    }
}
