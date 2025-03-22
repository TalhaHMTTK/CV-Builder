package com.example.cvbuilder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_SUMMARY = "userSummary";

    private EditText summaryEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryEditText = findViewById(R.id.summary);
        saveButton = findViewById(R.id.saveSummary);
        cancelButton = findViewById(R.id.cancelSummary);

        // Load saved summary from SharedPreferences
        loadSummary();

        // Save summary when clicking "Save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSummary();
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

    private void saveSummary() {
        String summaryText = summaryEditText.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SUMMARY, summaryText);
        editor.apply();
        Toast.makeText(this, "Summary saved!", Toast.LENGTH_SHORT).show();
    }

    private void loadSummary() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedSummary = sharedPreferences.getString(KEY_SUMMARY, "");
        summaryEditText.setText(savedSummary);
    }
}
