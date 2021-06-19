package com.example.attendancemanager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelperSession extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Session.db";
    public static final String TABLE_NAME = "Sessions";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "TIME";
    public static final String COL_4 = "COURSEID";
    public static final String COL_5 = "SIDS";

    public DBHelperSession(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String DATE, String TIME, String COURSEID, String SIDS)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,DATE);
        contentValues.put(COL_3,TIME);
        contentValues.put(COL_4,COURSEID);
        contentValues.put(COL_5,SIDS);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result == -1) { return false; }
        else { return true; }
    }

    public ArrayList<Session> getAllData()
    {
        ArrayList<Session> SessionArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (res.moveToFirst()) {
            do {
                SessionArrayList.add(new Session(Integer.toString(res.getInt(0)),
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4)));
            } while (res.moveToNext());
        }
        return SessionArrayList;
    }

    /*public boolean updateData(String ID, String DATE, String TIME, String COURSEID, String SIDS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,DATE);
        contentValues.put(COL_3,TIME);
        contentValues.put(COL_4,COURSEID);
        contentValues.put(COL_5,SIDS);
        long result = db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { ID });
        if (result == -1) { return false; }
        else { return true; }
    }*/

    public boolean deleteData (String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "ID = ?",new String[] {ID});
        if (result == -1) { return false; }
        else { return true; }
    }
}

