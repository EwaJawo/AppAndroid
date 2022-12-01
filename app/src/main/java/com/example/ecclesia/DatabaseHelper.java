package com.example.ecclesia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "NOTES";

    public static final String _ID  = "_id";
    public static final String SUBJECT  = "subject";
    public static final String CONTENTS = "contents";

    static final String DB_NAME = "NOTE_TABLE_NAME";

    static  final int DB_VERSION = 1;

    private static  final String CREATE_TABLE = "create table " +
            TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT +
            " TEXT NOT NULL, " + CONTENTS + " TEXT);";

    public  DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void  onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }




}
