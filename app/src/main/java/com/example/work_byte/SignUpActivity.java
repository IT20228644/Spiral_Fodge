package com.example.work_byte;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work_byte.Database.DBHelper;

public class SignUpActivity extends AppCompatActivity{

    EditText f_name, l_name, email, m_number, work_area, password, re_password, address, experience, category;
    Button sign_up, sign_in;
    DBHelper DB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        f_name = findViewById(R.id.f_name);
        l_name = findViewById(R.id.l_name);
        email = findViewById(R.id.email);
        m_number = findViewById(R.id.m_number);
        work_area = findViewById(R.id.work_area);
        password = findViewById(R.id.password);
        re_password = findViewById(R.id.re_password);
        address = findViewById(R.id.address);
        experience = findViewById(R.id.experience);
        sign_up = findViewById(R.id.btnsignup);
        sign_in = findViewById(R.id.btnlogsignin);
        category = findViewById(R.id.categoryin);
        DB = new DBHelper(this);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (f_name.getText().toString().equals("") || l_name.getText().toString().equals("") || email.getText().toString().equals("") || m_number.getText().toString().equals("") || work_area.getText().toString().equals("") || password.getText().toString().equals("") || re_password.getText().toString().equals("") || address.getText().toString().equals("") || experience.getText().toString().equals("") || category.getText().toString().equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    dbHelper.insertData(f_name.getText().toString(),
                            l_name.getText().toString(),
                            email.getText().toString(),
                            m_number.getText().toString(),
                            work_area.getText().toString(),
                            password.getText().toString(),
                            re_password.getText().toString(),
                            address.getText().toString(),
                            category.getText().toString(),
                            experience.getText().toString());
//

                    if (password.getText().toString().equals(re_password.getText().toString())){
                        Boolean usercheck = DB.checkUseremail(email.getText().toString());
                        if (usercheck == false){
//                            UserDetails.User newRowID = new UserDetails.User(f_name,l_name,
//                                    email,m_number,work_area,
//                                    password,re_password);
                            boolean insert = DB.insertData(f_name.getText().toString(), l_name.getText().toString() ,email.getText().toString(), m_number.getText().toString(), work_area.getText().toString(), password.getText().toString() ,re_password.getText().toString(), address.getText().toString(), experience.getText().toString(), category.getText().toString());
                            if (insert == false){

                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), WorkerViewActivity.class);
                                startActivity(intent);
                            }else
                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(SignUpActivity.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), WorkerViewActivity.class);
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