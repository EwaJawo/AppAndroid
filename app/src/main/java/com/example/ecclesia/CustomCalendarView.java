package com.example.ecclesia;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CustomCalendarView extends LinearLayout
{
    ImageButton NextButton, PastButton;
    TextView CurrentDate;
    GridView gridView;
    private static  final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.forLanguageTag ("pl-PL"));
    Context context;

    SimpleDateFormat dateFormat = new SimpleDateFormat ("LLLL yyyy", Locale.forLanguageTag ("pl-PL" ));
    SimpleDateFormat monthFormat = new SimpleDateFormat ("LLLL", Locale.forLanguageTag ("pl-PL" ));
    SimpleDateFormat  yearFormate = new SimpleDateFormat ("yyyy", Locale.forLanguageTag ("pl-PL" ));
    SimpleDateFormat eventDateFormate = new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag ( "pl-PL"));

    MyGridAdapter myGridAdapter;
    AlertDialog alertDialog;

    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();
    DBOpenHelper dbOpenHelper;

    public CustomCalendarView(Context context)
    {
        super ( context );
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) {
        super ( context, attrs );
        this.context = context;
        IntializeLayout ();
        SetUpCalendar ();

        PastButton.setOnClickListener ( new OnClickListener () {
            @Override
            public void onClick(View view) {
                calendar.add ( Calendar.MONTH, -1 );
                SetUpCalendar ();
            }
        } );
        NextButton.setOnClickListener ( new OnClickListener () {
            @Override
            public void onClick(View view) {
                calendar.add ( Calendar.MONTH, 1 );
                SetUpCalendar ();
            }
        } );

        gridView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder ( context );
                builder.setCancelable ( true );
                final View addView = LayoutInflater.from ( adapterView.getContext () ).inflate ( R.layout.add_new_event, null );
                final EditText EventName = addView.findViewById ( R.id.eventsId );
                final TextView EventTime = addView.findViewById ( R.id.eventTime );
                ImageButton SetTime = addView.findViewById ( R.id.time );
                Button AddEvent = addView.findViewById ( R.id.addEvent );
                SetTime.setOnClickListener ( new OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance ( Locale.forLanguageTag ( "pl-PL" ) );
                        final int hours = calendar.get ( Calendar.HOUR_OF_DAY );
                        final int minuts = calendar.get ( Calendar.MINUTE );
                        TimePickerDialog timePickerDialog = new TimePickerDialog ( addView.getContext (), R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener () {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                Calendar c = Calendar.getInstance ( Locale.forLanguageTag ( "pl-PL" ) );
                                c.set ( Calendar.HOUR_OF_DAY, i );
                                c.set ( Calendar.MINUTE, i1 );
                                c.setTimeZone ( TimeZone.getDefault () );
                                SimpleDateFormat hformate = new SimpleDateFormat ( "K:mm a", Locale.forLanguageTag ( "pl-PL" ) );
                                String event_Time = hformate.format ( c.getTime () );
                                EventTime.setText ( event_Time );
                            }
                        }, hours, minuts, false );
                        timePickerDialog.show ();
                    }
                } );
                final String date = eventDateFormate.format (dates.get (i));
                final String month = monthFormat.format (dates.get (i));
                final String year = yearFormate.format (dates.get (i));

                AddEvent.setOnClickListener ( new OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        SaveEvent (EventName.getText ().toString (), EventTime.getText ().toString (), date, month, year );
                        SetUpCalendar ();
                        alertDialog.dismiss ();
                    }
                } );

                builder.setView ( addView );
                alertDialog = builder.create ();
                alertDialog.show ();
            }
        } );


        gridView.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String date = eventDateFormate.format( dates.get(i) );
                AlertDialog.Builder builder = new AlertDialog.Builder(context );
                builder.setCancelable (true);
                View showView = LayoutInflater.from (adapterView.getContext()).inflate(R.layout.display_events,null );
                RecyclerView recyclerView = showView.findViewById(R.id.EventsNumber);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager ( showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter (showView.getContext(),CollectEventByDate(date));
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView (showView);
                alertDialog = builder.create ();
                alertDialog.show ();
                return true;
            }
        } );
    }




    private ArrayList<Events> CollectEventByDate(String date)
    {
        ArrayList<Events> arrayList = new ArrayList<>();
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.ReadEvents(date,sqLiteDatabase);
        while (cursor.moveToNext())
        {
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            String time = cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
            String Date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            String Year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
            Events events = new Events(event,time,Date,month,Year);
            arrayList.add(events);
        }
        cursor.close();
       dbOpenHelper.close();
        return arrayList;
    }
          public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
        {
            super ( context, attrs, defStyleAttr );
        }



    private void IntializeLayout()
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar,this);
        NextButton = view.findViewById(R.id.nextBtn);
        PastButton = view.findViewById( R.id. pastBtn);
        CurrentDate = view.findViewById(R.id.currentDate);
        gridView = view.findViewById(R.id.gridView);
    }

    private void SetUpCalendar ()
    {
        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);
        dates.clear();
        Calendar monthCalendar= (Calendar) calendar.clone();
        monthCalendar.set (Calendar.DAY_OF_MONTH,1);
        int FirstDayofMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, - FirstDayofMonth);

        CollectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormate.format(calendar.getTime()));

        while (dates.size() < MAX_CALENDAR_DAYS)
        {
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1 );
        }
        myGridAdapter = new MyGridAdapter(context,dates,calendar,eventsList);
        gridView.setAdapter(myGridAdapter);
    }



    private void SaveEvent (String event, String time, String date, String month, String year)
    {
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase sqLiteDatabase=  dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,time,date,month,year,sqLiteDatabase);
        dbOpenHelper.close();
        Toast.makeText ( context, "Zapisano", Toast.LENGTH_SHORT ).show ();
    }


    private  void  CollectEventsPerMonth(String Month, String year)
         {
             eventsList.clear();
            dbOpenHelper = new DBOpenHelper(context);
            SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
             Cursor cursor = dbOpenHelper.ReadEventsPerMonth (Month, year, sqLiteDatabase);
             while (cursor.moveToNext())
             {
                 String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
                 String time = cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
                 String date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
                 String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
                 String Year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
                 Events events = new Events(event,time,date,month,Year);
                 eventsList.add(events);
             }
             cursor.close();
             dbOpenHelper.close();
         }

}
