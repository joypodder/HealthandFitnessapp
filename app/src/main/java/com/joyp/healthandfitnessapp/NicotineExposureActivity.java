package com.joyp.healthandfitnessapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NicotineExposureActivity extends AppCompatActivity {

    private EditText nicotineInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nicotine_exposure);

        nicotineInput = findViewById(R.id.nicotine_input);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> {
            String nicotineCount = nicotineInput.getText().toString();

            if (nicotineCount.isEmpty()) {
                Toast.makeText(NicotineExposureActivity.this, "Please enter nicotine amount", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(NicotineExposureActivity.this, "Nicotine logged: " + nicotineCount + " cigarettes", Toast.LENGTH_LONG).show();
            }
        });
    }
}
