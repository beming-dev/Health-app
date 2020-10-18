package org.techtown.realapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExkindSelAdapter extends RecyclerView.Adapter<ExkindSelAdapter.ViewHolder> {

    private ArrayList<MyData> mDataset;
    private int requestCode;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View view, final int requestCode) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.image);
            mTextView = (TextView) view.findViewById(R.id.textView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION) {
                        if(pos == 0){
                            Intent intent = new Intent(v.getContext(), Upper.class);
                            intent.putExtra("requestCode", requestCode);
                            v.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT));
                        }
                        else if(pos == 1){
                            Intent intent = new Intent(v.getContext(), Lower.class);
                            intent.putExtra("requestCode", requestCode);
                            v.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT));
                        }
                        else if(pos == 2){
                            Intent intent = new Intent(v.getContext(), Diet.class);
                            intent.putExtra("requestCode", requestCode);
                            v.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT));
                        }
                        else {
                            Intent intent = new Intent(v.getContext(), Core.class);
                            intent.putExtra("requestCode", requestCode);
                            v.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT));
                        }
                    }
                }
            });
        }
    }

    public ExkindSelAdapter(ArrayList<MyData> myDataset, int requestCode) {
        mDataset = myDataset;
        this.requestCode = requestCode;
    }

    @Override
    public ExkindSelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_card, parent, false);

        ViewHolder vh = new ViewHolder(v, requestCode);
        return vh;
    }

    @Override
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
        public MyData(String text, int img) {
            this.text = text;
            this.img = img;
        }
    }
}
