package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Ex> exercise = new ArrayList<Ex>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent intent = new Intent(getApplicationContext(), Routine_1.class);
//                Intent intent = new Intent(getApplicationContext(), MyActivity.class);
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

        btn_maderoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Ex ex = new Ex("푸쉬업");
        exercise.add(ex);

        // ToDo: 배열 사용하여 최적화할 것. 코드 너무 복잡함
        String[] exNames = {"푸쉬업", "풀업", "윗몸일으키기", "□", "□", "□", "□"};

        //상체 운동 추가
        exercise.add(new Ex("푸쉬업"));
        exercise.add(new Ex("풀업"));
        exercise.add(new Ex("윗몸 일으키기"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        //하체 운동 추가
        exercise.add(new Ex("푸쉬업"));
        exercise.add(new Ex("풀업"));
        exercise.add(new Ex("윗몸 일으키기"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        //다이어트 운동 추가
        exercise.add(new Ex("푸쉬업"));
        exercise.add(new Ex("풀업"));
        exercise.add(new Ex("윗몸 일으키기"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        //코어 운동 추가
        exercise.add(new Ex("푸쉬업"));
        exercise.add(new Ex("풀업"));
        exercise.add(new Ex("윗몸 일으키기"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));
        exercise.add(new Ex("ㅁ"));

        SaveExerciseData(exercise);
    }

    private void SaveExerciseData(ArrayList<Ex> exercise) {
        SharedPreferences preferences = getSharedPreferences("ExEx", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString("ex", json);
        editor.apply();
    }

    private ArrayList<Ex> ReadExerciseData() {
        SharedPreferences preferences = getSharedPreferences("ExEx", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("exercise", "");
        Type type = new TypeToken<ArrayList<Ex>>() {
        }.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}