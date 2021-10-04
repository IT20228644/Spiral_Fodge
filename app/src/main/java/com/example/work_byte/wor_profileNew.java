package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_byte.Database.DBHelper;
import com.example.work_byte.Database.DbHandler;
import com.example.work_byte.Database.UserDetails;

public class wor_profileNew extends AppCompatActivity {

    private TextView ufirstnameview, ulastnameview, uemailview, uworkareaview, uteleview, uexperenceview,udes;
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wor_profile_new);

        context=this;
        dbHelper=new DBHelper(context);
        ufirstnameview = findViewById(R.id.first_name_view);
        ulastnameview = findViewById(R.id.last_name_view);
        uemailview = findViewById(R.id.email_view);
        uworkareaview = findViewById(R.id.work_area_view);
        uteleview = findViewById(R.id.tele_view);
        uexperenceview = findViewById(R.id.experience_view);
        udes=findViewById(R.id.des_view);

        //catch the values coming from relavent d
        final String worker_id=getIntent().getStringExtra("worker_id");

        //System.out.println(worker_id);
        UserDetails workerModel=dbHelper.getSingleWorker(worker_id);//string to int
        ufirstnameview.setText(workerModel.getFirst_name());
        ulastnameview.setText(workerModel.getLast_name());
        uemailview.setText(workerModel.getEmail());
        uworkareaview.setText(workerModel.getWorkArea());
        uteleview.setText(workerModel.getMobile());
        uexperenceview.setText(workerModel.getExperience());
        udes.setText(workerModel.getCategory());


    }




    }

