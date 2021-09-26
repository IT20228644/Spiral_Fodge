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

public class payment extends AppCompatActivity {

    private TextView t1,t2;
    private Button btn1;
    private Button btn2;
    private ImageView img1,img2;
    //private WorkerModel worker;
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

        //catch the values coming from relavent id.
        //final String id=getIntent().getStringExtra("hire_id");
        // public int getDura
        //System.out.println(id);
        //HireModel hire=dbHandler.getSingleHire(Integer.parseInt(id));//string to int

        //final String workerId=getIntent().getStringExtra("worker_id");
        //WorkerModel worker=dbHandler.getSingleWorker(Integer.parseInt(workerId));//string to int
        //System.out.println(workerId);

        //List<HireModel> hires=dbHandler.getAllHireDetail();

        //HireModel hire= dbHandler.getSingleHire();

        //Intent intent1=getIntent();
        //String r=intent1.getStringExtra(AddWorker.EXTRA_MESSAGE2);
        //System.out.println(r);
        Intent intent2=getIntent();
        String d=intent2.getStringExtra(HireDetail.EXTRA_MESSAGE);
        int ans=500*Integer.parseInt(d);

        double ans2=ans*0.1;

        t1.setText(String.valueOf(ans));
        t2.setText(String.valueOf(ans2));

//        Intent intent2=getIntent();
//        String d=intent2.getStringExtra(HireDetail.EXTRA_MESSAGE);
//        System.out.println(d);
//

        //int ans=Integer.parseInt(d)*Integer.parseInt(r);
        //t1.setText(String.valueOf(ans));
        //System.out.println(ans);
        //edit2.setText(worker.getSalary());
        //System.out.println(hire.getCustomerName());
        //System.out.println(hire.getWorker());

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, bank.class);
//                startActivity(intent);
            }
        });

    }
    }
