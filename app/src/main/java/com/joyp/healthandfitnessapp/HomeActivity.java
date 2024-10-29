package com.joyp.healthandfitnessapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;


public class HomeActivity extends AppCompatActivity {

    private Button assessmentButton, dietGuidelinesButton, eatToolButton, stepTrackerButton, nicotineTrackerButton, bmiTrackerButton;
    private VideoView videoView;
    private FirebaseStorage firebaseStorage;
    private StorageReference videoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up the VideoView and play the video from Firebase Storage
        setUpVideoView();

        //schedule notification
        scheduleNotification();

        // Set up navigation for different features
        setUpNavigationButtons();
    }
    private void scheduleNotification() {
        // Set constraints (optional), e.g., requires the device to not be low on battery
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)  // Only run if the battery is not low
                .build();

        // One-time work request for the first notification (after 1 minute)
        OneTimeWorkRequest firstNotificationWork = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setInitialDelay(1, TimeUnit.MINUTES)  // Delay the first notification by 1 minute
                .setConstraints(constraints)
                .build();

        // Periodic work request for subsequent notifications (every 24 hours)
        PeriodicWorkRequest subsequentNotificationsWork = new PeriodicWorkRequest.Builder(NotificationWorker.class, 24, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();

        // Enqueue the first notification work
        WorkManager.getInstance(this).enqueue(firstNotificationWork);

        // Schedule the periodic work independently after the first one-time notification
        WorkManager.getInstance(this).enqueue(subsequentNotificationsWork);
    }



    private void setUpVideoView() {
        firebaseStorage = FirebaseStorage.getInstance();

        videoRef = firebaseStorage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/health-and-fitness-app-6d046.appspot.com/o/Vid1%20-%20Made%20with%20Clipchamp.mp4?alt=media&token=fa5d5d12-aadf-41b5-bb3f-ccb4cc78b46e");

        videoView = findViewById(R.id.video_view);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);



        // Fetch and play the video from Firebase Storage
        videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                videoView.setVideoURI(uri);
                videoView.start();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors (e.g., show a Toast message)
                Toast.makeText(HomeActivity.this, "Failed to load video: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setUpNavigationButtons() {
        // Navigate to Assessment page
        assessmentButton = findViewById(R.id.assessment_button);
        assessmentButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AssessmentActivity.class);
            startActivity(intent);
        });

        // Navigate to Dietary Guidelines page
        dietGuidelinesButton = findViewById(R.id.diet_guidelines_button);
        dietGuidelinesButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DietGuidelinesActivity.class);
            startActivity(intent);
        });

        // Navigate to Mini-EAT Tool page
        eatToolButton = findViewById(R.id.eat_tool_button);
        eatToolButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MiniEATToolActivity.class);
            startActivity(intent);
        });

        // Navigate to Step Tracker page
        stepTrackerButton = findViewById(R.id.step_tracker_button);
        stepTrackerButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, StepTrackerActivity.class);
            startActivity(intent);
        });

        // Navigate to Nicotine Exposure Tracker page
        nicotineTrackerButton = findViewById(R.id.nicotine_tracker_button);
        nicotineTrackerButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NicotineExposureActivity.class);
            startActivity(intent);
        });

        // Navigate to BMI Tracker page
        bmiTrackerButton = findViewById(R.id.bmi_tracker_button);
        bmiTrackerButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BMITrackerActivity.class);
            startActivity(intent);
        });
    }
}
