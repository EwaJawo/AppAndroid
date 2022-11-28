package com.example.ecclesia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotebookDbAdapter {
    public static final String COL_ID = "_id";
    public static final String COL_CONTENT = "content";

    public static final int INDEX_ID = 0;
    public static final int INDEX_CONTENT = INDEX_ID + 1;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "dba_notes";
    private static final String TABLE_NAME = "tab_notes";
    public static  final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_NAME + " ( " +
                    COL_ID + "  INTEGER PRIMARY KEY autoincrement, " +
                    COL_CONTENT + " TEXT );";

    public NotebookDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }
    public  void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public void createNote(String name) {
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, name);
        mDb.insert(TABLE_NAME, null, values);
    }
    public long createNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, note.getContent());
        return mDb.insert(TABLE_NAME, null, values);
    }
    public  Note fetchNoteById (int id) {
        Cursor cursor = mDb.query (TABLE_NAME, new String[]{COL_ID, COL_CONTENT},COL_ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return new Note(
                cursor.getInt(INDEX_ID),
                cursor.getString(INDEX_CONTENT)
        );
    }

    public Cursor fetchAllNotes() {
        Cursor mCursor = mDb.query(TABLE_NAME, new String[]{COL_ID,  COL_CONTENT}, null, null, null, null,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public  void updateNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, note.getContent());
        mDb.update(TABLE_NAME, values,
                COL_ID + "=?", new String[]{String.valueOf(note.getId())});
    }
    public  void deleteNoteById(int nId) {
        mDb.delete (TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(nId )});
    }
    public  void deleteAllNote(){
        mDb.delete(TABLE_NAME, null,null);
    }


private static class DatabaseHelper extends SQLiteOpenHelper {
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }
    @Override
    public  void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
}
