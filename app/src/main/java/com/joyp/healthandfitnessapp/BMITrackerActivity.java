package com.joyp.healthandfitnessapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BMITrackerActivity extends AppCompatActivity {

    private EditText heightInput, weightInput;
    private Button calculateButton;
    private TextView bmiResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_tracker);

        heightInput = findViewById(R.id.height_input);
        weightInput = findViewById(R.id.weight_input);
        calculateButton = findViewById(R.id.calculate_button);
        bmiResultTextView = findViewById(R.id.bmi_result_text_view);

        calculateButton.setOnClickListener(v -> {
            String heightStr = heightInput.getText().toString();
            String weightStr = weightInput.getText().toString();

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(BMITrackerActivity.this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            } else {
                double height = Double.parseDouble(heightStr) / 100;  // Convert height from cm to meters
                double weight = Double.parseDouble(weightStr);
                double bmi = weight / (height * height);  // BMI formula

                bmiResultTextView.setText(String.format("Your BMI: %.2f", bmi));
            }
        });
    }
}
