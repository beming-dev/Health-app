package org.techtown.realapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        //complete -> mainActivity
        Intent intent = getIntent();

        Button complete = findViewById(R.id.complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Routine이 생성됐습니다. 내 루틴을 확인하세요.", Toast.LENGTH_LONG);
                Intent send_intent = new Intent(getApplicationContext(), MainActivity.class);
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

        final Intent btn_intent = new Intent(getApplicationContext(), MyActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView textView_day1 = findViewById(R.id.textview_day1);
        TextView textView_day2 = findViewById(R.id.textview_day2);
        TextView textView_day3 = findViewById(R.id.textview_day3);
        TextView textView_day4 = findViewById(R.id.textview_day4);

        if(resultCode == RESULT_OK){
            switch(requestCode) {
                case 1111:
                    exercise = ReadExerciseData(Constants.EX_SHP_KEY_day1);
                    Toast.makeText(getApplicationContext(), "1번 받음", Toast.LENGTH_SHORT).show();
                    textView_day1.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day1.append(exercise.get(i).getName()+ "\n");
                            SaveExerciseData(exercise, Constants.EX_SHP_KEY_day1);
                        }
                    }
                    break;
                case 2222:
                    exercise = ReadExerciseData(Constants.EX_SHP_KEY_day2);
                    Toast.makeText(getApplicationContext(), "2번 받음", Toast.LENGTH_SHORT).show();
                    textView_day2.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day2.append(exercise.get(i).getName()+ "\n");
                            SaveExerciseData(exercise, Constants.EX_SHP_KEY_day2);
                        }
                    }
                    break;
                case 3333:
                    exercise = ReadExerciseData(Constants.EX_SHP_KEY_day3);
                    Toast.makeText(getApplicationContext(), "3번 받음", Toast.LENGTH_SHORT).show();
                    textView_day3.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day3.append(exercise.get(i).getName()+ "\n");
                            SaveExerciseData(exercise, Constants.EX_SHP_KEY_day3);
                        }
                    }
                    break;
                case 4444:
                    exercise = ReadExerciseData(Constants.EX_SHP_KEY_day4);
                    Toast.makeText(getApplicationContext(), "4번 받음", Toast.LENGTH_SHORT).show();
                    textView_day4.setText(null);
                    for(int i=0; i<exercise.size(); i++){
                        if(exercise.get(i).getChoosed() == 1) {
                            textView_day4.append(exercise.get(i).getName()+ "\n");
                            SaveExerciseData(exercise, Constants.EX_SHP_KEY_day4);
                        }
                    }
                    break;
            }
        }
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
}