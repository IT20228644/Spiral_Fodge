package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.work_byte.Database.DbHandler;
import com.example.work_byte.Database.WorkerModel;

import java.util.ArrayList;
import java.util.List;

public class MasonList extends AppCompatActivity {

    private ListView listView1;
    private DbHandler dbHandler;
    private List<WorkerModel> workers;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mason_list);
        workers=new ArrayList<>();
        listView1=findViewById(R.id.listView);
        context=this;
        dbHandler=new DbHandler(context);

        //get all masons
        workers=dbHandler.getAllWorkers("Mason");

        //get the adapter
        WorkerAdapter adapter=new WorkerAdapter(context,R.layout.single_worker,workers);

        //set the adapter
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the relavanttodo from the database
                WorkerModel worker=workers.get(position);

                Intent i=new Intent(context,wor_profileNew.class);
                i.putExtra("worker_id",String.valueOf(worker.getWorkerId()));
                startActivity(i);

            }
        });

    }

}
