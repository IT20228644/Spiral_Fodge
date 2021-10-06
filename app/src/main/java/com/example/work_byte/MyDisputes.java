package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_byte.Database.DbHandler;

import java.text.DateFormat;
import java.util.Calendar;

public class MyDisputes extends AppCompatActivity {

    private TextView t1,t2;
    private Button btn;
    private DbHandler dbHandler;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_disputes);

        t1=findViewById(R.id.hire);
        t2=findViewById(R.id.daten);
        btn=findViewById(R.id.delete);
        context=this;
        dbHandler=new DbHandler(context);
        Bundle b = getIntent().getExtras();
        String email = b.getString("email");
        String contract = b.getString("des");
        Calendar calendar=Calendar.getInstance();
        String currntdate= DateFormat.getDateInstance().format(calendar.getTime());
        t1.setText(contract);
        t2.setText(currntdate);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteHire(email);
                Toast.makeText(MyDisputes.this, "Successfully Canceled the hire", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,Category.class));
            }
        });



    }
}