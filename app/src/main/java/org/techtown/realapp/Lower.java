package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Lower extends AppCompatActivity {
    ArrayList<Ex> upperEx = new ArrayList<Ex>();

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

        myDataset = new ArrayList<>();
        mAdapter = new LowerAdapter(myDataset, this);
        mRecyclerView.setAdapter(mAdapter);

//        for(int i=0; i<8; i++){
//            lowerEx[i] = new Ex();
//            lowerEx[i].choosed = 0;
//            lowerEx[i].name = null;
//        }

        upperEx = ReadExerciseData();

        SaveExerciseData(upperEx);

//        lowerEx[0].name = "스쿼트";
        myDataset.add(new LowerAdapter.MyData(("스쿼트"), false));
//        lowerEx[1].name = "양발 런지";
        myDataset.add(new LowerAdapter.MyData(("양발 런지"), false));
//        lowerEx[2].name = "오른발 런지";
        myDataset.add(new LowerAdapter.MyData(("오른발 런지"), false));
//        lowerEx[3].name = "왼발 런지";
        myDataset.add(new LowerAdapter.MyData(("왼발 런지"), false));
//        lowerEx[4].name = "ㅁ";
        myDataset.add(new LowerAdapter.MyData(("ㅁ"), false));
//        lowerEx[5].name = "ㅁ";
        myDataset.add(new LowerAdapter.MyData(("ㅁ"), false));

        Button button = findViewById(R.id.select_lower);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void SaveExerciseData(ArrayList<Ex> exercise){
        SharedPreferences preferences = getSharedPreferences("exercise", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        String json = gson.toJson(exercise, type);
        editor.putString("exercise", json);
        editor.commit();
    }

    private ArrayList<Ex> ReadExerciseData() {
        SharedPreferences sharedpref = getSharedPreferences("exercise", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpref.getString("exercise", "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}