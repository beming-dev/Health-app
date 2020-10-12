package org.techtown.realapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyRoutineAdapter extends RecyclerView.Adapter<MyRoutineAdapter.ViewHolder> {

    private ArrayList<MyData> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button delete_btn;
        public Button intensity_btn;
        public TextView ex_name;

        public ViewHolder(View view){
            super(view);
            delete_btn = view.findViewById(R.id.delete_btn);
            intensity_btn = view.findViewById(R.id.intensity_btn);
            ex_name = view.findViewById(R.id.Ex_name_);
        }
    }

    public MyRoutineAdapter(ArrayList<MyData> myDataset) {mDataset = myDataset;}

    @NonNull
    @Override
    public MyRoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myexercise, parent, false);

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
        public MyData(String text){
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
