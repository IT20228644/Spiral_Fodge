package com.example.work_byte.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "work_byte.db";

    public DBHelper(Context context) {
        super(context, "work_byte.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =

            "CREATE TABLE " + UserDetails.User.TABLE_NAME + "("
                    + UserDetails.User.first_name + "," + UserDetails.User.last_name + " ,"
                    + UserDetails.User.email + "  PRIMARY KEY," + UserDetails.User.mobile + " ,"
                    + UserDetails.User.workArea + "," + UserDetails.User.password + ","
                    + UserDetails.User.re_password + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserDetails.User.TABLE_NAME;

    public boolean insertData(String f_name, String l_name, String email, String m_number, String work_area, String password, String re_password) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserDetails.User.first_name, f_name);
        values.put(UserDetails.User.last_name, l_name);
        values.put(UserDetails.User.email, email);
        values.put(UserDetails.User.mobile, m_number);
        values.put(UserDetails.User.workArea, work_area);
        values.put(UserDetails.User.password, password);
        values.put(UserDetails.User.re_password, re_password);


// Insert the new row, returning the primary key value of the new row
        long result = db.insert(UserDetails.User.TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }



//    public List readAll (){
//        SQLiteDatabase db = getReadableDatabase();
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                BaseColumns._ID,
//                UserDetails.User.first_name,
//                UserDetails.User.last_name,
//                UserDetails.User.email,
//                UserDetails.User.mobile,
//                UserDetails.User.workArea,
//                UserDetails.User.password,
//                UserDetails.User.re_password
//        };
//
//        // Filter results WHERE "title" = 'My Title'
//        String selection = UserDetails.User.email + " = ?";
//        String[] selectionArgs = { "Email" };
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                UserDetails.User.email + " ASC";
//
//        Cursor cursor = db.query(
//                UserDetails.User.TABLE_NAME,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                null,              // The columns for the WHERE clause
//                null,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder               // The sort order
//        );
//
//        List user = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            String email = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.email));
//            user.add(email);
//        }
//        cursor.close();
//        return user;
//    }
//
//
//    public List readAllInfo (String email){
//        SQLiteDatabase db = getReadableDatabase();
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                BaseColumns._ID,
//                UserDetails.User.first_name,
//                UserDetails.User.last_name,
//                UserDetails.User.email,
//                UserDetails.User.mobile,
//                UserDetails.User.workArea,
//                UserDetails.User.password,
//                UserDetails.User.re_password
//        };
//
//        // Filter results WHERE "title" = 'My Title'
//        String selection = UserDetails.User.email + " LIKE ";
//        String[] selectionArgs = { email };
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                UserDetails.User.email + " ASC";
//
//        Cursor cursor = db.query(
//                UserDetails.User.TABLE_NAME,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder               // The sort order
//        );
//
//        List userInfo = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.password));
//            userInfo.add(pass); //0th index
//        }
//        cursor.close();
//        return userInfo;
//    }

    public boolean checkUseremail (String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password =?", new String[] {email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return  false;
    }



}











//    public static final int VERSION=1;
//    public static final String DB_NAME="WorkByte.db";
//    public static final String users="users";
//    long result;



//
//    public DBHelper(Context context) {
//
//        super(context, "WorkByte.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase MyDB) {
////         create table sql query
//        String user_table = "CREATE TABLE " + users + "("
//                + first_name + "TEXT," + last_name + " TEXT,"
//                + email + " TEXT PRIMARY KEY," + mobile + " TEXT,"
//                + workArea + "TEXT," + password + "TEXT,"
//                + re_password + "TEXT" + ");";
////        MyDB.execSQL("create Table users (first_name TEXT, last_name TEXT, email TEXT primary key, mobile TEXT, workArea TEXT, password TEXT, re_password TEXT )");
//        MyDB.execSQL(user_table);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
//        // drop table sql query
//        String drop_user_table = "DROP TABLE IF EXISTS " + users;
//        MyDB.execSQL(drop_user_table);
//        onCreate(MyDB);
//
////        MyDB.execSQL("drop Table if exists users");
//    }
//
//    public boolean insertData(UserDetails auser){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(first_name, auser.getF_name());
//        contentValues.put(last_name, auser.getL_name());
//        contentValues.put(email,auser.getEmail());
//        contentValues.put(mobile, auser.getM_number());
//        contentValues.put(workArea, auser.getWork_area());
//        contentValues.put(password, auser.getPassword());
//        contentValues.put(re_password, auser.getRe_password());
//
//        long result = MyDB.insert(users, null, contentValues);
//        MyDB.close();
//        if (result == -1)
//            return false;
//        else
//           return true;
//    }

//    public boolean checkUseremail (String email){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[] {email});
//        if (cursor.getCount() > 0)
//            return true;
//        else
//            return false;
//    }
//
//    public boolean checkEmailPassword(String email, String password){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password =?", new String[] {email, password});
//        if (cursor.getCount() > 0)
//            return true;
//        else
//            return  false;
//    }
