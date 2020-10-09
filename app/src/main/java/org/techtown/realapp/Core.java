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

public class Core extends AppCompatActivity {
    ArrayList<Ex> upperEx = new ArrayList<Ex>();

    private RecyclerView mRecylcerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CoreAdapter.MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core);
        mRecylcerView = (RecyclerView) findViewById(R.id.core_recycler);

        mRecylcerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecylcerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new CoreAdapter(myDataset, this);
        mRecylcerView.setAdapter(mAdapter);

        upperEx = ReadExerciseData();

        SaveExerciseData(upperEx);

        myDataset.add(new CoreAdapter.MyData(("플랭크"), false));
        myDataset.add(new CoreAdapter.MyData(("크런치"),  false));
        myDataset.add(new CoreAdapter.MyData(("할로우바디홀드"),  false));
        myDataset.add(new CoreAdapter.MyData(("레그레이즈"),  false));
        myDataset.add(new CoreAdapter.MyData(("ㅁ"),  false));
        myDataset.add(new CoreAdapter.MyData(("ㅁ"),  false));
        myDataset.add(new CoreAdapter.MyData(("ㅁ"),  false));
        myDataset.add(new CoreAdapter.MyData(("ㅁ"),  false));

        Button button = findViewById(R.id.select_core);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void SaveExerciseData(ArrayList<Ex> exercise){
        SharedPreferences preferences = getSharedPreferences(Constants.EX_SHP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.commit();
    }

    private ArrayList<Ex> ReadExerciseData() {
        SharedPreferences sharedpref = getSharedPreferences(Constants.EX_SHP_KEY, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpref.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}