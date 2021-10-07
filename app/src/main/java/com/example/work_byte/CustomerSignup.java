package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work_byte.Database.DBHandle;

public class CustomerSignup extends AppCompatActivity {

    EditText uf_name, ul_name, uemail, um_number, upassword, ure_password, uaddress ;
    Button sign_up, sign_in;
    DBHandle DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);

        uf_name = findViewById(R.id.f_name);
        ul_name = findViewById(R.id.l_name);
        uemail = findViewById(R.id.email);
        um_number = findViewById(R.id.m_number);
        upassword = findViewById(R.id.password);
        ure_password = findViewById(R.id.repassword);
        uaddress = findViewById(R.id.address);
        sign_up = findViewById(R.id.btnsignup);
        sign_in = findViewById(R.id.btnlogsignin);
        DB = new DBHandle(this);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uf_name.getText().toString().equals("") || ul_name.getText().toString().equals("") || uemail.getText().toString().equals("") || um_number.getText().toString().equals("") || upassword.getText().toString().equals("") || ure_password.getText().toString().equals("") || uaddress.getText().toString().equals("") ){
                    Toast.makeText(CustomerSignup.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBHandle dbHelper = new DBHandle(getApplicationContext());
                    dbHelper.insertData(uf_name.getText().toString(),
                            ul_name.getText().toString(),
                            uemail.getText().toString(),
                            um_number.getText().toString(),
                            upassword.getText().toString(),
                            ure_password.getText().toString(),
                            uaddress.getText().toString());
//

                    if (upassword.getText().toString().equals(ure_password.getText().toString())){
                        Boolean usercheck = DB.checkUseremail(uemail.getText().toString());
                        if (usercheck == false){
//                            CustomerDetails.Customer newRowID = new CustomerDetails.Customer(f_name,l_name,
//                                    email,m_number,work_area,
//                                    password,re_password);
                            boolean insert = DB.insertData(uf_name.getText().toString(), ul_name.getText().toString() ,uemail.getText().toString(), um_number.getText().toString(), upassword.getText().toString() ,ure_password.getText().toString(), uaddress.getText().toString());
                            if (insert == false){

                                Toast.makeText(CustomerSignup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), CustomerProfile.class);
                                startActivity(intent);
                            }else
                                Toast.makeText(CustomerSignup.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(CustomerSignup.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CustomerProfile.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(CustomerSignup.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),CustomerLogin.class);
                startActivity(intent);
            }
        });
    }
}