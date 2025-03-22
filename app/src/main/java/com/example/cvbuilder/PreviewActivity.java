package com.example.cvbuilder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";

    private TextView personalInfo, certificationInfo, referenceInfo, summaryInfo, educationInfo, experienceInfo;
    private Button closeButton, shareButton;

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
        shareButton = findViewById(R.id.sharePreview); // New Share Button

        // Load and display stored data
        displayStoredData();

        // Close button action
        closeButton.setOnClickListener(v -> finish()); // Close the preview screen

        // Share button action
        shareButton.setOnClickListener(v -> sharePreview());
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

    private void sharePreview() {
        String previewText = "📄 CV Preview\n\n" +
                "👤 Personal Information\n" +
                "• Name: " + personalInfo.getText().toString().replace("👤 Personal Information\n", "") + "\n\n" +
                "📜 Certification\n" +
                "• " + certificationInfo.getText().toString().replace("📜 Certification\n", "") + "\n\n" +
                "📌 Reference\n" +
                "• " + referenceInfo.getText().toString().replace("📌 Reference\n", "") + "\n\n" +
                "📘 Education\n" +
                "• " + educationInfo.getText().toString().replace("Education\n• ", "") + "\n\n" +
                "💼 Experience\n" +
                "• " + experienceInfo.getText().toString().replace("Experience\n• ", "") + "\n\n" +
                "📝 Summary\n" +
                "• " + summaryInfo.getText().toString().replace("Summary\n• ", "");

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, previewText);
        startActivity(Intent.createChooser(shareIntent, "Share CV via"));
    }
}
