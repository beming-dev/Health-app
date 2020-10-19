package org.techtown.realapp;

import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;

public class CompleteEx {
    CalendarDay date;
    int day;

    CompleteEx(CalendarDay date, int day){
        this.date = date;
        this.day = day;
    }

    public CalendarDay getDate(){
        return date;
    }
    public int getDay(){
        return day;
    }
}
