package com.example.work_byte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.work_byte.Database.DbHandler;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateHire extends AppCompatActivity {

    private TextInputLayout t1,t2,t3,t4,t5,t6,t7;
    private DbHandler dbHandler;
    private Button btn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hire);

        t1=findViewById(R.id.update_name);
        t2=findViewById(R.id.update_email);
        t3=findViewById(R.id.update_phone);
        t4=findViewById(R.id.update_location);
        t5=findViewById(R.id.update_description);
        t6=findViewById(R.id.update_duration);
        t7=findViewById(R.id.update_date);
        btn=findViewById(R.id.update_btn);


        context=this;
        dbHandler=new DbHandler(context);
        Bundle b = getIntent().getExtras();
        String data1 = b.getString("cusName");
        String data2 = b.getString("email");
        String data3 = b.getString("uPhone");
        String data4 = b.getString("cusLoc");
        String data5 = b.getString("des");
        String data6 = b.getString("data2");
        String data7 = b.getString("uDate");

        t1.getEditText().setText(data1);
        t1.setEnabled(false);
        t2.getEditText().setText(data2);
        t2.setEnabled(false);
        t3.getEditText().setText(data3);
        t3.setEnabled(false);
        t4.getEditText().setText(data4);
        t5.getEditText().setText(data5);
        t6.getEditText().setText(data6);
        t6.setEnabled(false);
        t7.getEditText().setText(data7);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=t1.getEditText().getText().toString();
                String uemail=t2.getEditText().getText().toString();
                String uphone=t3.getEditText().getText().toString();
                String ulocation=t4.getEditText().getText().toString();
                String udes=t5.getEditText().getText().toString();
                String uduration=t6.getEditText().getText().toString();
                String udate=t7.getEditText().getText().toString();
                //String todoDes=editdes.getText().toString();


                //save object with updated values
//                HireModel hire=new HireModel(uname,Integer.parseInt(uphone),uemail,ulocation,udate,Integer.parseInt(uduration),udes);
//                int state=dbHandler.updateHire(hire);
//                System.out.println(state);
//                startActivity(new Intent(context,MainActivity.class));

                boolean isUpdate= dbHandler.updateHireDetail(uemail,uname,uphone,ulocation,udes,uduration,udate);
                if(isUpdate==true){
                    Toast.makeText(UpdateHire.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder= new AlertDialog.Builder(context);//here canot use this.Because we
                    // need mainactivity.class content.when use this wiill invoke only this method
                    builder.setTitle("Updated Details");
                    builder.setMessage("Customer : "+uname+"\n"+"Email : "+uemail+"\n"+"Phone : "+uphone+"\n"
                            +"Work Location : "+ulocation+"\n"+"Contract : "+udes+"\n"+"Completion Duration : "+uduration+"\n"+
                            "Starting Date : "+udate);

                    builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(context,HireDetail.class));
                        }
                    });
                    builder.show();



                }else{
                    Toast.makeText(UpdateHire.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}