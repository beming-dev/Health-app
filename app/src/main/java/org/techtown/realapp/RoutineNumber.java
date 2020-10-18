package org.techtown.realapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class RoutineNumber extends AppCompatActivity {
    ArrayList<Ex> exercise;
    ArrayList<Ex> exercise_1;
    ArrayList<Ex> exercise_2;
    ArrayList<Ex> exercise_3;
    ArrayList<Ex> exercise_4;
    TextView textView_day1;
    TextView textView_day2;
    TextView textView_day3;
    TextView textView_day4;

    SaveExercise saveRead = new SaveExercise();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_number);

        LinearLayout receive1 = findViewById(R.id.layout_recieve1);
        LinearLayout receive2 = findViewById(R.id.layout_recieve2);
        LinearLayout receive3 = findViewById(R.id.layout_recieve3);
        LinearLayout receive4 = findViewById(R.id.layout_recieve4);
        Button btn_day1 = findViewById(R.id.btn_day1);
        Button btn_day2 = findViewById(R.id.btn_day2);
        Button btn_day3 = findViewById(R.id.btn_day3);
        Button btn_day4 = findViewById(R.id.btn_day4);
        textView_day1 = findViewById(R.id.textview_day1);
        textView_day2 = findViewById(R.id.textview_day2);
        textView_day3 = findViewById(R.id.textview_day3);
        textView_day4 = findViewById(R.id.textview_day4);

        exercise_1 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
        exercise_2 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
        exercise_3 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
        exercise_4 = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);

        //set textview when oncreate called
        textView_day1.setText(null);
        textView_day2.setText(null);
        textView_day3.setText(null);
        textView_day4.setText(null);

        for(int i=0; i<exercise_1.size(); i++) {
            if(exercise_1.get(i).getChoosed() == 1) {
                textView_day1.append(exercise_1.get(i).getName() + "\n");
            }
            if(exercise_2.get(i).getChoosed() == 1) {
                textView_day2.append(exercise_2.get(i).getName() + "\n");
            }
            if(exercise_3.get(i).getChoosed() == 1) {
                textView_day3.append(exercise_3.get(i).getName() + "\n");
            }
            if(exercise_4.get(i).getChoosed() == 1) {
                textView_day4.append(exercise_4.get(i).getName() + "\n");
            }
        }

        //complete -> mainActivity
        Intent intent = getIntent();
        Button complete = findViewById(R.id.complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), getString(R.string.MakeRoutineComplete), Toast.LENGTH_LONG).show();
                Intent send_intent = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(send_intent);
            }
        });

        int num = intent.getExtras().getInt("routineNum");

        switch(num){
            case 1:
                receive1.setVisibility(Button.VISIBLE);
                receive2.setVisibility(Button.GONE);
                receive3.setVisibility(Button.GONE);
                receive4.setVisibility(Button.GONE);
                break;
            case 2:
                receive1.setVisibility(Button.VISIBLE);
                receive2.setVisibility(Button.VISIBLE);
                receive3.setVisibility(Button.GONE);
                receive4.setVisibility(Button.GONE);
                break;
            case 3:
                receive1.setVisibility(Button.VISIBLE);
                receive2.setVisibility(Button.VISIBLE);
                receive3.setVisibility(Button.VISIBLE);
                receive4.setVisibility(Button.GONE);
                break;
            case 4:
                receive1.setVisibility(Button.VISIBLE);
                receive2.setVisibility(Button.VISIBLE);
                receive3.setVisibility(Button.VISIBLE);
                receive4.setVisibility(Button.VISIBLE);
                break;
        }

        final Intent btn_intent = new Intent(getApplicationContext(), ExkindSel.class);
        btn_day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_intent.putExtra("requestCode", 1111);
                startActivityForResult(btn_intent, 1111);
            }
        });
        btn_day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_intent.putExtra("requestCode", 2222);
                startActivityForResult(btn_intent, 2222);
            }
        });
        btn_day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_intent.putExtra("requestCode", 3333);
                startActivityForResult(btn_intent, 3333);
            }
        });
        btn_day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_intent.putExtra("requestCode", 4444);
                startActivityForResult(btn_intent, 4444);
            }
        });
    }

    //몇째날을 선택한건지 확인
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        textView_day1.setMovementMethod(new ScrollingMovementMethod());
        textView_day2.setMovementMethod(new ScrollingMovementMethod());
        textView_day3.setMovementMethod(new ScrollingMovementMethod());
        textView_day4.setMovementMethod(new ScrollingMovementMethod());


        if(resultCode == RESULT_OK){
            switch(requestCode) {
                case 1111:
                    exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                    textView_day1.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day1.append(exercise.get(i).getName()+ "\n");
                            saveRead.SaveExerciseData(getApplicationContext(), exercise, Constants.EX_SHP_KEY_day1);
                        }
                    }
                    break;
                case 2222:
                    exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                    textView_day2.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day2.append(exercise.get(i).getName()+ "\n");
                            saveRead.SaveExerciseData(getApplicationContext(), exercise, Constants.EX_SHP_KEY_day2);
                        }
                    }
                    break;
                case 3333:
                    exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                    textView_day3.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day3.append(exercise.get(i).getName()+ "\n");
                            saveRead.SaveExerciseData(getApplicationContext(), exercise, Constants.EX_SHP_KEY_day3);
                        }
                    }
                    break;
                case 4444:
                    exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                    textView_day4.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day4.append(exercise.get(i).getName()+ "\n");
                            saveRead.SaveExerciseData(getApplicationContext(), exercise, Constants.EX_SHP_KEY_day4);
                        }
                    }
                    break;
            }
        }
    }
}