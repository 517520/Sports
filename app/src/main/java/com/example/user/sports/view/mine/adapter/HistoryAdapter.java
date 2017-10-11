package com.example.user.sports.view.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.sports.R;

/**
 * Created by user on 10/11/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private Context context;
    private int HEADER_POSITION = 0;
    private int NORMAL_POSITION = 1;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_POSITION)
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_header, parent,false));
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, parent,false));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_POSITION;
        else
            return NORMAL_POSITION;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
