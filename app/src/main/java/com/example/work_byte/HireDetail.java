package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.work_byte.Database.DbHandler;
import com.example.work_byte.Database.HireModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class HireDetail extends AppCompatActivity {

    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    private Button btn1,btn2;
    private DbHandler dbHandler;
    private Context context;
    private List<HireModel> hires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_detail);


        t1=findViewById(R.id.customer);
        t2=findViewById(R.id.worker);
        t3=findViewById(R.id.date);
        t4=findViewById(R.id.location);
        t5=findViewById(R.id.s_date);
        t6=findViewById(R.id.tot);
        t7=findViewById(R.id.adv);
        t8=findViewById(R.id.bal);
        t9=findViewById(R.id.duraa);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        context=this;
        dbHandler=new DbHandler(context);

        hires=dbHandler.getAllHireDetail();

        Bundle b = getIntent().getExtras();
        String tot = b.getString("total");
        String adv = b.getString("advance");
        String cus=b.getString("cusName");
        String loc=b.getString("cusLoc");
        String time=b.getString("data2");
        String worker=b.getString("worker");
        String date=b.getString("uDate");
        Calendar calendar=Calendar.getInstance();
        String currntdate= DateFormat.getDateInstance().format(calendar.getTime());

        double bal=Double.parseDouble(tot)-Double.parseDouble(adv);

        t1.setText(cus);
        t2.setText(worker);
        t3.setText(currntdate);
        t4.setText(loc);
        t5.setText(date);
        t6.setText("Rs."+tot);
        t7.setText("Rs."+adv);
        t8.setText("Rs."+String.valueOf(bal));
        t9.setText(time);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,UpdateHire.class);
                i.putExtras(getIntent().getExtras());
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MyDisputes.class);
                intent.putExtras(getIntent().getExtras());
                startActivity(intent);
            }
        });





    }
}