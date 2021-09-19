package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work_byte.Database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button signin, signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.loginemailt);
        password = (EditText) findViewById(R.id.loginpasswordt);

        signin = (Button) findViewById(R.id.btnsignin);
        signup = (Button) findViewById(R.id.btnlogsignin);

        DB = new DBHelper(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginemail = email.getText().toString();
                String pword = password.getText().toString();


                if (loginemail.equals("") || pword.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = DB.checkEmailPassword(loginemail,pword);
                    if (checkUserPass == true){
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


    }
}