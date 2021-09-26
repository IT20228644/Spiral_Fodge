package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class WorkListActivity extends AppCompatActivity {


    GridView gridView;
    ArrayList<Work> list;
    WorkListAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);


        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new WorkListAdapter(this, R.layout.activity_my_item, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = EditMyWorkActivity.sqLiteHelper.getData("SELECT * FROM WORK");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new Work(name, image, id));
        }
        adapter.notifyDataSetChanged();

    }
}