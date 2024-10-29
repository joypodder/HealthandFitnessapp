package com.joyp.healthandfitnessapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StepTrackerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView stepsTextView;
    private ProgressBar progressBar;
    private boolean isTracking = false;
    private Sensor stepSensor;
    private boolean isStepCounter = false;

    // Variables to keep track of steps
    private int totalSteps = 0;
    private int initialStepCount = -1;
    private int currentStepCount = 0;
    private final int TARGET_STEPS = 5000; // Set target step goal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracker);

        stepsTextView = findViewById(R.id.steps_text_view);
        progressBar = findViewById(R.id.progressBar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        if (stepSensor == null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isStepCounter = true;
        }

        if (stepSensor != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
                } else {
                    startStepTracking();
                }
            } else {
                startStepTracking();
            }
        } else {
            stepsTextView.setText("Step Sensor not available");
        }
    }

    private void startStepTracking() {
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        isTracking = true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isTracking) {
            if (isStepCounter) {
                if (initialStepCount == -1) {
                    initialStepCount = (int) event.values[0];
                }
                currentStepCount = (int) event.values[0] - initialStepCount;
            } else {
                currentStepCount++;
            }

            // Update the progress bar and the text view
            progressBar.setProgress(currentStepCount);
            stepsTextView.setText(String.valueOf(currentStepCount));

            // Optional: If you want to handle over 5000 steps
            if (currentStepCount >= TARGET_STEPS) {
                stepsTextView.setText("Target Reached!");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing for now
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startStepTracking();
        } else {
            stepsTextView.setText("Permission for Activity Recognition not granted");
        }
    }
}
