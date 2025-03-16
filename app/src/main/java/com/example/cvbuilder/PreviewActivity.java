package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imageView = findViewById(R.id.imageViewPreview);

        // Get image URI from Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("imageUri")) {
            String imageUriString = intent.getStringExtra("imageUri");
            Log.d("PreviewActivity", "Received Image URI: " + imageUriString); // Debugging line

            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);
                imageView.setImageURI(imageUri); // Use this instead of MediaStore.Images.Media.getBitmap()
            } else {
                Log.e("PreviewActivity", "Image URI is null");
            }
        } else {
            Log.e("PreviewActivity", "Intent does not contain imageUri");
        }
    }
}
