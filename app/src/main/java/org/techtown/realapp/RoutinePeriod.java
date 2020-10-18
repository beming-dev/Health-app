package org.techtown.realapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RoutinePeriod extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_period);

        Button btn_day1 = findViewById(R.id.day_1);
        Button btn_day2 = findViewById(R.id.day_2);
        Button btn_day3 = findViewById(R.id.day_3);
        Button btn_day4 = findViewById(R.id.day_4);

        final Intent intent = new Intent(getApplicationContext(), RoutineNumber.class);

        btn_day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("routineNum", 1);
                startActivity(intent);
            }
        });

        btn_day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("routineNum", 2);
                startActivity(intent);
            }
        });

        btn_day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("routineNum", 3);
                startActivity(intent);
            }
        });

        btn_day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("routineNum", 4);
                startActivity(intent);
            }
        });
    }
}
