package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {


    Button as_work, as_visit;

<<<<<<< HEAD
    private Context context;


=======
>>>>>>> abd4ac3ef9820429b6acd18ee361e646a8192706
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        as_work = findViewById(R.id.btn_as_work);
        as_visit = findViewById(R.id.btn_as_visit);


        as_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_work = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent_work);
            }
        });

//        as_visit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent_visit = new Intent(getApplicationContext(), EditMyWorkActivity.class);
//                startActivity(intent_visit);
//            }
//        });


        as_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                Intent intent_work = new Intent(StartActivity.this, CustomerLogin.class);
                startActivity(intent_work);
            }
        });

=======

                Intent intent_visit = new Intent(getApplicationContext(),Category.class);

                //Intent intent_visit = new Intent(getApplicationContext(),EditWorkProfileActivity.class);

                startActivity(intent_visit);
            }
        });


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(StartActivity.this,ReviewActivity.class);
//                startActivity(intent);
//            }
//        });
>>>>>>> abd4ac3ef9820429b6acd18ee361e646a8192706
    }

}