package com.example.cvbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private Uri imageUri;
    private ImageView imageView;
    private Button btnSaveImage, btnCancelImage, btnSelectImage;

    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        btnSaveImage = findViewById(R.id.btnSaveImage);
        btnCancelImage = findViewById(R.id.btnCancelImage);
        imageView = findViewById(R.id.imageView);
        btnSelectImage = findViewById(R.id.btnSelectImage);

        btnSelectImage.setOnClickListener(v -> openGallery());
        btnSaveImage.setOnClickListener(view -> saveImage());
        btnCancelImage.setOnClickListener(view -> finish());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void saveImage() {
        if (imageUri != null) {
            Log.d("SaveImage", "Saving Image URI: " + imageUri.toString());
            Toast.makeText(this, "Saving Image: " + imageUri.toString(), Toast.LENGTH_LONG).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedImageUri", imageUri.toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            Log.e("SaveImage", "imageUri is null!");
            Toast.makeText(this, "Please select an image first!", Toast.LENGTH_SHORT).show();
        }
    }
}
