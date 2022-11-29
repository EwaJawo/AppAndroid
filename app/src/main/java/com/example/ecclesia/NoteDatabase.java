package com.example.ecclesia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 2;
    public static String DB_NAME = "NotesDB.db";
    public static String DB_TABLE = "NotesTable";

    public static String COLUMN_ID = "ID";
    public static String COLUMN_TITLE = "NotesTitle";
    public static String COLUMN_CONTENTS = "NotesContents";
    public static String COLUMN_DATE = "NotesDate";
    public static String COLUMN_TIME = "NotesTime";

    public NoteDatabase(@Nullable Context context) {
        super ( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DB_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_CONTENTS + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT" + ")";

        sqLiteDatabase.execSQL ( query );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i > i1)
            return;
        sqLiteDatabase.execSQL ( "DROP TABLE IF EXISTS" + DB_NAME );
        onCreate ( sqLiteDatabase );
    }

    public long AddNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put ( COLUMN_TITLE, note.getNoteTitle () );
        contentValues.put ( COLUMN_CONTENTS, note.getNoteContents () );
        contentValues.put ( COLUMN_DATE, note.getNoteDate () );
        contentValues.put ( COLUMN_TIME, note.getNoteTime () );

        long ID = db.insert ( DB_TABLE, null, contentValues );
        Log.d ( "Inserted", "id ->" + ID );
        return ID;

    }

    public List<Note> getNote() {
        SQLiteDatabase db = this.getReadableDatabase ();
        List<Note> allNote = new ArrayList<> ();

        String queryStatement = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery ( queryStatement, null );

        if (cursor.moveToFirst ()) {
            do {
                Note note = new Note ();
                note.setId ( cursor.getInt ( 0 ) );
                note.setNoteTitle ( cursor.getString ( 1 ) );
                note.setNoteContents ( cursor.getString ( 2 ) );
                note.setNoteDate ( cursor.getString ( 3 ) );
                note.setNoteTime ( cursor.getString ( 4 ) );

                allNote.add ( note );
            }while (cursor.moveToNext());
            }
        return allNote;
        }
    }

