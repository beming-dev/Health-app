package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class TodayExercise extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<TodayAdapter.MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_exercise);
        mRecyclerView = (RecyclerView) findViewById(R.id.today_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new TodayAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

//        String s = Lower.lowerEx[1].name.toString();
//        myDataset.add(new TodayAdapter.MyData(getString(R.string.upper), R.drawable.btn));

//        for(int i=0; i<7; i++){
//            String s = upperEx[i].name.toString();
//            myDataset.add(new TodayAdapter.MyData(s, R.drawable.btn));
//        }
    }
}
