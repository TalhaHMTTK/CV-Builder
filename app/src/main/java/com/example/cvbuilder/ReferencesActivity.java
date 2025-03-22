package com.example.cvbuilder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReferencesActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_REFERENCE = "reference";

    private EditText referenceEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        referenceEditText = findViewById(R.id.reference);
        saveButton = findViewById(R.id.saveReference);
        cancelButton = findViewById(R.id.cancelReference);

        // Load saved reference data
        loadReferenceData();

        // Save reference when clicking "Save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReferenceData();
                finish();
            }
        });

        // Close activity when clicking "Cancel" button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the activity and returns to the previous screen
            }
        });
    }

    // Function to save reference data in SharedPreferences
    private void saveReferenceData() {
        String reference = referenceEditText.getText().toString().trim();

        if (reference.isEmpty()) {
            Toast.makeText(this, "Please enter a reference", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REFERENCE, reference);
        editor.apply();

        Toast.makeText(this, "Reference Saved!", Toast.LENGTH_SHORT).show();
    }

    // Function to load saved reference data from SharedPreferences
    private void loadReferenceData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        referenceEditText.setText(sharedPreferences.getString(KEY_REFERENCE, ""));
    }
}
