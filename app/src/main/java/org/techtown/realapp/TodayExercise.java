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
    ArrayList<Ex> check;
    ArrayList<Ex> forTodayEx;
    ArrayList<Ex> todayEx = new ArrayList<Ex>();
    TextView textView_todayEx;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.today_exercise);

        Button button = findViewById(R.id.button_todayEx);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartEx.class);
                startActivity(intent);
            }
        });

        check = ReadExerciseData(Constants.EX_SHP_KEY_day1);
        if(isExEmpty(check) == 1){
            Toast.makeText(getApplicationContext(), "루틴을 생성해주세요.", Toast.LENGTH_SHORT).show();
            finish();
        }

        calendar = findViewById(R.id.calendarView);
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2019, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendar.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());

        forTodayEx = ReadExerciseData(Constants.EX_SHP_KEY_todayEx);

        if(forTodayEx == null){
            day = 1;
            forTodayEx = ReadExerciseData(Constants.EX_SHP_KEY_day1);

            for(int i=0; i<forTodayEx.size(); i++){
                if(forTodayEx.get(i).getChoosed() == 1){
                    todayEx.add(forTodayEx.get(i));
                }
            }

            todayEx.add(new Ex(day + ""));
            SaveExerciseData(todayEx, Constants.EX_SHP_KEY_todayEx);
        }


        textView_todayEx = findViewById(R.id.textView_todayEx);
        for(int i=0; i<todayEx.size()-1; i++) {
            textView_todayEx.append(todayEx.get(i).getName() + "/");
        }
    }

    int isExEmpty(ArrayList<Ex> exercise) {
        int cnt = 0;
        for (int i = 0; i < exercise.size(); i++) {
            if (exercise.get(i).getChoosed() == 0) {
                cnt++;
            }
        }
        if (cnt == exercise.size()) {
            return 1;
        }
        return 0;
    }

    private void SaveExerciseData(ArrayList<Ex> exercise, String key){
        SharedPreferences prefForEx = getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefForEx.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.commit();
    }

    private ArrayList<Ex> ReadExerciseData(String key) {
        SharedPreferences prefForEx = getSharedPreferences(key, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefForEx.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
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
}
