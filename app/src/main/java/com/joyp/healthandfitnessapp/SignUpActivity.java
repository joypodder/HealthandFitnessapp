package com.joyp.healthandfitnessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "HealthAppPrefs";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button signUpButton = findViewById(R.id.signup_button);

        // Get SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate the username and password fields
                if (TextUtils.isEmpty(username)) {
                    usernameEditText.setError("Username is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Password is required");
                    return;
                }

                // Save the new user's credentials in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USERNAME_KEY, username);
                editor.putString(PASSWORD_KEY, password);
                editor.apply(); // Save changes

                // Inform the user of successful sign-up
                Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                // Redirect to LoginActivity after successful sign-up
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close SignUpActivity
            }
        });
    }
}
