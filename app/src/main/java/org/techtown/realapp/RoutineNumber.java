package org.techtown.realapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class RoutineNumber extends AppCompatActivity {
    ArrayList<Ex> excercise;
    static int itcalled = 0;
    TextView textView_day1;
    TextView textView_day2;
    TextView textView_day3;
    TextView textView_day4;

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

        Intent intent = getIntent();

        excercise = ReadExcerciseData();

        //처음 이 activity 호출되면 초기화 / 곧 지울 수 있음.
        itcalled++;
        if(itcalled == 1){
            for(int i=0; i<excercise.size(); i++){
//                Upper.upperEx[i] = new Ex();
//                Upper.upperEx[i].choosed = 0;
//                Upper.upperEx[i].name = null;
                excercise.get(i).init();
            }
        }

        Button complete = findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                startActivityForResult(btn_intent, 1111);
            }
        });
        btn_day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(btn_intent, 2222);
            }
        });
        btn_day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(btn_intent, 3333);
            }
        });
        btn_day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    Toast.makeText(getApplicationContext(), "1번 받음", Toast.LENGTH_SHORT).show();
                    for(int i=0; i<excercise.size(); i++){
                        textView_day1.append(excercise.get(i).getName().toString() + "\n");
                    }
                    break;
                case 2222:
                    Toast.makeText(getApplicationContext(), "2번 받음", Toast.LENGTH_SHORT).show();
                    for(int i=0; i<8; i++){
                        textView_day2.append(excercise.get(i).getName().toString() + "\n");
//                        if(Upper.upperEx[i].choosed == 1 && Upper.upperEx[i].name != null) {
//                            Upper.upperEx[i].choosed++;
//                            textView_day2.append(Upper.upperEx[i].name);
//                            textView_day2.append("\n");
//                        }
//                        if(Lower.lowerEx[i].choosed == 1 && Lower.lowerEx[i].name != null) {
//                            Lower.lowerEx[i].choosed++;
//                            textView_day2.append(Lower.lowerEx[i].name);
//                            textView_day2.append("\n");
//                        }
//                        if(Core.coreEx[i].choosed == 1 && Core.coreEx[i].name != null) {
//                            Core.coreEx[i].choosed++;
//                            textView_day2.append(Core.coreEx[i].name);
//                            textView_day2.append("\n");
//                        }
//                        if(Diet.dietEx[i].choosed == 1 && Diet.dietEx[i].name != null) {
//                            Diet.dietEx[i].choosed++;
//                            textView_day2.append(Diet.dietEx[i].name);
//                            textView_day2.append("\n");
//                        }
                    }
                    break;
                case 3333:
                    Toast.makeText(getApplicationContext(), "3번 받음", Toast.LENGTH_SHORT).show();
                    for(int i=0; i<8; i++){
                        textView_day3.append(excercise.get(i).getName().toString() + "\n");
                    }
                    break;
                case 4444:
                    Toast.makeText(getApplicationContext(), "4번 받음", Toast.LENGTH_SHORT).show();
                    for(int i=0; i<8; i++){
                        textView_day4.append(excercise.get(i).getName().toString() + "\n");
                    }
                    break;
            }
        }
    }

    private void SaveExcerciseData(ArrayList<Ex> excercise){
        SharedPreferences preferences = getSharedPreferences("excercise", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(excercise);
        editor.putString("excercise", json);
        editor.commit();
    }

    private ArrayList<Ex> ReadExcerciseData() {
        SharedPreferences sharedpref = getSharedPreferences("excercise", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpref.getString("excercise", "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}