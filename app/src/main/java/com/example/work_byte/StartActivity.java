package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.content.Context;
=======
>>>>>>> 6d94a05752b2b8345db5db48b451134849738bed
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

<<<<<<< HEAD
    Button as_work, as_visit;

    private Context context;
=======
    public Button button;
>>>>>>> 6d94a05752b2b8345db5db48b451134849738bed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

<<<<<<< HEAD
        as_work = findViewById(R.id.btn_as_work);
        as_visit = findViewById(R.id.btn_as_visit);

        as_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_work = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent_work);
            }
        });

        as_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_visit = new Intent(getApplicationContext(),EditMyWorkActivity.class);
                startActivity(intent_visit);
            }
        });
=======
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(StartActivity.this,ReviewActivity.class);
                startActivity(intent);
            }
        });


>>>>>>> 6d94a05752b2b8345db5db48b451134849738bed
    }

}