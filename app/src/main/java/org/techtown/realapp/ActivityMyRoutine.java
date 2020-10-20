package org.techtown.realapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityMyRoutine extends AppCompatActivity {
    SaveExercise saveRead = new SaveExercise();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myroutine);

        int[] viewIds = {
                R.id.myroutine_1,
                R.id.myroutine_2,
                R.id.myroutine_3,
                R.id.myroutine_4
        };

        String[] shpKeyDay = {
                Constants.EX_SHP_KEY_day1,
                Constants.EX_SHP_KEY_day2,
                Constants.EX_SHP_KEY_day3,
                Constants.EX_SHP_KEY_day4
        };

        for (int i = 0; i < 4; i++) {

            // Get recycler view and do settings
            RecyclerView mRecyclerView = findViewById(viewIds[i]);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Display chosen data on RecyclerView
            ArrayList<MyRoutineAdapter.MyData> myDataSet = new ArrayList<>();
            int j = 0;
            for (Ex ex : saveRead.<ArrayList<Ex>>loadDataEx(getApplicationContext(), shpKeyDay[i])) {
                if (ex.getChoosed() == 1) {
                    myDataSet.add(new MyRoutineAdapter.MyData(ex.getName(), j));
                }
                j++;
            }
            RecyclerView.Adapter mAdapter = new MyRoutineAdapter(myDataSet, this, (i + 1));
            mRecyclerView.setAdapter(mAdapter);

            // Add ItemTouchHelper on RecyclerView
            ItemTouchHelperCallback mCallback = new ItemTouchHelperCallback((ItemTouchHelperListener) mAdapter);
            ItemTouchHelper helper = new ItemTouchHelper(mCallback);
            helper.attachToRecyclerView(mRecyclerView);
        }
    }
}
