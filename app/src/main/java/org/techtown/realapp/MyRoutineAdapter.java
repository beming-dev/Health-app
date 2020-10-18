package org.techtown.realapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class MyRoutineAdapter extends RecyclerView.Adapter<MyRoutineAdapter.ViewHolder>
        implements ItemTouchHelperListener{

    ArrayList<Ex> exercise;
    Context mContext;
    private ArrayList<MyData> mDataset;
    int day;
    SaveExercise saveRead = new SaveExercise();

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button delete_btn;
        public Button intensity_btn;
        public TextView ex_name;

        public ViewHolder(View view){
            super(view);
            delete_btn = view.findViewById(R.id.delete_btn);
            intensity_btn = view.findViewById(R.id.intensity_btn);
            ex_name = view.findViewById(R.id.Ex_name_);

            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDeleteMessage(getAdapterPosition());
                }
            });

            intensity_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SetIntensity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.putExtra("day", day);
                    intent.putExtra("pos", mDataset.get(getAdapterPosition()).getPos());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public MyRoutineAdapter(ArrayList<MyData> myDataset, Context context, int day) {
        mDataset = myDataset;
        mContext = context;
        this.day = day;
    }

    @NonNull
    @Override
    public MyRoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myexercise_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRoutineAdapter.ViewHolder holder, int position) {
        holder.ex_name.setText(mDataset.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class MyData{
        public String text;
        public int pos;

        public MyData(String text, int pos){
            this.text = text;
            this.pos = pos;
        }

        public String getText() {
            return text;
        }
        public int getPos(){
            return pos;
        }
    }

    private void showDeleteMessage(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.Delete)); // 제거
        builder.setMessage(mContext.getString(R.string.DeleteAsk)); // 제거하시겠습니까?
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(day){
                    case 1:
                        exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day1);
                        exercise.get(mDataset.get(position).getPos()).unchoice();
                        saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day1);
                        break;
                    case 2:
                        exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day2);
                        exercise.get(mDataset.get(position).getPos()).unchoice();
                        saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day2);
                        break;
                    case 3:
                        exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day3);
                        exercise.get(mDataset.get(position).getPos()).unchoice();
                        saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day3);
                        break;
                    case 4:
                        exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day4);
                        exercise.get(mDataset.get(position).getPos()).unchoice();
                        saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day4);
                        break;
                }
                mDataset.remove(position);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton(mContext.getString(R.string.No), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //드래그로 위치 바꾸기
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        switch(day){
            case 1:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day1);
                Collections.swap(mDataset, fromPosition, toPosition);
                swapPos(fromPosition, toPosition);
                Collections.swap(exercise, mDataset.get(fromPosition).getPos(), mDataset.get(toPosition).getPos());
                saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day1);
                break;
            case 2:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day2);
                Collections.swap(mDataset, fromPosition, toPosition);
                swapPos(fromPosition, toPosition);
                Collections.swap(exercise, mDataset.get(fromPosition).getPos(), mDataset.get(toPosition).getPos());
                saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day2);
                break;
            case 3:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day3);
                Collections.swap(mDataset, fromPosition, toPosition);
                swapPos(fromPosition, toPosition);
                Collections.swap(exercise, mDataset.get(fromPosition).getPos(), mDataset.get(toPosition).getPos());
                saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day3);
                break;
            case 4:
                exercise = saveRead.ReadExerciseData(mContext, Constants.EX_SHP_KEY_day4);
                Collections.swap(mDataset, fromPosition, toPosition);
                swapPos(fromPosition, toPosition);
                Collections.swap(exercise, mDataset.get(fromPosition).getPos(), mDataset.get(toPosition).getPos());
                saveRead.SaveExerciseData(mContext, exercise, Constants.EX_SHP_KEY_day4);
                break;
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public void swapPos(int fromPosition, int toPosition){
        int x = mDataset.get(fromPosition).getPos();
        mDataset.get(fromPosition).pos = mDataset.get(toPosition).pos;
        mDataset.get(toPosition).pos = x;
    }
}
