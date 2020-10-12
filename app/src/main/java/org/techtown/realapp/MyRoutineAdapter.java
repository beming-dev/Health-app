package org.techtown.realapp;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyRoutineAdapter extends RecyclerView.Adapter<MyRoutineAdapter.ViewHolder> {

    Context mContext;
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

            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDeleteMessage(getAdapterPosition());
                }
            });
        }
    }

    public MyRoutineAdapter(ArrayList<MyData> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

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

    private void showDeleteMessage(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("제거");
        builder.setMessage("제거하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "예 버튼이 눌렸습니다.";
                mDataset.remove(position);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "아니오 버튼이 눌렸습니다.";
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
