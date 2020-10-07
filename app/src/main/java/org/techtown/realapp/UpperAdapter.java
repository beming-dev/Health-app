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

public class UpperAdapter extends RecyclerView.Adapter<UpperAdapter.ViewHolder> {

    private ArrayList<MyData> mDataSet;
    private ArrayList<Ex> exercise = null;
    private Context mcontext;
    SharedPreferences preferences;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CheckBox cbSelect;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textView);
            cbSelect = (CheckBox) view.findViewById(R.id.check_exercise);

            int pos = getAdapterPosition();


            Type type = new TypeToken<ArrayList<Ex>>() {
            }.getType();
            Gson gson = new Gson();

            preferences = view.getContext().getSharedPreferences("ExEx", MODE_PRIVATE);
            String json = preferences.getString("ex", "");
            exercise = gson.fromJson(json, type);

            assert exercise != null;

            SharedPreferences sharedPreferences1 = view.getContext().getSharedPreferences("pref", MODE_PRIVATE);

            //Creating editor to store values to shared preferences
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putBoolean("check", cbSelect.isChecked());
            editor.apply();
        }
    }

    public UpperAdapter(ArrayList<MyData> myDataSet, Context mContext) {
        mDataSet = myDataSet;
        this.mcontext = mContext;
    }

    @Override
    public UpperAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisekind, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {

        assert exercise != null;

        holder.mTextView.setText(mDataSet.get(position).text);
        holder.cbSelect.setChecked(mDataSet.get(position).isSelected());
        holder.cbSelect.setVisibility(View.VISIBLE);
        holder.cbSelect.setOnCheckedChangeListener(null);
        holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mDataSet.get(position).setSelected(b);

                if (mDataSet.get(position).isSelected) {
//                    Upper.upperEx[position].choosed = 1;
                    exercise.get(position).choice();
                } else {
//                    Upper.upperEx[position].choosed = 0;
                    exercise.get(position).unchoice();
                }

                SaveExcerciseData(exercise);
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

    private void SaveExcerciseData(ArrayList<Ex> excercise) {
        SharedPreferences preferences = mcontext.getSharedPreferences("ExEx", mcontext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Ex>>() {
        }.getType();
        String json = gson.toJson(excercise, type);
        editor.putString("ex", json);
        editor.commit();
    }

    private ArrayList<Ex> ReadExcerciseData() {
        SharedPreferences preferences = mcontext.getSharedPreferences("ExEx", mcontext.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("excercise", "");
        Type type = new TypeToken<ArrayList<Ex>>() {
        }.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}
