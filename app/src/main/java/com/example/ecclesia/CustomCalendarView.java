package com.example.ecclesia;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CustomCalendarView extends LinearLayout
{
    ImageButton nextBtn, pastBtn;
    TextureView CurrentDate;
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
    }
}
