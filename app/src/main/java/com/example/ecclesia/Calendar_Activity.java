package com.example.ecclesia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Calendar_Activity extends AppCompatActivity {

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView (R.layout.activity_calendar);

        customCalendarView = findViewById(R.id.custom_calendar_view);
    }
}