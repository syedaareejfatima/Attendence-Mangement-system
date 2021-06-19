package com.example.attendancemanager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelperSection extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Attendance.db";
    public static final String TABLE_NAME = "Sections";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "BATCH";
    public static final String COL_3 = "CODE";

    public DBHelperSection(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT," + COL_3 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String BATCH, String CODE)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,BATCH);
        contentValues.put(COL_3,CODE);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result == -1) { return false; }
        else { return true; }
    }

    public ArrayList<Section> getAllData()
    {
        ArrayList<Section> SectionArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (res.moveToFirst()) {
            do {
                SectionArrayList.add(new Section(Integer.toString(res.getInt(0)),
                        res.getString(1),
                        res.getString(2)));
            } while (res.moveToNext());
        }
        return SectionArrayList;
    }
    public boolean updateData(String ID,String BATCH, String CODE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,BATCH);
        contentValues.put(COL_3,CODE);
        long result = db.update(TABLE_NAME, contentValues, "ID=?",new String[] { ID });
        if (result == -1) { return false; }
        else { return true; }
    }

    public boolean deleteData (String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "ID=?",new String[] { ID });
        if (result == -1) { return false; }
        else { return true; }
    }
}

