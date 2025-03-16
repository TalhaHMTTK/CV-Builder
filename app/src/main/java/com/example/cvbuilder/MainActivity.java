package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnProfile;

        btnProfile = findViewById(R.id.profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(iProfile);
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

    }
}