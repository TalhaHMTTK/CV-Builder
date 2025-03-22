package com.example.cvbuilder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PROFILE = 2;

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView profileImageView;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_IMAGE_URI = "savedImageUri";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnProfile;

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        btnProfile = findViewById(R.id.profile);
        profileImageView = findViewById(R.id.mainPic);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivityForResult(iProfile, PICK_IMAGE_REQUEST);
            }
        });






        Button btnPersonel;

        btnPersonel = findViewById(R.id.personel);
        btnPersonel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iPersonel = new Intent(MainActivity.this, PersonelActivity.class);
                startActivity(iPersonel);
            }
        });


        Button btnSummary;

        btnSummary = findViewById(R.id.summary);
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSummary = new Intent(MainActivity.this, SummaryActivity.class);
                startActivity(iSummary);
            }
        });


        Button btnEducation;

        btnEducation = findViewById(R.id.education);
        btnEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEducation = new Intent(MainActivity.this, EducationActivity.class);
                startActivity(iEducation);
            }
        });


        Button btnExperience;

        btnExperience = findViewById(R.id.experience);
        btnExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iExperience = new Intent(MainActivity.this, ExperienceActivity.class);
                startActivity(iExperience);
            }
        });


        Button btnCertificate;

        btnCertificate = findViewById(R.id.certifications);
        btnCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCertificate = new Intent(MainActivity.this, CertificationsActivity.class);
                startActivity(iCertificate);
            }
        });

        Button btnReferences;

        btnReferences = findViewById(R.id.references);
        btnReferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iReferences = new Intent(MainActivity.this, ReferencesActivity.class);
                startActivity(iReferences);
            }
        });


        Button btnPreview;

        btnPreview = findViewById(R.id.btnPreview);
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iPreview = new Intent(MainActivity.this, PreviewActivity.class);
                startActivity(iPreview);
            }
        });

    }

    // code of profile pic save
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null && data.getStringExtra("selectedImageUri") != null) {
                Uri selectedImageUri = Uri.parse(data.getStringExtra("selectedImageUri"));

                profileImageView.setImageURI(selectedImageUri);

                Toast.makeText(this, "Image URI saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Image URI is null", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
