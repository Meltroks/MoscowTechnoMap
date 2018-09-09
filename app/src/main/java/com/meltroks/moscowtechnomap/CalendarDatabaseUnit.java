package com.meltroks.moscowtechnomap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CalendarDatabaseUnit extends SQLiteOpenHelper{
    public CalendarDatabaseUnit(Context context) {
        super(context, "test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists CalendarDatabaseUnit(\n" +
                "id integer primary key autoincrement not null,\n" +
                "name varchar,\n" +
                "date integer\n" +
                ")");
        db.execSQL("insert into CalendarUnit(name, date) values (\"RAVE\", 100);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase CalendarDatabaseUnit, int i, int i1) {}
}
