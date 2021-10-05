package com.example.work_byte;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work_byte.Database.DBHelper;
import com.example.work_byte.Database.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity{

    EditText f_name, l_name, uemail, m_number, uwork_area, upassword, ure_password, uaddress, uexperience, ucategory;
    Button sign_up, sign_in;
    DBHelper DB;
    private List<UserDetails> workers;
    int position=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        f_name = findViewById(R.id.f_name);
        l_name = findViewById(R.id.l_name);
        uemail = findViewById(R.id.email);
        m_number = findViewById(R.id.m_number);
        uwork_area = findViewById(R.id.work_area);
        upassword = findViewById(R.id.password);
        ure_password = findViewById(R.id.re_password);
        uaddress = findViewById(R.id.address);
        uexperience = findViewById(R.id.experience);
        ucategory=findViewById(R.id.categoryin);

//        work_area = findViewById(R.id.work_area);
//        password = findViewById(R.id.password);
//        re_password = findViewById(R.id.re_password);
//        address = findViewById(R.id.address);
//        experience = findViewById(R.id.experience);

        sign_up = findViewById(R.id.btnsignup);
        sign_in = findViewById(R.id.btnlogsignin);
        workers=new ArrayList<>();

        DB = new DBHelper(this);
        workers=DB.getAllWorkers();


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (f_name.getText().toString().equals("") || l_name.getText().toString().equals("") || uemail.getText().toString().equals("") || m_number.getText().toString().equals("") || uwork_area.getText().toString().equals("") || upassword.getText().toString().equals("") || ure_password.getText().toString().equals("") || uaddress.getText().toString().equals("") || uexperience.getText().toString().equals("") || ucategory.getText().toString().equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    dbHelper.insertData(f_name.getText().toString(),
                            l_name.getText().toString(),
                            uemail.getText().toString(),
                            m_number.getText().toString(),
                            uwork_area.getText().toString(),
                            upassword.getText().toString(),
                            ure_password.getText().toString(),
                            uaddress.getText().toString(),
                            uexperience.getText().toString(),
                            ucategory.getText().toString());
//

                    if (upassword.getText().toString().equals(ure_password.getText().toString())){
                        Boolean usercheck = DB.checkUseremail(uemail.getText().toString());
                        if (usercheck == false){
//                            UserDetails.User newRowID = new UserDetails.User(f_name,l_name,
//                                    email,m_number,work_area,
//                                    password,re_password);
                            boolean insert = DB.insertData(f_name.getText().toString(), l_name.getText().toString() ,uemail.getText().toString(), m_number.getText().toString(), uwork_area.getText().toString(), upassword.getText().toString() ,ure_password.getText().toString(), uaddress.getText().toString(), uexperience.getText().toString(), ucategory.getText().toString());
                            if (insert == false){

                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), WorkerViewActivity.class);
                                startActivity(intent);
                            }else
                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(SignUpActivity.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), wor_profileNew.class);
                        String mail=uemail.getText().toString();
                        intent.putExtra("worker_id",mail);
                        startActivity(intent);

                    }else
                        Toast.makeText(SignUpActivity.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}