package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.work_byte.Database.DbHandler;
import com.example.work_byte.Database.HireModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class HireDetail extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="com.example.mad_mobileapp";
    TextInputLayout name, email, phone,location,des,time,date;
    Button btn;
    private List<HireModel> hires;
    //private List<WorkerModel> workers;

    private DbHandler dbHandler;
    private Context context;

    private  int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_detail);
        name = findViewById(R.id.hire_name);
        email = findViewById(R.id.hire_email);
        phone = findViewById(R.id.hire_phone);
        location=findViewById(R.id.hire_location);
        des=findViewById(R.id.hire_description);
        date=findViewById(R.id.hire_date);
        time=findViewById(R.id.hire_duration);
        btn=findViewById(R.id.hire_btn);
        hires=new ArrayList<>();
        //workers=new ArrayList<>();
        context=this;


        dbHandler=new DbHandler(context);


        // workers=dbHandler.getAllWorkers("Mason");

    }

    public void call(View view) {

        if (!validateName() | !validateEmail() | !validatePhone() | !validatelLocation() | !validateDes() | !validateDuration() | !validateDate()) {
            return;

        }

        String uName=name.getEditText().getText().toString();
        int uPhone=Integer.parseInt(phone.getEditText().getText().toString());
        String uEmail=email.getEditText().getText().toString();
        String uLoc=location.getEditText().getText().toString();
        String uDate= date.getEditText().getText().toString();
        int uTime=Integer.parseInt(time.getEditText().getText().toString());
        String uDes=des.getEditText().getText().toString();

        HireModel hireModel=new HireModel(uName,uPhone,uEmail,uLoc,uDate,uTime,uDes);

        dbHandler.addHireDetails(hireModel);

        hires=dbHandler.getAllHireDetail();

        //HireModel hire=hires.get(position);//comes output as objects
        //WorkerModel worker=workers.get(position);//comes output as objects

        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE,String.valueOf(uTime));
        //intent.putExtra("worker_id",String.valueOf(worker.getWorkerId()));

        //System.out.println(hire.getId());
        //System.out.println(worker.getWorkerId());

        startActivity(intent);
    }
    /*validations*/
    private boolean validateName () {
        String val = name.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            name.setError("Field Cannot Be Empty");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail () {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field Cannot Be Empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePhone () {
        String val = phone.getEditText().getText().toString().trim();
        String checkPhone = "[0-9]{10}";


        if (val.isEmpty()) {
            phone.setError("Field Cannot Be Empty");
            return false;
        } else if (!val.matches(checkPhone)) {
            phone.setError("Invalid Phone no");
            return false;

        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatelLocation () {
        String val = location.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            location.setError("Field Cannot Be Empty");
            return false;
        } else {
            location.setError(null);
            location.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDes () {
        String val = des.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            des.setError("Field Cannot Be Empty");
            return false;
        } else {
            des.setError(null);
            des.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDuration () {
        String val = time.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            time.setError("Field Cannot Be Empty");
            return false;
        } else {
            time.setError(null);
            time.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDate () {
        String val = date.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            date.setError("Field Cannot Be Empty");
            return false;
        } else {
            date.setError(null);
            date.setErrorEnabled(false);
            return true;
        }
    }



}
}