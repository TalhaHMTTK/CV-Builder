package com.example.cvbuilder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_EDUCATION = "userEducation";

    private EditText educationEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        educationEditText = findViewById(R.id.education);
        saveButton = findViewById(R.id.saveEducation);
        cancelButton = findViewById(R.id.cancelEducation);

        // Load saved education data
        loadEducation();

        // Save education when clicking "Save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEducation();
                finish();
            }
        });

        // Go back to MainActivity when clicking "Cancel" button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Closes this activity and returns to MainActivity
            }
        });
    }

    private void saveEducation() {
        String educationText = educationEditText.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EDUCATION, educationText);
        editor.apply();
        Toast.makeText(this, "Education saved!", Toast.LENGTH_SHORT).show();
    }

    private void loadEducation() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedEducation = sharedPreferences.getString(KEY_EDUCATION, "");
        educationEditText.setText(savedEducation);
    }
}
