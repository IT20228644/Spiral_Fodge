package com.example.work_byte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.work_byte.Database.DbHandler;
import com.example.work_byte.Database.WorkerModel;

public class AddWorker extends AppCompatActivity {

    public static final String EXTRA_MESSAGE2="com.example.mad_mobileapp";
    private EditText name,salary,category,wId;
    private Button btn1;
    private DbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        name=findViewById(R.id.addWorker_name);
        salary=findViewById(R.id.salary);
        category=findViewById(R.id.addWorker_category2);
        wId=findViewById(R.id.id);
        btn1=findViewById(R.id.add);
//        btn2=findViewById(R.id.view);
//        btn3=findViewById(R.id.update);
//        btn4=findViewById(R.id.viewOne);
        context=this;
        dbHandler=new DbHandler(context);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //store values when user insert. use .toString to convert title type object into string
                //int userId=Integer.parseInt(wId.getText().toString());
                String userName=name.getText().toString();
                int userSalary=Integer.parseInt(salary.getText().toString());
                String userCat=category.getText().toString();



//                //store the time that inserttodo to the db
//                /long started=System.currentTimeMillis();

                //create model type object
                WorkerModel workerModel=new WorkerModel(userName,userSalary,userCat);

                dbHandler.addWorkerDetails(workerModel);

                //again redirect to the main activity
                Intent intent=new Intent(context,wor_profileNew.class);
//                intent.putExtra("id",String.valueOf(workerModel.getWorkerId()));
                intent.putExtra(EXTRA_MESSAGE2,String.valueOf(userSalary));
//                System.out.println();
                startActivity(intent);

            }
        });

    }
}