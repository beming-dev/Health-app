package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityDiet extends AppCompatActivity {
    ArrayList<Ex> exercise = new ArrayList<Ex>();
    SaveExercise saveRead = new SaveExercise();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DietAdapter.MyData> myDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);
        mRecyclerView = (RecyclerView) findViewById(R.id.diet_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        int requestCode = intent.getExtras().getInt("requestCode");

        myDataSet = new ArrayList<>();
        mAdapter = new DietAdapter(myDataSet, this, requestCode);
        mRecyclerView.setAdapter(mAdapter);

        /*
        ToDo:옵션이 여러 개 있을때 표현하는 좋은 방법!

        A 0x01 00000001
        B 0x02 00000010
        C 0x04 00000100
        D 0x08 00001000
        E 0x10 00010000

        exs  0b00110110

        for(int i=0x1000;i>0;i>>1)

        */

        switch (requestCode) {
            case 1111:
                exercise = saveRead.loadDataEx(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                break;
            case 2222:
                exercise = saveRead.loadDataEx(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                break;
            case 3333:
                exercise = saveRead.loadDataEx(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                break;
            case 4444:
                exercise = saveRead.loadDataEx(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                break;
        }

        for (int i = Constants.EX_DIET_START; i < Constants.EX_CORE_START; i++) {
            myDataSet.add(new DietAdapter.MyData(exercise.get(i).getName(), false));
        }

        Button button = findViewById(R.id.select_diet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}