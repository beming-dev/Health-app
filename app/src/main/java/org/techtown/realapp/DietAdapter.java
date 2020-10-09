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

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {

    private ArrayList<MyData> mDataSet;
    private ArrayList<Ex> exercise;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CheckBox cbSelect;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textView);
            cbSelect = (CheckBox) view.findViewById(R.id.check_exercise);

            int pos = getAdapterPosition();
        }
    }

    public DietAdapter(ArrayList<MyData> myDataset, Context mcontext) {
        mDataSet = myDataset;
        this.mContext = mcontext;
    }

    @Override
    public DietAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisekind, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        exercise = ReadExerciseData();
        if(exercise.get(position + Constants.EX_DIET_START).getChoosed() ==1){
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
                    exercise.get(position + Constants.EX_DIET_START).choice();
                }else{
                    exercise.get(position + Constants.EX_DIET_START).unchoice();
                }
                SaveExerciseData(exercise);
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

    private void SaveExerciseData(ArrayList<Ex> exercise){
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.EX_SHP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.commit();
    }

    private ArrayList<Ex> ReadExerciseData() {
        SharedPreferences sharedpref = mContext.getSharedPreferences(Constants.EX_SHP_KEY, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpref.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}
