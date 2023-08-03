package com.example.ecclesia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private  static final String DB_NAME = "Prayer.db";
    private static  final String DB_TABLE = "Prayer_Table";


    private static final String ID = "ID";
    private static final String NAME = "NAME PRAYER";
    private static final String CONTENT = "CONTENT PRAYER";

    private static final String CREATE_TABLE = "CREATE TABLE "
            + DB_TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT, "
            + CONTENT + " TEXT "+")";

    public DBHelper(Context context) {
        super(context, DB_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
    onCreate(db);
    }
    public boolean insertData(String name, String con) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(CONTENT,con);

        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }
    public Cursor viewDate(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery (query,null);

        return cursor;

    }
}
