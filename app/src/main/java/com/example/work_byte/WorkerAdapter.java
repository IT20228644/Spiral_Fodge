package com.example.work_byte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.work_byte.Database.WorkerModel;

import java.util.List;

public class WorkerAdapter extends ArrayAdapter<WorkerModel> {
    private Context context;
    private int resource;
    private List<WorkerModel> workers;

    public WorkerAdapter(Context context, int resource,List<WorkerModel> workers) {
        super(context, resource,workers);
        this.context=context;
        this.resource=resource;
        this.workers=workers;
    }


    //this method will interact with the single_todo.xml and display the relevant values from the database
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //convert single_worker.xml into java
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        //one single raw converted.output come as View object
        View row=layoutInflater.inflate(resource,parent,false);

        //linking xml file wi th java
        TextView wname = row.findViewById(R.id.rowName);
        //TextView wemail = row.findViewById(R.id.rowEmail);
        TextView wprice = row.findViewById(R.id.rowPrice);
        TextView wcategory = row.findViewById(R.id.rowCategory);

        //Button btn=row.findViewById(R.id.cancal);


        WorkerModel worker= workers.get(position);//todos[0,1,2]=>todos[index].position is auto incremented.
        wname.setText(worker.getWorkerName());
        wprice.setText(String.valueOf(worker.getSalary()));
        wcategory.setText(worker.getCategory());
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //context.startActivity(new Intent(context,profile.class));
//                Intent intent=new Intent(context,profile.class);
//                context.startActivity(intent);
//            }
//        });
        //img1.setVisibility(row.INVISIBLE);
        return row;
    }
}
