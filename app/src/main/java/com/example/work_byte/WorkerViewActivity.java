package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WorkerViewActivity extends AppCompatActivity {


    TextView firstnameview, lastnameview, emailview, workareaview, teleview, experenceview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_view);

        firstnameview = findViewById(R.id.first_name_view);
        lastnameview = findViewById(R.id.last_name_view);
        emailview = findViewById(R.id.last_name_view);
        workareaview = findViewById(R.id.last_name_view);
        teleview = findViewById(R.id.last_name_view);
        experenceview = findViewById(R.id.last_name_view);
        lastnameview = findViewById(R.id.last_name_view);

    }


}