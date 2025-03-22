package com.example.cvbuilder;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ExperienceActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_COMPANY_NAME = "experienceCompany";
    private static final String KEY_START_DATE = "experienceStartDate";
    private static final String KEY_END_DATE = "experienceEndDate";

    private EditText companyNameEditText, fromDateEditText, toDateEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        companyNameEditText = findViewById(R.id.experience);
        fromDateEditText = findViewById(R.id.fromExperienceDate);
        toDateEditText = findViewById(R.id.toExperienceDate);
        saveButton = findViewById(R.id.saveExperience);
        cancelButton = findViewById(R.id.cancelExperience);

        // Load saved data
        loadExperienceData();

        // Open Date Picker when clicking on "Start Date" field
        fromDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(fromDateEditText);
            }
        });

        // Open Date Picker when clicking on "End Date" field
        toDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(toDateEditText);
            }
        });

        // Save experience when clicking "Save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExperienceData();
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

    // Function to open Date Picker and set selected date in EditText
    private void showDatePicker(final EditText dateEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateEditText.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    // Function to save entered experience data in SharedPreferences
    private void saveExperienceData() {
        String companyName = companyNameEditText.getText().toString().trim();
        String startDate = fromDateEditText.getText().toString().trim();
        String endDate = toDateEditText.getText().toString().trim();

        if (companyName.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COMPANY_NAME, companyName);
        editor.putString(KEY_START_DATE, startDate);
        editor.putString(KEY_END_DATE, endDate);
        editor.apply();

        Toast.makeText(this, "Experience saved!", Toast.LENGTH_SHORT).show();
    }

    // Function to load saved experience data from SharedPreferences
    private void loadExperienceData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedCompany = sharedPreferences.getString(KEY_COMPANY_NAME, "");
        String savedStartDate = sharedPreferences.getString(KEY_START_DATE, "");
        String savedEndDate = sharedPreferences.getString(KEY_END_DATE, "");

        companyNameEditText.setText(savedCompany);
        fromDateEditText.setText(savedStartDate);
        toDateEditText.setText(savedEndDate);
    }
}
