package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Upper extends AppCompatActivity {
//    static Ex[] upperEx = new Ex[8];
    ArrayList<Ex> upperEx = new ArrayList<Ex>();

    private RecyclerView mRecylcerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<UpperAdapter.MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.upper);
        mRecylcerView = (RecyclerView) findViewById(R.id.upper_recycler);

        mRecylcerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecylcerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new UpperAdapter(myDataset, this);
        mRecylcerView.setAdapter(mAdapter);

        myDataset.add(new UpperAdapter.MyData(("푸쉬 업"), false));
        myDataset.add(new UpperAdapter.MyData(("풀업"),  false));
        myDataset.add(new UpperAdapter.MyData(("윗몸 일으키기"),  false));
        myDataset.add(new UpperAdapter.MyData(("ㅁ"),  false));
        myDataset.add(new UpperAdapter.MyData(("ㅁ"),  false));
        myDataset.add(new UpperAdapter.MyData(("ㅁ"),  false));
        myDataset.add(new UpperAdapter.MyData(("ㅁ"),  false));

        Button button = findViewById(R.id.select_upper);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}