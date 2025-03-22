package com.example.cvbuilder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonelActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";

    private EditText nameEditText, phoneEditText, addressEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel);

        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phone);
        addressEditText = findViewById(R.id.address);
        saveButton = findViewById(R.id.savePersonal);
        cancelButton = findViewById(R.id.cancelPersonal);

        // Load saved data
        loadPersonalData();

        // Save personal info when clicking "Save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePersonalData();
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

    // Function to save personal info in SharedPreferences
    private void savePersonalData() {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_ADDRESS, address);
        editor.apply();

        Toast.makeText(this, "Personal Information Saved!", Toast.LENGTH_SHORT).show();
    }

    // Function to load saved personal info from SharedPreferences
    private void loadPersonalData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nameEditText.setText(sharedPreferences.getString(KEY_NAME, ""));
        phoneEditText.setText(sharedPreferences.getString(KEY_PHONE, ""));
        addressEditText.setText(sharedPreferences.getString(KEY_ADDRESS, ""));
    }
}
