package org.techtown.realapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SetIntensity extends AppCompatActivity {
    ArrayList<Ex> exercise;
    SaveExercise saveRead = new SaveExercise();

    EditText editText_set;
    EditText editText_number;
    EditText editText_rest;

    int pos;
    int day;
    String Intensity_key;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_intensity);

        editText_number = findViewById(R.id.editText_number);
        editText_rest = findViewById(R.id.editText_rest);
        editText_set = findViewById(R.id.editText_set);

        Intent intent = getIntent();
        day = intent.getExtras().getInt("day");
        pos = intent.getExtras().getInt("pos");

        switch(day){
            case 1:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                Intensity_key = Constants.EX_SHP_KEY_day1;
                break;
            case 2:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                Intensity_key = Constants.EX_SHP_KEY_day2;
                break;
            case 3:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                Intensity_key = Constants.EX_SHP_KEY_day3;
                break;
            case 4:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                Intensity_key = Constants.EX_SHP_KEY_day4;
                break;
        }

        //기존에 있던 데이터 세팅
        editText_number.setText(Integer.toString(exercise.get(pos).getNumber_of_times()));
        editText_rest.setText(Integer.toString(exercise.get(pos).getRestTime()));
        editText_set.setText(Integer.toString(exercise.get(pos).getSet()));

        Button btn_complete = findViewById(R.id.btn_complete);
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //null인 상태로 종료 시 default 값 전달
                if(editText_set.getText() == null) {
                    int set = Constants.DEFAULT_SET;
                    exercise.get(pos).setSet(set);
                } else{
                    int set = Integer.parseInt(editText_set.getText().toString());
                    exercise.get(pos).setSet(set);
                }

                if(editText_number.getText() == null) {
                    int number = Constants.DEFAULT_NUMBER;
                    exercise.get(pos).setNumber_of_times(number);
                } else{
                    int number = Integer.parseInt(editText_number.getText().toString());
                    exercise.get(pos).setNumber_of_times(number);
                }
                if(editText_set.getText() == null) {
                    int rest = Constants.DEFAULT_REST;
                    exercise.get(pos).setRestTime(rest);
                } else{
                    int rest = Integer.parseInt(editText_rest.getText().toString());
                    exercise.get(pos).setRestTime(rest);
                }
                saveRead.SaveExerciseData(getApplicationContext(), exercise, Intensity_key);
                finish();
            }
        });
    }
}
