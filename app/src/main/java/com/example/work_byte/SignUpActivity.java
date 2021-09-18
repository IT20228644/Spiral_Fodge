package com.example.work_byte;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work_byte.Database.DBHelper;
import com.example.work_byte.Database.UserDetails;

public class SignUpActivity extends AppCompatActivity {

    EditText f_name, l_name, email, m_number, work_area, password, re_password;
    Button sign_up;
    DBHelper DB;
    private Context context;

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
        sign_up = findViewById(R.id.btnsignup);
        DB = new DBHelper(this);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.insertData(f_name.getText().toString(),
                                    l_name.getText().toString(),
                                    email.getText().toString(),
                                    m_number.getText().toString(),
                                    work_area.getText().toString(),
                                    password.getText().toString(),
                                    re_password.getText().toString());

//                String fname = f_name.getText().toString();
//                String lname = l_name.getText().toString();
//                String e_mail = email.getText().toString();
//                String mobile = m_number.getText().toString();
//                String workarea = work_area.getText().toString();
//                String p_word = password.getText().toString();
//                String re_p_word = re_password.getText().toString();

                if (f_name.equals("") || l_name.equals("") || email.equals("") || m_number.equals("") || work_area.equals("") || password.equals("") || re_password.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password.getText().toString().equals(re_password.getText().toString())){
                        Boolean usercheck = DB.checkUseremail(email.getText().toString());
                        if (usercheck == false){
                            UserDetails newRowID = new UserDetails(f_name,l_name,email,m_number,work_area,password,re_password);
                            boolean insert = DB.insertData(f_name.getText().toString(), l_name.getText().toString() ,email.getText().toString(), m_number.getText().toString(), work_area.getText().toString(), password.getText().toString() ,re_password.getText().toString());
                            if (insert == false){
                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else
                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(SignUpActivity.this, "User already registered.", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(SignUpActivity.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}