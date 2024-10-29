package com.joyp.healthandfitnessapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AssessmentActivity extends AppCompatActivity {

    private EditText weightInput, heightInput;
    private Spinner assessmentIntervalSpinner;
    private Button saveAssessmentButton, profileButton;  // Add the profileButton

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HealthAppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        // Initialize UI elements
        weightInput = findViewById(R.id.weight_input);
        heightInput = findViewById(R.id.height_input);
        assessmentIntervalSpinner = findViewById(R.id.assessment_interval_spinner);
        saveAssessmentButton = findViewById(R.id.save_assessment_button);
        profileButton = findViewById(R.id.btn_profile);  // Initialize the profile button

        // Setup Spinner for intervals
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.assessment_intervals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assessmentIntervalSpinner.setAdapter(adapter);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Save button click listener
        saveAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAssessmentData();
            }
        });

        // Add a listener to profileButton to open ProfileActivity
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ProfileActivity when the profile button is clicked
                Intent intent = new Intent(AssessmentActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to save user input
    private void saveAssessmentData() {
        String weight = weightInput.getText().toString();
        String height = heightInput.getText().toString();
        String interval = assessmentIntervalSpinner.getSelectedItem().toString();

        if (weight.isEmpty() || height.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the data to SharedPreferences (could use a database if needed)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("weight", weight);
        editor.putString("height", height);
        editor.putString("interval", interval);
        editor.apply();

        Toast.makeText(this, "Assessment saved!", Toast.LENGTH_SHORT).show();
    }
}
