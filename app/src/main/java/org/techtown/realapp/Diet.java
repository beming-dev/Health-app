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

public class Diet extends AppCompatActivity {
    ArrayList<Ex> upperEx = new ArrayList<Ex>();

    private RecyclerView mRecylcerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DietAdapter.MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);
        mRecylcerView = (RecyclerView) findViewById(R.id.diet_recycler);

        mRecylcerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecylcerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new DietAdapter(myDataset, this);
        mRecylcerView.setAdapter(mAdapter);

//        for(int i=0; i<8; i++){
//            dietEx[i] = new Ex();
//            dietEx[i].choosed = 0;
//            dietEx[i].name = null;
//        }

        upperEx = ReadExerciseData();

        SaveExerciseData(upperEx);


//        dietEx[0].name = "버피 테스트";
        myDataset.add(new DietAdapter.MyData(("버피 테스트"), false));
//        dietEx[1].name = "슬로우 버피";
        myDataset.add(new DietAdapter.MyData(("슬로우 버피"),  false));
//        dietEx[2].name = "ㅁ";
        myDataset.add(new DietAdapter.MyData(("ㅁ"),  false));
//        dietEx[3].name = "ㅁ";
        myDataset.add(new DietAdapter.MyData(("ㅁ"),  false));
//        dietEx[4].name = "ㅁ";
        myDataset.add(new DietAdapter.MyData(("ㅁ"),  false));
//        dietEx[5].name = "ㅁ";
        myDataset.add(new DietAdapter.MyData(("ㅁ"),  false));
//        dietEx[6].name = "ㅁ";
        myDataset.add(new DietAdapter.MyData(("ㅁ"),  false));
//        dietEx[7].name = "ㅁ";
        myDataset.add(new DietAdapter.MyData(("ㅁ"),  false));


        Button button = findViewById(R.id.select_diet);
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