package org.techtown.realapp;

import android.content.Context;
import android.content.SharedPreferences;
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

public class LowerAdapter extends RecyclerView.Adapter<LowerAdapter.ViewHolder> {

    private ArrayList<MyData> mDataSet;
    private ArrayList<Ex> exercise;
    SaveExercise saveRead = new SaveExercise();
    private Context mContext;
    private int requestCode;
    String key_save;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CheckBox cbSelect;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textView);
            cbSelect = (CheckBox) view.findViewById(R.id.check_exercise);
        }
    }

    public LowerAdapter(ArrayList<MyData> myDataset, Context mcontext, int requestCode) {
        mDataSet = myDataset;
        this.mContext = mcontext;
        this.requestCode = requestCode;
    }

    @Override
    public LowerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ex_property_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch(requestCode){
            case 1111:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day1);
                key_save = Constants.EX_SHP_KEY_day1;
                break;
            case 2222:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day2);
                key_save = Constants.EX_SHP_KEY_day2;
                break;
            case 3333:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day3);
                key_save = Constants.EX_SHP_KEY_day3;
                break;
            case 4444:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day4);
                key_save = Constants.EX_SHP_KEY_day4;
                break;
        }
        if(exercise.get(position + Constants.EX_LOWER_START).getChoosed() ==1){
            mDataSet.get(position).setSelected(true);
        }

        holder.mTextView.setText(mDataSet.get(position).text);
        holder.cbSelect.setChecked(mDataSet.get(position).isSelected());
        holder.cbSelect.setVisibility(View.VISIBLE);
        holder.cbSelect.setOnCheckedChangeListener(null);



        holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mDataSet.get(position).setSelected(b);
                if(mDataSet.get(position).isSelected){
                    exercise.get(position + Constants.EX_LOWER_START).choice();
                }else{
                    exercise.get(position + Constants.EX_LOWER_START).unchoice();
                }
                saveRead.SaveExerciseData(mContext, exercise, key_save);
            }
        });
    }

    public int getItemCount() {
        return mDataSet.size();
    }

    static class MyData{
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
}
