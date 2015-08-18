package com.example.yamauchi.fruitsorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yamauchi on 2015/08/18.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String TABLENAME = "TestTable"; // テーブル名

    public DBHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "CREATE TABLE IF NOT EXISTS " + TABLENAME
                + "(id integer primary key autoincrement,"
                + "name text not null,"
                + "address text not null,"
                + "gendar text not null,"
                + "apple text default 0,"
                + "orange text defalt 0,"
                + "peach text defaault 0)";

        db.execSQL(s); // テーブル作成

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String s = "DROP TABLE IF EXISTS " + TABLENAME;
        db.execSQL(s);
    }
}
