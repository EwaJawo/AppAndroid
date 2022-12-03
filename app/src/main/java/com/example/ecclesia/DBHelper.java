package com.example.ecclesia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "PRAYER";

    public static final String _ID  = "_id";
    public static final String TITLE  = "title";
    public static final String PRA = "text";

    static final String DB_NAME = "PRAYER_DB";

    static  final int DB_VERSION = 1;

    private static  final String CREATE_PRAYER_TABLE = "create table " +
            TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE +
            " TEXT NOT NULL, " + PRA + " TEXT);";

    public  DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void  onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRAYER_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
