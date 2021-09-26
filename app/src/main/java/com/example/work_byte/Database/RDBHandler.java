package com.example.work_byte.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RDBHandler extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String DB_NAME="Review";
    public  static final String TABLE_NAME="WorkerRate";
    
    //column names
    public static final String WORKER_NAME="worker_name";
    public static final String WORKER_RATING="worker_rating";


    public RDBHandler(@Nullable Context context) { super(context, DB_NAME, null,VERSION);
    
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY="CREATE TABLE " +TABLE_NAME+" "+
                "("
                +WORKER_NAME+"STRING PRIMARY KEY "+"AUTOINCREMENT,"
                +WORKER_RATING+"TEXT"+
                ")";
        
        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //when change the version drop exists table
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //drop table if exited
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);

    }
        /*insert data*/
        public void addRating(RatingHandle ratingHandle){

            SQLiteDatabase sqLiteDatabase=getWritableDatabase();
            
            ContentValues contentValues=new ContentValues();
            
            contentValues.put(WORKER_NAME,WORKER_RATING);
            
            sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
            
            sqLiteDatabase.close();
            
            }
        

    }

