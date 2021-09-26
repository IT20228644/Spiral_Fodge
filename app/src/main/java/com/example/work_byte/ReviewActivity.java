package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {

    Button btnsubmit, btncancel;
    RatingBar ratingBar;

    float myRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        btnsubmit = findViewById(R.id.btnsubmit);
        btncancel = findViewById(R.id.btncancel);
        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            private RatingBar ratingBar;

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                myRating = ratingBar.getRating();
                Toast.makeText(ReviewActivity.this, "Your rating is: " + myRating, Toast.LENGTH_SHORT).show();

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ReviewActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ReviewActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });
    }
}