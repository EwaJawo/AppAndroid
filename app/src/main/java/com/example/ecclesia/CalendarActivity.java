package com.example.ecclesia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private TextView selectedDate;
    private CalendarView calendarView;
    private Button saveTextButton;
    private Button getSaveTextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_calendar );

        calendarView = findViewById(R.id.calendarView);
        saveTextButton = findViewById(R.id.saveTextButton);
        Toolbar toolbar = findViewById (R.id.toolbarCalendar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar ();
        actionBar.setDisplayHomeAsUpEnabled (true);

        calendarView.setOnDateChangeListener ( new CalendarView.OnDateChangeListener () {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Toast.makeText(getApplicationContext (),i2 + "/" + i1 + "/" + i, Toast.LENGTH_LONG).show ();
            }
        } );
    }
}
