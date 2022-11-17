package com.example.ecclesia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper  extends SQLiteOpenHelper
{
    private static final String CREATE_EVENTS_TABLE = "create table "+DBStructure.EVENT_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            +DBStructure.EVENT+" TEXT, "+DBStructure.TIME+" TEXT, "+DBStructure.DATE+" TEXT, "+DBStructure.MONTH+" TEXT, "+DBStructure.YEAR+" TEXT, "+DBStructure.Notify+" TEXT)";

    private static final String DROP_EVENTS_TABLE= "DROP TABLE IF EXISTS "+DBStructure.EVENT_TABLE_NAME;

    public DBOpenHelper(@Nullable Context context)
    {
        super (context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(DROP_EVENTS_TABLE);
        onCreate(sqLiteDatabase);
    }
    public void SaveEvent(String event, String time, String date, String month, String year,String notify, SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.EVENT,event);
        contentValues.put(DBStructure.TIME,time);
        contentValues.put(DBStructure.DATE,date);
        contentValues.put(DBStructure.MONTH,month);
        contentValues.put(DBStructure.YEAR,year);
        contentValues.put(DBStructure.Notify,notify);

        sqLiteDatabase.insert(DBStructure.EVENT_TABLE_NAME, null, contentValues);
    }

    public Cursor ReadEvents(String date, SQLiteDatabase sqLiteDatabase)
    {
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.DATE +"=?";
        String [] SelectionArgs = {date};

        return  sqLiteDatabase.query (DBStructure.EVENT_TABLE_NAME, Projections,Selection, SelectionArgs,null,null, null);
    }

    public Cursor ReadIDEvents(String date, String event, String time ,SQLiteDatabase sqLiteDatabase)
    {
        String [] Projections = {DBStructure.ID,DBStructure.Notify,DBStructure.TIME};
        String Selection = DBStructure.DATE +"=? and "+DBStructure.EVENT +"=? and "+DBStructure.TIME +"=?";
        String [] SelectionArgs = {date,event,time};

        return  sqLiteDatabase.query (DBStructure.EVENT_TABLE_NAME, Projections,Selection, SelectionArgs,null,null, null);
    }


    public Cursor ReadEventsPerMonth(String month, String year, SQLiteDatabase sqLiteDatabase)
    {
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.MONTH +"=? and "+ DBStructure.YEAR+"=?" ;
        String [] SelectionArgs = {month,year};

        return  sqLiteDatabase.query (DBStructure.EVENT_TABLE_NAME, Projections,Selection, SelectionArgs,null,null, null);
    }
    public  void deleteEvent(String event, String date, String time, SQLiteDatabase sqLiteDatabase)
    {
        String selection = DBStructure.EVENT+"=? and "+DBStructure.DATE+"=? and "+DBStructure.TIME+"=?";
        String[] selectionArg = {event,date,time};
        sqLiteDatabase.delete(DBStructure.EVENT_TABLE_NAME,selection,selectionArg);
    }


    public  void updateEvent(String date, String event, String time, String notify, SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Notify,notify);
        String Selection = DBStructure.DATE +"=? and "+DBStructure.EVENT +"=? and "+DBStructure.TIME +"=?";
        String [] SelectionArgs = {date,event,time};
        sqLiteDatabase.update(DBStructure.EVENT_TABLE_NAME, contentValues,Selection, SelectionArgs);
    }


}

