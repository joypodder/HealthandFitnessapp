package com.joyp.healthandfitnessapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class DietGuidelinesActivity extends AppCompatActivity {

    private TextView guidelinesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_guidelines);

        // Set up the TextView to display dietary guidelines
        guidelinesTextView = findViewById(R.id.guidelines_text_view);

        // Display static dietary guidelines (you can fetch from Firebase instead)
        String guidelines = "Here are some healthy dietary guidelines:\n\n" +
                "1. Eat a variety of foods.\n" +
                "2. Include plenty of fruits and vegetables.\n" +
                "3. Reduce salt intake.\n" +
                "4. Limit sugar and saturated fats.\n" +
                "5. Drink enough water throughout the day.";

        guidelinesTextView.setText(guidelines);
    }
}
