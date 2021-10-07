package com.example.work_byte;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.work_byte.Database.DBHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class EditWorkProfileActivity extends AppCompatActivity {

    ImageView propic;
    EditText e_mail, m_number, work_area, password, re_password, address, experience;
    Button  save;
    ImageButton edit_propic;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private int RUSULT_OK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_work_profile);


        e_mail = findViewById(R.id.emailsearch);
        m_number = findViewById(R.id.tele_edit);
        address = findViewById(R.id.address_edit);
        work_area = findViewById(R.id.work_areaedit);
        experience = findViewById(R.id.experience_edit);
        password = findViewById(R.id.passwrdedit);
        re_password = findViewById(R.id.retypepasswordedit);
        propic = findViewById(R.id.propicedit);
        edit_propic = findViewById(R.id.btn_edit_propic);
        save = findViewById(R.id.btn_save);




<<<<<<< HEAD
                if(user.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No user", Toast.LENGTH_SHORT).show();
                    e_mail.setText(null);
                }
                else{
                    Toast.makeText(getApplicationContext(), "CustomerDetails Found ! " , Toast.LENGTH_SHORT).show();

                    e_mail.setText(user.get(0).toString());
                    m_number.setText(user.get(3).toString());
                    address.setText(user.get(4).toString());
                    work_area.setText(user.get(1).toString());
                    experience.setText(user.get(2).toString());
                    password.setText(user.get(5).toString());
                    re_password.setText(user.get(6).toString());

                }
            }
        });

=======
>>>>>>> abd4ac3ef9820429b6acd18ee361e646a8192706
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                Boolean status = dbHelper.updateInfo(e_mail.getText().toString(), work_area.getText().toString(), m_number.getText().toString(), password.getText().toString(), re_password.getText().toString(), address.getText().toString(), experience.getText().toString());

                if (status)
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Update Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

        edit_propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //Show popupp for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);

                    }else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }else{
                    //system os is less than marshmellow
                    pickImageFromGallery();
                }
            }
        });


    }

    private void pickImageFromGallery() {
        //pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(getApplicationContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    //handle results of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            //set image to image view
//            propic.setImageURI(data.getData());
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                propic.setImageBitmap(bitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Nothing Selected.", Toast.LENGTH_SHORT).show();
    }
}
//resultCode == RUSULT_OK && requestCode == IMAGE_PICK_CODE