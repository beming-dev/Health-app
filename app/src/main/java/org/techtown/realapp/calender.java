package org.techtown.realapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;

public class calender {
    public static class SundayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SundayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }
    }

    public static class SaturdayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SaturdayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }

    public static class OneDayDecorator implements DayViewDecorator {

        private CalendarDay date;
        private  int day;

        public OneDayDecorator(CalendarDay date, int day) {
            this.date = date;
            this.day = day;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new AbsoluteSizeSpan(90));
            Log.d("Colordd", Integer.toString(this.day));
            switch (day){
                case 1:
                    view.addSpan(new ForegroundColorSpan(Color.RED));
                    break;
                case 2:
                    view.addSpan(new ForegroundColorSpan(Color.BLUE));
                    break;
                case 3:
                    view.addSpan(new ForegroundColorSpan(Color.YELLOW));
                    break;
                case 4:
                    view.addSpan(new ForegroundColorSpan(Color.GREEN));
                    break;
            }
        }
    }

    public static class TodayDecorator implements DayViewDecorator {

        private CalendarDay date;
        public TodayDecorator() {date = CalendarDay.today(); }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5,Color.BLACK));
        }
    }
}
