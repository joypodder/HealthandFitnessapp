package com.joyp.healthandfitnessapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MiniEATToolActivity extends AppCompatActivity {

    private EditText mealInput, calorieInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_eat_tool);

        mealInput = findViewById(R.id.meal_input);
        calorieInput = findViewById(R.id.calorie_input);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> {
            String meal = mealInput.getText().toString();
            String calories = calorieInput.getText().toString();

            if (meal.isEmpty() || calories.isEmpty()) {
                Toast.makeText(MiniEATToolActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MiniEATToolActivity.this, "Meal logged: " + meal + " (" + calories + " kcal)", Toast.LENGTH_LONG).show();
            }
        });
    }
}
