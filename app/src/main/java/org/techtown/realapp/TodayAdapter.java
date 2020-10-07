package org.techtown.realapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.today_image);
            mTextView = (TextView)view.findViewById(R.id.today_textView);
        }
    }

    public TodayAdapter(ArrayList<MyData> myDataset) {mDataset = myDataset;}

    public TodayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todayex, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).text);
        holder.mImageView.setImageResource(mDataset.get(position).img);
    }

    public int getItemCount() {
        return mDataset.size();
    }

    static class MyData{
        public String text;
        public int img;
        public MyData(String text, int img){
            this.text = text;
            this.img = img;
        }
    }
}
