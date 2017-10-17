package com.example.user.sports.view.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.view.mine.model.Exercise;

import java.util.List;

/**
 * Created by user on 10/11/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Exercise> mExerciseList;
    private Context context;
    private int HEADER_POSITION = 0;
    private int NORMAL_POSITION = 1;

    public HistoryAdapter(Context context,List<Exercise> list) {
        this.context = context;
        mExerciseList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_POSITION)
            return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_header, parent,false));
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder){

            if (mExerciseList.get(i-1).getExerciseStyle()==1){
                ((MyViewHolder) viewHolder).textView.setText("跑步"+mExerciseList.get(i-1).getExerciseDistance()+"Km, 耗时"+mExerciseList.get(i-1).getExerciseTime()+"min");

                ((MyViewHolder) viewHolder).mImageView.setImageResource(R.drawable.ic_gray_run);
            }
            else if (mExerciseList.get(i-1).getExerciseStyle()==2){
                ((MyViewHolder) viewHolder).textView.setText("骑行"+mExerciseList.get(i-1).getExerciseDistance()+"Km, 耗时"+mExerciseList.get(i-1).getExerciseTime()+"min");
                ((MyViewHolder) viewHolder).mImageView.setImageResource(R.drawable.ic_gray_riding);

            }
            else {
                ((MyViewHolder) viewHolder).textView.setText("步行"+mExerciseList.get(i-1).getExerciseDistance()+"Km, 耗时"+mExerciseList.get(i-1).getExerciseTime()+"min");
                ((MyViewHolder) viewHolder).mImageView.setImageResource(R.drawable.ic_gray_walk);

            }

        }
        if (viewHolder instanceof HeaderViewHolder){

            int distance =0;
            for (int j=0;j<mExerciseList.size();j++){
                distance+=mExerciseList.get(j).getExerciseDistance();
            }
            int time = 0;
            for (int j=0;j<mExerciseList.size();j++){
                time+=mExerciseList.get(j).getExerciseTime();
            }

            ((HeaderViewHolder) viewHolder).mTextViewNumber.setText(String.valueOf(mExerciseList.size()));
            ((HeaderViewHolder) viewHolder).mTextViewKilo.setText(String.valueOf(distance));
            ((HeaderViewHolder) viewHolder).mTextViewTime.setText(String.valueOf(time));
            ((HeaderViewHolder) viewHolder).mTextViewSpeed.setText(String.valueOf((distance*60)/time));
            ((HeaderViewHolder) viewHolder).mTextViewCost.setText(String.valueOf(distance*997));

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_POSITION;
        else
            return NORMAL_POSITION;
    }



    @Override
    public int getItemCount() {
        return mExerciseList.size()+1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView mImageView;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.content_tv);
            mImageView = (ImageView)itemView.findViewById(R.id.sport_style_iv);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewKilo;
        private TextView mTextViewNumber;
        private TextView mTextViewTime;
        private TextView mTextViewSpeed;
        private TextView mTextViewCost;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mTextViewKilo = (TextView)itemView.findViewById(R.id.kilo_nunber_tv);
            mTextViewNumber = (TextView)itemView.findViewById(R.id.number_tv);
            mTextViewTime = (TextView)itemView.findViewById(R.id.time_history_tv);
            mTextViewSpeed = (TextView)itemView.findViewById(R.id.speed_average_tv);
            mTextViewCost = (TextView)itemView.findViewById(R.id.cost_history_tv);

        }
    }

}
