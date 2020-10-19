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
    ArrayList<Ex> todayEx = new ArrayList<>();
    ArrayList<CompleteEx> CompleteEx;
    TextView textView_todayEx;
    TextView textView_day;
    SaveExercise saveRead = new SaveExercise();
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.today_exercise);

        textView_day = findViewById(R.id.textView_day);

        Button start = findViewById(R.id.button_todayEx);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartEx.class);
                startActivity(intent);
            }
        });

        //다음 날
        Button nextBtn = findViewById(R.id.btn_next_day);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = Integer.parseInt(todayEx.get(todayEx.size()-1).getName()) + 1;
                if(day == 5){day =1;}

                switch (day){
                    case 1:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                        break;
                    case 2:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                        break;
                    case 3:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                        break;
                    case 4:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                        break;
                }
                if(isExEmpty(forTodayEx) == 1){
                    forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                    day = 1;
                }

                todayEx = new ArrayList<Ex>();
                for(int i=0; i<forTodayEx.size(); i++){
                    if(forTodayEx.get(i).getChoosed() == 1){
                        todayEx.add(forTodayEx.get(i));
                    }
                }

                todayEx.add(new Ex(day + ""));
                saveRead.SaveExerciseData(getApplicationContext(), todayEx, Constants.EX_SHP_KEY_todayEx);

                textView_todayEx.setText("");
                textView_day.setText("(day" +day+ ")");

                for(int i=0; i<todayEx.size()-1; i++) {
                    textView_todayEx.append(todayEx.get(i).getName() + "/");
                }
            }
        });

        //이전 날
       Button prevBtn = findViewById(R.id.btn_prev_day);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = Integer.parseInt(todayEx.get(todayEx.size()-1).getName()) - 1;
                if(day == 0){day =4;}

                switch (day){
                    case 1:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                        break;
                    case 2:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                        break;
                    case 3:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                        break;
                    case 4:
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                        break;
                }
                if(isExEmpty(forTodayEx) == 1){
                    forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                    day = 3;
                    if(isExEmpty(forTodayEx) == 1){
                        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                        day = 2;
                        if(isExEmpty(forTodayEx) == 1){
                            forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                            day = 1;
                        }
                    }
                }

                todayEx = new ArrayList<Ex>();
                for(int i=0; i<forTodayEx.size(); i++){
                    if(forTodayEx.get(i).getChoosed() == 1){
                        todayEx.add(forTodayEx.get(i));
                    }
                }

                todayEx.add(new Ex(day + ""));
                saveRead.SaveExerciseData(getApplicationContext(), todayEx, Constants.EX_SHP_KEY_todayEx);
                textView_day.setText("(day" +day+ ")");

                textView_todayEx.setText("");
                for(int i=0; i<todayEx.size()-1; i++) {
                    textView_todayEx.append(todayEx.get(i).getName() + "/");
                }
            }
        });

        check = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
        if(isExEmpty(check) == 1){
            Toast.makeText(getApplicationContext(), "루틴을 생성해주세요.", Toast.LENGTH_SHORT).show();
            finish();
        }

        CompleteEx = saveRead.ReadExercisCompData(getApplicationContext(), Constants.EX_SHP_KEY_COMPLETE);
        if(CompleteEx == null){
            CompleteEx = new ArrayList<>();
        }

        calendar = findViewById(R.id.calendarView);
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2019, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendar.addDecorators(
                new calender.SundayDecorator(),
                new calender.SaturdayDecorator(),
                new calender.TodayDecorator());

        for(int i=0; i<CompleteEx.size(); i++) {
            calendar.addDecorators(
                    new calender.OneDayDecorator(CompleteEx.get(i).getDate(), CompleteEx.get(i).getDay()));
        }

        forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_todayEx);

        if(forTodayEx == null){
            day = 1;
            forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
        }
        else{
            day = Integer.parseInt(forTodayEx.get(forTodayEx.size()-1).getName());

            switch (day){
                case 1:
                    forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                    break;
                case 2:
                    forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                    break;
                case 3:
                    forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                    break;
                case 4:
                    forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                    break;
            }
            if(isExEmpty(forTodayEx) == 1){
                forTodayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                day = 1;
            }
        }

        for(int i=0; i<forTodayEx.size(); i++){
            if(forTodayEx.get(i).getChoosed() == 1){
                todayEx.add(forTodayEx.get(i));
            }
        }

        todayEx.add(new Ex(day + ""));
        saveRead.SaveExerciseData(getApplicationContext(), todayEx, Constants.EX_SHP_KEY_todayEx);

        textView_todayEx = findViewById(R.id.textView_todayEx);
        textView_day.setText("(day" +day+ ")");
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
}

