package com.example.ecclesia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DBHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        databaseHelper = new DBHelper ( context );
        database = databaseHelper.getWritableDatabase ();
        return this;
    }

    public void close() {
        databaseHelper.close ();
    }

    public void place (String sub, String desc) {
        ContentValues contentValues = new ContentValues ();
        contentValues.put (DBHelper.TITLE, sub );
        contentValues.put (DBHelper.PRA, desc );
        database.insert (DBHelper.TABLE_NAME, null, contentValues );
    }

    public Cursor fetch() {
        String[] columns = new String[]{DBHelper._ID,
                DBHelper.TITLE, DBHelper.PRA};

        Cursor cursor = database.query (DBHelper.TABLE_NAME,
                columns, null, null, null, null, null );

        if (cursor != null) {
            cursor.moveToNext ();
        }
        return cursor;
    }
    public int update(long _id, String sub, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TITLE, sub);
        contentValues.put(DBHelper.PRA, desc);

        int i = database.update(DBHelper.TABLE_NAME,
                contentValues, DBHelper._ID +
                        " = " + _id, null);
        return i;
    }
    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME,
                DBHelper._ID + " = " + _id, null);
    }

}
