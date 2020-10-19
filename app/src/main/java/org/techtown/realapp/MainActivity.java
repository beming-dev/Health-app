package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Ex> exercise_day1 = new ArrayList<Ex>();
    ArrayList<Ex> exercise_day2 = new ArrayList<Ex>();
    ArrayList<Ex> exercise_day3 = new ArrayList<Ex>();
    ArrayList<Ex> exercise_day4 = new ArrayList<Ex>();

    SaveExercise saveRead = new SaveExercise();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        Button btn_today = findViewById(R.id.btn_today);
        Button btn_routine = findViewById(R.id.btn_routine);
        Button btn_myroutine = findViewById(R.id.btn_myroutine);
        Button btn_maderoutine = findViewById(R.id.btn_maderoutine);

        btn_routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RoutinePeriod.class);
                startActivity(intent);
            }
        });

        btn_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodayExercise.class);
                startActivity(intent);
            }
        });

        btn_myroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRoutine.class);
                startActivity(intent);
            }
        });

        btn_maderoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // ToDo: 운동 추가할 때 Constant의 Start변수도 꼭 바꾸기
        String[] exNames = {"상체 1", "상체 2", "상체 3", "상체 4", "상체 5", "상체 6", "상체 7", "상체 8",
                "하체 1", "하체 2", "하체 3", "하체 4", "하체 5", "하체 6", "하체 7", "하체 8",
                "다이어트 1", "다이어트 2", "다이어트 3", "다이어트 4", "다이어트 5", "다이어트 6", "다이어트 7", "다이어트 8",
                "코어 1", "코어 2", "코어 3", "코어 4", "코어 5", "코어 6", "코어 7", "코어 8"};

        if(saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1) != null) {
            exercise_day1 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
            exercise_day2 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
            exercise_day3 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
            exercise_day4 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
        }

        if(exercise_day1.size() == 0 && exercise_day2.size() == 0 && exercise_day3.size() == 0 && exercise_day4.size() == 0) {
            for (int i = 0; i < exNames.length; i++) {
                exercise_day1.add(new Ex(exNames[i]));
                exercise_day2.add(new Ex(exNames[i]));
                exercise_day3.add(new Ex(exNames[i]));
                exercise_day4.add(new Ex(exNames[i]));
            }

            saveRead.SaveExerciseData(getApplicationContext(), exercise_day1, Constants.EX_SHP_KEY_day1);
            saveRead.SaveExerciseData(getApplicationContext(), exercise_day2, Constants.EX_SHP_KEY_day2);
            saveRead.SaveExerciseData(getApplicationContext(), exercise_day3, Constants.EX_SHP_KEY_day3);
            saveRead.SaveExerciseData(getApplicationContext(), exercise_day4, Constants.EX_SHP_KEY_day4);
        }
    }
}