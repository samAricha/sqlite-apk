package com.example.sqliteappv10;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="students_tbl";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="SURNAME";
    public static final String COL_4="MARKS";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT ,SURNAME TEXT, MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

     public boolean insertData(String NAME, String SURNAME, String MARKS){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,SURNAME);
        contentValues.put(COL_4,MARKS);

       long result= db.insert(TABLE_NAME, null, contentValues);

       if(result==-1)
           return false;
       else
           return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }

    public boolean updataData(String ID,String NAME, String SURNAME, String MARKS){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,SURNAME);
        contentValues.put(COL_4,MARKS);
        db.update(TABLE_NAME , contentValues ,"ID=?" , new String[] {ID} );
        return true;
    }

    public Integer deleteData(String ID){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID=?", new  String[] {ID});

    }
}
