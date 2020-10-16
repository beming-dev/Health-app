package org.techtown.realapp;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyRoutine extends AppCompatActivity {
    ArrayList<Ex> exercise_day1 = new ArrayList<Ex>();
    ArrayList<Ex> exercise_day2 = new ArrayList<Ex>();
    ArrayList<Ex> exercise_day3 = new ArrayList<Ex>();
    ArrayList<Ex> exercise_day4 = new ArrayList<Ex>();

    private RecyclerView mRecyclerView_1;
    private RecyclerView mRecyclerView_2;
    private RecyclerView mRecyclerView_3;
    private RecyclerView mRecyclerView_4;

    private RecyclerView.Adapter mAdapter_1;
    private RecyclerView.Adapter mAdapter_2;
    private RecyclerView.Adapter mAdapter_3;
    private RecyclerView.Adapter mAdapter_4;
    private RecyclerView.LayoutManager mLayoutManager_1;
    private RecyclerView.LayoutManager mLayoutManager_2;
    private RecyclerView.LayoutManager mLayoutManager_3;
    private RecyclerView.LayoutManager mLayoutManager_4;
    private ArrayList<MyRoutineAdapter.MyData> myDataset_1;
    private ArrayList<MyRoutineAdapter.MyData> myDataset_2;
    private ArrayList<MyRoutineAdapter.MyData> myDataset_3;
    private ArrayList<MyRoutineAdapter.MyData> myDataset_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myroutine);

        mRecyclerView_1 = findViewById(R.id.myroutine_1);
        mRecyclerView_2 = findViewById(R.id.myroutine_2);
        mRecyclerView_3 = findViewById(R.id.myroutine_3);
        mRecyclerView_4 = findViewById(R.id.myroutine_4);

        mRecyclerView_1.setHasFixedSize(true);
        mRecyclerView_2.setHasFixedSize(true);
        mRecyclerView_3.setHasFixedSize(true);
        mRecyclerView_4.setHasFixedSize(true);

        mLayoutManager_1 = new LinearLayoutManager(this);
        mLayoutManager_2 = new LinearLayoutManager(this);
        mLayoutManager_3 = new LinearLayoutManager(this);
        mLayoutManager_4 = new LinearLayoutManager(this);

        mRecyclerView_1.setLayoutManager(mLayoutManager_1);
        mRecyclerView_2.setLayoutManager(mLayoutManager_2);
        mRecyclerView_3.setLayoutManager(mLayoutManager_3);
        mRecyclerView_4.setLayoutManager(mLayoutManager_4);

        myDataset_1 = new ArrayList<>();
        myDataset_2 = new ArrayList<>();
        myDataset_3 = new ArrayList<>();
        myDataset_4 = new ArrayList<>();

        mAdapter_1 = new MyRoutineAdapter(myDataset_1, this, 1);
        mAdapter_2 = new MyRoutineAdapter(myDataset_2, this, 2);
        mAdapter_3 = new MyRoutineAdapter(myDataset_3, this, 3);
        mAdapter_4 = new MyRoutineAdapter(myDataset_4, this, 4);

        ItemTouchHelperCallback mCallback_1 = new ItemTouchHelperCallback((ItemTouchHelperListener) mAdapter_1);
        ItemTouchHelperCallback mCallback_2 = new ItemTouchHelperCallback((ItemTouchHelperListener) mAdapter_2);
        ItemTouchHelperCallback mCallback_3 = new ItemTouchHelperCallback((ItemTouchHelperListener) mAdapter_3);
        ItemTouchHelperCallback mCallback_4 = new ItemTouchHelperCallback((ItemTouchHelperListener) mAdapter_4);

        ItemTouchHelper helper_1 = new ItemTouchHelper(mCallback_1);
        ItemTouchHelper helper_2 = new ItemTouchHelper(mCallback_2);
        ItemTouchHelper helper_3 = new ItemTouchHelper(mCallback_3);
        ItemTouchHelper helper_4 = new ItemTouchHelper(mCallback_4);

        helper_1.attachToRecyclerView(mRecyclerView_1);
        helper_2.attachToRecyclerView(mRecyclerView_2);
        helper_3.attachToRecyclerView(mRecyclerView_3);
        helper_4.attachToRecyclerView(mRecyclerView_4);

        mRecyclerView_1.setAdapter(mAdapter_1);
        mRecyclerView_2.setAdapter(mAdapter_2);
        mRecyclerView_3.setAdapter(mAdapter_3);
        mRecyclerView_4.setAdapter(mAdapter_4);

        exercise_day1 =ReadExerciseData(Constants.EX_SHP_KEY_day1);
        exercise_day2 =ReadExerciseData(Constants.EX_SHP_KEY_day2);
        exercise_day3 =ReadExerciseData(Constants.EX_SHP_KEY_day3);
        exercise_day4 =ReadExerciseData(Constants.EX_SHP_KEY_day4);


        for(int i=0; i<exercise_day1.size(); i++){
            if(exercise_day1.get(i).getChoosed() == 1){
                myDataset_1.add(new MyRoutineAdapter.MyData(exercise_day1.get(i).getName(), i));
            }
        }

        for(int i=0; i<exercise_day2.size(); i++){
            if(exercise_day2.get(i).getChoosed() == 1){
                myDataset_2.add(new MyRoutineAdapter.MyData(exercise_day2.get(i).getName(), i));
            }
        }

        for(int i=0; i<exercise_day3.size(); i++){
            if(exercise_day3.get(i).getChoosed() == 1){
                myDataset_3.add(new MyRoutineAdapter.MyData(exercise_day3.get(i).getName(), i));
            }
        }

        for(int i=0; i<exercise_day4.size(); i++){
            if(exercise_day4.get(i).getChoosed() == 1){
                myDataset_4.add(new MyRoutineAdapter.MyData(exercise_day4.get(i).getName(), i));
            }
        }
    }

    private ArrayList<Ex> ReadExerciseData(String key) {
        SharedPreferences prefForEx = getSharedPreferences(key, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefForEx.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}
