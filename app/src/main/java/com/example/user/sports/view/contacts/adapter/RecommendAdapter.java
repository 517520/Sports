package com.example.user.sports.view.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.view.contacts.model.Team;

import java.util.List;

/**
 * Author : user
 * Date : 17-9-7
 * Description :
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder> {

    private Context context;
    private List<Team> list;

    private RecommendAdapter.OnItemClickLitener mOnItemClickLitener;

    public RecommendAdapter(Context context, List<Team> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recommend,
                viewGroup, false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        myViewHolder.tvName.setText(list.get(i).getName());
        myViewHolder.tvDetails.setText(list.get(i).getDetail());
        myViewHolder.imageView.setImageBitmap(list.get(i).getBitmap());
        if (mOnItemClickLitener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = myViewHolder.getPosition();
                    mOnItemClickLitener.onItemClick(myViewHolder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetails;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name_recommend_item_tv);
            tvDetails = (TextView) itemView.findViewById(R.id.details_recommend_item_tv);
            imageView = (ImageView) itemView.findViewById(R.id.headview_recommend_item_iv);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickLitener(RecommendAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
