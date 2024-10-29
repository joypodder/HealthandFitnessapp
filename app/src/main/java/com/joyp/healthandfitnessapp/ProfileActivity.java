package com.joyp.healthandfitnessapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText nameInput, ageInput, weightInput, heightInput;
    private Button saveButton, viewProgressButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize UI elements
        nameInput = findViewById(R.id.name_input);
        ageInput = findViewById(R.id.age_input);
        weightInput = findViewById(R.id.weight_input);
        heightInput = findViewById(R.id.height_input);
        saveButton = findViewById(R.id.save_button);
        viewProgressButton = findViewById(R.id.view_progress_button);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Load existing profile data if available
        loadProfileData();

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
            }
        });

        // View progress button listener (for future progress tracking)
        viewProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProgressActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to load existing profile data
    private void loadProfileData() {
        Cursor cursor = dbHelper.getProfile();
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            double weight = cursor.getDouble(cursor.getColumnIndexOrThrow("weight"));
            double height = cursor.getDouble(cursor.getColumnIndexOrThrow("height"));

            nameInput.setText(name);
            ageInput.setText(String.valueOf(age));
            weightInput.setText(String.valueOf(weight));
            heightInput.setText(String.valueOf(height));
        }
        cursor.close();
    }

    // Method to save profile data
    private void saveProfileData() {
        String name = nameInput.getText().toString();
        int age = Integer.parseInt(ageInput.getText().toString());
        double weight = Double.parseDouble(weightInput.getText().toString());
        double height = Double.parseDouble(heightInput.getText().toString());

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.insertProfile(name, age, weight, height)) {
            Toast.makeText(this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error saving profile.", Toast.LENGTH_SHORT).show();
        }
    }
}
