package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.work_byte.Database.DbHandler;
import com.example.work_byte.Database.UserDetails;

public class Payment extends AppCompatActivity {

    private TextView t1,t2;
    private Button btn1;
    private Button btn2;
    private ImageView img1,img2;
    private UserDetails worker;
    //private List<HireModel> all;
    Context context;
    DbHandler dbHandler;
    private  int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        t1=findViewById(R.id.textView9);
        t2=findViewById(R.id.textView10);
        btn1=findViewById(R.id.payment_btn1);
        btn2=findViewById(R.id.payment_btn2);
        img1=findViewById(R.id.pay_img1);
        img2=findViewById(R.id.pay_img2);
        context=this;
        dbHandler=new DbHandler(context);
        //worker=new WorkerModel();



        Bundle b = getIntent().getExtras();
        String data1 = b.getString("data1");
        String data2 = b.getString("data2");
        int ans=Integer.parseInt(data1)*Integer.parseInt(data2);

        double ans2=ans*0.1;

        t1.setText(String.valueOf("Rs."+ans));
        t2.setText(String.valueOf("Rs."+ans2));


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BankTransfer.class);
                intent.putExtras(getIntent().getExtras());
                intent.putExtra("total",String.valueOf(ans));
                intent.putExtra("advance",String.valueOf(ans2));
                startActivity(intent);
            }
        });
    }
}