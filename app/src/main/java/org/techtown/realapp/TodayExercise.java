package org.techtown.realapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


public class TodayExercise extends AppCompatActivity {
    MaterialCalendarView calendar;
    TextView textView_todayEx;
    ArrayList<Ex> Exe;
    ArrayList<Ex> todayEx;
    int cnt;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.today_exercise);

//        todayEx = ReadExerciseData(Constants.EX_SHP_KEY_day4);
//        if(todayEx.get(0) == null){
//            Toast.makeText(getApplicationContext(), "루틴을 만들어 오세요", Toast.LENGTH_LONG);
//        }
//        todayEx.clear();

        Exe = ReadExerciseData(Constants.EX_SHP_KEY_todayEx);

        if(Exe == null){
            Exe = ReadExerciseData(Constants.EX_SHP_KEY_day1);
            day = 1;
            Log.d("asdfg", day + "");
        }else{
            day = Integer.parseInt(Exe.get(Exe.size()-1).getName()) + 1;
            if(day == 5){ day = 1; }

                switch (day) {
                    case 1:
                        Exe = ReadExerciseData(Constants.EX_SHP_KEY_day1);
                    case 2:
                        Exe = ReadExerciseData(Constants.EX_SHP_KEY_day2);
                    case 3:
                        Exe = ReadExerciseData(Constants.EX_SHP_KEY_day3);
                    case 4:
                        Exe = ReadExerciseData(Constants.EX_SHP_KEY_day4);

                cnt = 0;
                for(int i=0; i<Exe.size(); i++){
                    if(Exe.get(i).getChoosed() == 0) {cnt ++;}
                }
                if(cnt == Exe.size()) {
                    day = 1;
                    Exe = ReadExerciseData(Constants.EX_SHP_KEY_day1);
                }
                Log.d("qqqqqqq", "qqqqqqq");
                Log.d("asdfg", day + "");
            }
        }

        for(int i=0; i<Exe.size(); i++){
            if(Exe.get(i).getChoosed() == 1)
                todayEx.add(new Ex(Exe.get(i)));
        }
         //마지막에 day 몇 했는지 추가
        Ex ex = new Ex(day + "");
        todayEx.add(ex);
        SaveExerciseData(todayEx, Constants.EX_SHP_KEY_todayEx);

        for(int i=0; i<todayEx.size(); i++){
            textView_todayEx.append(todayEx.get(i).getName() + "/");
        }


        Button button = findViewById(R.id.button_todayEx);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartEx.class);
                startActivity(intent);
            }
        });

        calendar = findViewById(R.id.calendarView);
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2019, 0,1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendar.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());
    }
    public class SundayDecorator implements DayViewDecorator {

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

    public class SaturdayDecorator implements DayViewDecorator {

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

    public class OneDayDecorator implements DayViewDecorator {

        private CalendarDay date;

        public OneDayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
        }
    }

    private void SaveExerciseData(ArrayList<Ex> exercise, String key) {
        SharedPreferences prefForEx = getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefForEx.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.apply();
    }

    private ArrayList<Ex> ReadExerciseData(String key) {
        SharedPreferences prefForEx = getSharedPreferences(key, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefForEx.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}
