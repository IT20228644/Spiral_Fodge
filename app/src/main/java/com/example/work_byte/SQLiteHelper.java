<<<<<<< HEAD
package com.example.work_byte;
=======
package com.example.imagecrud;
>>>>>>> 6d94a05752b2b8345db5db48b451134849738bed

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {


    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

<<<<<<< HEAD
    public void insertData(String name, byte[] image){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO WORK VALUES (NULL, ?, ?)";
=======
    public void insertData(String itemname, String discription, byte[] image, String phone, String price){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?, ?, ?)";
>>>>>>> 6d94a05752b2b8345db5db48b451134849738bed

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

<<<<<<< HEAD
        statement.bindString(1, name);
        statement.bindBlob(2, image);
=======
        statement.bindString(1, itemname);
        statement.bindString(2, discription);
        statement.bindBlob(3, image);
        statement.bindString(4, phone);
        statement.bindString(5, price);

>>>>>>> 6d94a05752b2b8345db5db48b451134849738bed

        statement.executeInsert();

    }

<<<<<<< HEAD
=======
    //Updating data into sqlite
    public void updateData(String itemname, String discription, byte[] image, String phone, String price, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE FOOD SET name = ?, discription = ?, image = ?, phone = ?,price = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, itemname);
        statement.bindString(2, discription);
        statement.bindBlob(3, image);
        statement.bindString(4, phone);
        statement.bindString(5, price);
        statement.bindDouble(6, (double)id);

        statement.execute();
        database.close();
    }


    //Deleting data in sqlite with id field
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM FOOD WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }


>>>>>>> 6d94a05752b2b8345db5db48b451134849738bed
    //get all data from database
    public Cursor getData(String sql){

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
