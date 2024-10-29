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

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "HealthAppPrefs";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);

        // Get SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
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

                // Retrieve stored credentials from SharedPreferences
                String savedUsername = sharedPreferences.getString(USERNAME_KEY, "");
                String savedPassword = sharedPreferences.getString(PASSWORD_KEY, "");

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    // Credentials match, log the user in
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(IS_LOGGED_IN, true);  // Set login status to true
                    editor.apply(); // Save changes

                    // Redirect to AssessmentActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Close LoginActivity
                } else {
                    // Show error if credentials don't match
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle sign-up button (redirects to SignUpActivity)
        Button signUpButton = findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
