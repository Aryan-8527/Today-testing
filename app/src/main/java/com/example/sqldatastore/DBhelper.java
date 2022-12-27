package com.example.sqldatastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Usertable";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_IMAGE = "image";

    private static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME + "(id integer primary key autoincrement, "
            + COLUMN_TITLE + " text NOT NULL UNIQUE," + COLUMN_DETAILS + " text,"+ COLUMN_IMAGE + " text)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String TRUNCATE_TABLE = "DELETE FROM " + TABLE_NAME ;

    public DBhelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
          db.execSQL(DROP_TABLE);
          onCreate(db);
    }

    public long saveDetailsTldb(SQLiteDatabase database,String title , String details,String image) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE , title);
        cv.put(COLUMN_DETAILS , details);
        cv.put(COLUMN_IMAGE , image);
        long id = database.insert(TABLE_NAME, null, cv);
        return id;
    }
    public void deleteFLdb(SQLiteDatabase database){
        database.execSQL(TRUNCATE_TABLE);
    }
    public  List<Model_class> getAllData(SQLiteDatabase db){
        List<Model_class> list = new ArrayList<Model_class>();

        // Select All Query
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model_class mc = new Model_class(cursor.getString(1),cursor.getString(2), cursor.getString(3));
                list.add(mc);
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
//        db.close();
        // returning lables
        return list;
    }
}
