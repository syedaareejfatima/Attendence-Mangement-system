package com.example.attendancemanager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelperStudent extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Attendance.db";
    public static final String TABLE_NAME = "Students";
    public static final String COL_1 = "ROLLNUMBER";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SECTIONID";
    public static final String COL_4 = "CIDS";

    public DBHelperStudent(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_1 + " TEXT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String ROLLNUMBER, String NAME, String SECTIONID, String CIDS)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ROLLNUMBER);
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,SECTIONID);
        contentValues.put(COL_4,CIDS);

        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result == -1) { return false; }
        else { return true; }
    }

    public ArrayList<Student> getAllData()
    {
        ArrayList<Student> StudentArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (res.moveToFirst()) {
            do {
                StudentArrayList.add(new Student(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4)));
            } while (res.moveToNext());
        }
        return StudentArrayList;
    }
    public boolean updateData(String ROLLNUMBER, String NAME, String SECTIONID, String CIDS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,SECTIONID);
        contentValues.put(COL_4,CIDS);
        long result =  db.update(TABLE_NAME, contentValues, "ROLLNUMBER=?",new String[] { ROLLNUMBER });
        if (result == -1) { return false; }
        else { return true; }
    }

    public boolean deleteData (String ROLLNUMBER) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result =  db.delete(TABLE_NAME, "ROLLNUMBER=?",new String[] {  ROLLNUMBER  });
        if (result == -1) { return false; }
        else { return true; }
    }
}

