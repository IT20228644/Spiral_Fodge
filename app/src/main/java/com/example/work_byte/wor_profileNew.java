package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_byte.Database.DBHelper;
import com.example.work_byte.Database.DbHandler;
import com.example.work_byte.Database.UserDetails;

public class wor_profileNew extends AppCompatActivity {

    private TextView ufirstnameview, ulastnameview, uemailview, uworkareaview, uteleview, uexperenceview,udes,usal;
    private DBHelper dbHelper;
    private Context context;
    Button btn1;

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
        usal=findViewById(R.id.salView);
        btn1=findViewById(R.id.hireMe);

        //catch the values coming from relavent d
        final String email=getIntent().getStringExtra("email");

        //System.out.println(worker_id);
        UserDetails workerModel=dbHelper.getSingleWorkerbyEmail(email);//string to int
        ufirstnameview.setText(workerModel.getFirst_name());
        ulastnameview.setText(workerModel.getLast_name());
        uemailview.setText(workerModel.getEmail());
        uworkareaview.setText(workerModel.getWorkArea());
        uteleview.setText(workerModel.getMobile());
        uexperenceview.setText(workerModel.getExperience());
        udes.setText(workerModel.getCategory());
        usal.setText(String.valueOf(workerModel.getSalary()));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,AddHireDetail.class);
                intent.putExtras(getIntent().getExtras());
                intent.putExtra("worker",workerModel.getFirst_name());
                intent.putExtra("data1",String.valueOf(workerModel.getSalary()));

                startActivity(intent);
            }
        });


    }




    }

