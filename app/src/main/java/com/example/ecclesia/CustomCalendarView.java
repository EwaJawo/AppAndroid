package com.example.ecclesia;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CustomCalendarView extends LinearLayout
{
    ImageButton NextButton, PastButton;
    TextView CurrentDate;
    GridView gridView;
    private static  final int MMAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.forLanguageTag ("pl-PL"));
    Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat ("MMMM yyyy", Locale.forLanguageTag ("pl-PL" ));
    SimpleDateFormat monthFormat = new SimpleDateFormat ("MMMM", Locale.forLanguageTag ("pl-PL" ));
    SimpleDateFormat  yearFormate = new SimpleDateFormat ("yyyy", Locale.forLanguageTag ("pl-PL" ));

    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();

    public CustomCalendarView(Context context)
    {
        super ( context );
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs)
    {
        super ( context, attrs );
        this.context = context;
        IntializeLayout();
        SetUpCalendar ();

        PastButton.setOnClickListener ( new OnClickListener () {
            @Override
            public void onClick(View view)
            {
                calendar.add(Calendar.MONTH,-1  );
                SetUpCalendar();
            }
        } );
        NextButton.setOnClickListener ( new OnClickListener () {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,1);
                SetUpCalendar ();
            }
        } );
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
       String currentDate = dateFormat.format(calendar.getTime ());
       CurrentDate.setText(currentDate);
    }

}
