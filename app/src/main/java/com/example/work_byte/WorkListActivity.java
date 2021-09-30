package com.example.work_byte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.work_byte.Database.SQLiteMyWorkHandler;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class WorkListActivity extends AppCompatActivity {


    private static SQLiteMyWorkHandler sqLiteMyWorkHandler;
    GridView gridView;
    ArrayList<Work> list;
    WorkListAdapter adapter = null;
    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);


        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new WorkListAdapter(this, R.layout.activity_my_item, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = EditMyWorkActivity.sqLiteMyWorkHandler.getData("SELECT * FROM WORK");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new Work(name, image, id));
        }
        adapter.notifyDataSetChanged();



        /////on long click


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(WorkListActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = EditMyWorkActivity.sqLiteMyWorkHandler.getData("SELECT id FROM WORK");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(WorkListActivity.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = EditMyWorkActivity.sqLiteMyWorkHandler.getData("SELECT id FROM WORK");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
//                            Toast.makeText(getApplicationContext(),"Delete..", Toast.LENGTH_SHORT);
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    //
    ImageView imageViewItem;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_myworks);
        dialog.setTitle("Update");

        imageViewItem = (ImageView) dialog.findViewById(R.id.imageViewItem);
        final EditText edItemName = (EditText) dialog.findViewById(R.id.edItemName);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        WorkListActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditMyWorkActivity.sqLiteMyWorkHandler.updateData(
                            edItemName.getText().toString().trim(),
                            EditMyWorkActivity.imageViewToByte(imageViewItem),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateItemList();
            }
        });
    }

    private void showDialogDelete(final int idItem){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(WorkListActivity.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to delete this work from your work list?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    EditMyWorkActivity.sqLiteMyWorkHandler.deleteData(idItem);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateItemList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateItemList(){
        // get all data from sqlite
        Cursor cursor = EditMyWorkActivity.sqLiteMyWorkHandler.getData("SELECT * FROM WORK");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String itemname = cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new Work(itemname, image, id));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewItem.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}