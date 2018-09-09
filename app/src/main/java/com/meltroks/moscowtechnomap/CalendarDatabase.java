package com.meltroks.moscowtechnomap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CalendarDatabase {
    SQLiteDatabase calendardb;

    public CalendarDatabase(Context context){
        CalendarDatabaseUnit openHelper = new CalendarDatabaseUnit(context);
        this.calendardb = openHelper.getReadableDatabase();
    }

    public void insert(String name, int date){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("date", date);
        calendardb.insert("CalendarDatabseUnit", null, cv);
    }

    public String getListUnits(){
        String result = "";
        Cursor cursor = calendardb.query("CalendarDatabaseUnit",null,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            result+=cursor.getString(1) + "  " + cursor.getString(2) + "\n";
            cursor.moveToNext();
        }
        return result;
    }
}
