package org.techtown.realapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class UpperAdapter extends RecyclerView.Adapter<UpperAdapter.ViewHolder> {

    private ArrayList<MyData> mDataSet;
    private ArrayList<Ex> exercise = null;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CheckBox cbSelect;

        public ViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.textView);
            cbSelect = view.findViewById(R.id.check_exercise);
//            SharedPreferences sharedPreferences1 = view.getContext().getSharedPreferences("pref", MODE_PRIVATE);
//
//            //Creating editor to store values to shared preferences
//            SharedPreferences.Editor editor = sharedPreferences1.edit();
//            editor.putBoolean("check", cbSelect.isChecked());
//            editor.apply();
        }
    }

    public UpperAdapter(ArrayList<MyData> myDataSet, Context mContext) {
        mDataSet = myDataSet;
        this.mContext = mContext;
    }

    @Override
    public UpperAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisekind, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.mTextView.setText(mDataSet.get(position).text);
        holder.cbSelect.setChecked(mDataSet.get(position).isSelected());
        holder.cbSelect.setVisibility(View.VISIBLE);
        holder.cbSelect.setOnCheckedChangeListener(null);

        exercise = ReadExerciseData();

        holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mDataSet.get(position).setSelected(b);
                if (mDataSet.get(position).isSelected) {
                    exercise.get(position + Constants.EX_UPPER_START).choice();
                } else {
                    exercise.get(position + + Constants.EX_UPPER_START).unchoice();
                }
                SaveExerciseData(exercise);
            }
        });
    }

    public int getItemCount() {
        return mDataSet.size();
    }

    static class MyData {
        public String text;
        public boolean isSelected;

        public MyData(String text, boolean isSelected) {
            this.text = text;
            this.isSelected = isSelected;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

    private void SaveExerciseData(ArrayList<Ex> exercise) {
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.EX_SHP_KEY, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.commit();
    }

    private ArrayList<Ex> ReadExerciseData() {
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.EX_SHP_KEY, mContext.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>() {}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}
