package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work_byte.Database.DBHandle;

import java.util.List;

public class EditCustomerProfile extends AppCompatActivity {

    EditText e_mail, m_number, password, re_password, address;
    Button save, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_profile);

        e_mail = findViewById(R.id.emailsearch);
        m_number = findViewById(R.id.tele_edit);
        address = findViewById(R.id.address_edit);
        password = findViewById(R.id.passwrdedit);
        re_password = findViewById(R.id.retypepasswordedit);
        save = findViewById(R.id.btn_save);
        search = findViewById(R.id.btn_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandle dbHelper = new DBHandle(EditCustomerProfile.this);

                List user = dbHelper.readAllInfo(e_mail.getText().toString());


                if(user.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No user", Toast.LENGTH_SHORT).show();
                    e_mail.setText(null);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Customer Found ! " , Toast.LENGTH_SHORT).show();

                    e_mail.setText(user.get(0).toString());
                    m_number.setText(user.get(3).toString());
                    address.setText(user.get(4).toString());
                    password.setText(user.get(5).toString());
                    re_password.setText(user.get(6).toString());

                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandle dbHelper = new DBHandle(getApplicationContext());

                Boolean status = dbHelper.updateInfo(e_mail.getText().toString(), m_number.getText().toString(), password.getText().toString(), re_password.getText().toString(), address.getText().toString());

                if (status)
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Update Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}