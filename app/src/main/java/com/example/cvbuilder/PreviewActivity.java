package com.example.cvbuilder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";

    private TextView personalInfo, certificationInfo, referenceInfo, summaryInfo, educationInfo, experienceInfo;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        personalInfo = findViewById(R.id.personalInfo);
        certificationInfo = findViewById(R.id.certificationInfo);
        referenceInfo = findViewById(R.id.referenceInfo);
        educationInfo = findViewById(R.id.educationInfo);
        experienceInfo = findViewById(R.id.experienceInfo);
        summaryInfo = findViewById(R.id.summaryInfo);
        closeButton = findViewById(R.id.closePreview);
        educationInfo = findViewById(R.id.educationInfo);

        // Load and display stored data
        displayStoredData();

        // Close button action
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the preview screen
            }
        });
    }

    private void displayStoredData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "Not Provided");
        String phone = sharedPreferences.getString("phone", "Not Provided");
        String address = sharedPreferences.getString("address", "Not Provided");
        String certificate = sharedPreferences.getString("certificateName", "Not Provided");
        String reference = sharedPreferences.getString("reference", "Not Provided");
        String summary = sharedPreferences.getString("userSummary", "Not Provided");
        String experienceCompany = sharedPreferences.getString("experienceCompany", "Not Provided");
        String experienceStart = sharedPreferences.getString("experienceStartDate", "Not Provided");
        String experienceEnd = sharedPreferences.getString("experienceEndDate", "Not Provided");
        String education = sharedPreferences.getString("userEducation", "Not Provided");

        // Set separate values for each section
        personalInfo.setText("👤 Personal Information\n• Name: " + name + "\n• Phone: " + phone + "\n• Address: " + address);
        certificationInfo.setText("📜 Certification\n• " + certificate);
        referenceInfo.setText("📌 Reference\n• " + reference);
        summaryInfo.setText("Summary\n• " + summary);
        experienceInfo.setText("Experience\n• " + "Company Name: " + experienceCompany + "\n• Start Date: " + experienceStart + "\n• End Date: " + experienceEnd );
        educationInfo.setText("Education\n• " + education);
    }
}
