package com.example.cvbuilder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CertificationsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_CERTIFICATE = "certificateName";

    private EditText certificateEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        certificateEditText = findViewById(R.id.certificate);
        saveButton = findViewById(R.id.saveCertificate);
        cancelButton = findViewById(R.id.cancelCertificate);

        // Load saved data
        loadCertificateData();

        // Save certificate when clicking "Save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCertificateData();
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

    // Function to save entered certificate data in SharedPreferences
    private void saveCertificateData() {
        String certificateName = certificateEditText.getText().toString().trim();

        if (certificateName.isEmpty()) {
            Toast.makeText(this, "Please enter a certificate", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CERTIFICATE, certificateName);
        editor.apply();

        Toast.makeText(this, "Certificate saved!", Toast.LENGTH_SHORT).show();
    }

    // Function to load saved certificate data from SharedPreferences
    private void loadCertificateData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedCertificate = sharedPreferences.getString(KEY_CERTIFICATE, "");
        certificateEditText.setText(savedCertificate);
    }
}
