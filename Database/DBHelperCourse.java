package com.example.attendancemanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelperCourse extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Courses.db";
    public static final String TABLE_NAME = "Courses";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "COURSECODE";
    public static final String COL_3 = "DESCRIPTION";


    public DBHelperCourse(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String COURSECODE, String DESCRIPTION)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,COURSECODE);
        contentValues.put(COL_3,DESCRIPTION);
        long result = db.insert(TABLE_NAME,null, contentValues);
        db.close();
        if (result == -1) { return false;   }
        else { return true; }
    }

    public long getcount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }

    public ArrayList<Course> getAllData()
    {
        ArrayList<Course> CourseArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (res.moveToFirst()) {
            do {
                CourseArrayList.add(new Course(Integer.toString(res.getInt(0)),
                        res.getString(1),
                        res.getString(2)));
            } while (res.moveToNext());
        }
        db.close();
        return CourseArrayList;
    }
    public boolean updateData(String ID, String COURSECODE, String DESCRIPTION) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,COURSECODE);
        contentValues.put(COL_3,DESCRIPTION);
        long result = db.update(TABLE_NAME, contentValues, "ID=?",new String[] { ID });
        db.close();
        if (result == -1) { return false; }
        else { return true; }
    }

    public Course getById(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        Course data = new Course("","","");
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + "=" + ID  +"", null);
        if (res.moveToFirst()) {
            do {
                data.setId(Integer.toString(res.getInt(0)));
                data.setCourseCode(res.getString(1));
                data.setDescription(res.getString(2));
            } while (res.moveToNext());
        }
        db.close();
        return data;
    }

    public boolean deleteData (String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result =db.delete(TABLE_NAME, "ID=?",new String[] { ID });
        db.close();
        if (result == -1) { return false; }
        else { return true; }
    }
}

