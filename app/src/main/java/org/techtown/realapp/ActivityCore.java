package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityCore extends AppCompatActivity {
    ArrayList<Ex> exercise = new ArrayList<Ex>();
    SaveExercise saveRead = new SaveExercise();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CoreAdapter.MyData> myDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core);

        mRecyclerView = (RecyclerView) findViewById(R.id.core_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        // ToDo: Use constants
        int requestCode = intent.getExtras().getInt("requestCode");

        myDataSet = new ArrayList<>();
        mAdapter = new CoreAdapter(myDataSet, this, requestCode);
        mRecyclerView.setAdapter(mAdapter);

        switch (requestCode){
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

        for(int i=Constants.EX_CORE_START; i<exercise.size(); i++){
            myDataSet.add(new CoreAdapter.MyData(exercise.get(i).getName(), false));
        }

        Button button = findViewById(R.id.select_core);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}