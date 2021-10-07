package com.example.work_byte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work_byte.Database.DBHandle;

public class ConfirmCustomerDelete extends AppCompatActivity {

    Button deleteAcc, cancel;
    EditText delEmail;
    DBHandle dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_customer_delete);

        deleteAcc = findViewById(R.id.btn_deleteAcc_confirm);
        cancel = findViewById(R.id.btn_cancel);
        delEmail = findViewById(R.id.delete_request_email);
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDelete=dbHelper.deleteCustomer(delEmail.getText().toString());
                if (isDelete==true)
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "not deleted", Toast.LENGTH_SHORT).show();
                warnpopup();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    //
    public void deleteAcct() {

        String delmail = delEmail.getText().toString();
//        String delemail = delEmail.getText().toString();
        if (delmail.isEmpty())
            Toast.makeText(getApplicationContext(), "Please Enter your email to delete the account", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(getApplicationContext(), "Deletion Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), CustomerLogin.class);
            startActivity(intent);
            Boolean cursor = dbHelper.deleteCustomer(delmail);
            if (cursor = true)
                Toast.makeText(getApplicationContext(), "Deletion Successful", Toast.LENGTH_SHORT).show();
            else{
                Toast.makeText(getApplicationContext(), "Deletion Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public  void warnpopup(){
        AlertDialog dialog = new AlertDialog.Builder(ConfirmCustomerDelete.this)
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setTitle("Warning!")
                .setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", null)
                .setNegativeButton("No", null)
                .show();
        Button positivebutton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteAcct();
            }
        });
        Button negativebutton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}