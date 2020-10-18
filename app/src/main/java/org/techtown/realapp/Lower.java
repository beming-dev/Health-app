package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Lower extends AppCompatActivity {
    ArrayList<Ex> exercise = new ArrayList<Ex>();
    SaveExercise saveRead = new SaveExercise();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<LowerAdapter.MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lower);
        mRecyclerView = (RecyclerView) findViewById(R.id.lower_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        int requestCode = intent.getExtras().getInt("requestCode");

        myDataset = new ArrayList<>();
        mAdapter = new LowerAdapter(myDataset, this, requestCode);
        mRecyclerView.setAdapter(mAdapter);

        switch (requestCode){
            case 1111:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                break;
            case 2222:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                break;
            case 3333:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                break;
            case 4444:
                exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                break;
        }

        for(int i = Constants.EX_LOWER_START; i<Constants.EX_DIET_START; i++){
            myDataset.add(new LowerAdapter.MyData(exercise.get(i).getName(), false));
        }

        Button button = findViewById(R.id.select_lower);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}