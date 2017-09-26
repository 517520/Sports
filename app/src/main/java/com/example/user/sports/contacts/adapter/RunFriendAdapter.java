package com.example.user.sports.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.contacts.model.Message;
import com.example.user.sports.contacts.model.RunFriend;

import java.util.List;

/**
 * Author : user
 * Date : 17-9-6
 * Description :
 */
public class RunFriendAdapter extends RecyclerView.Adapter<RunFriendAdapter.MyViewHolder>{

    private Context context;
    private List<RunFriend> list;

    private OnItemClickLitener mOnItemClickLitener;


    public RunFriendAdapter(Context context, List<RunFriend> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_run_friend, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        myViewHolder.tvName.setText(list.get(i).getName());
        myViewHolder.tvDistance.setText(list.get(i).getDistance());

        if (mOnItemClickLitener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = myViewHolder.getPosition();
                    mOnItemClickLitener.onItemClick(myViewHolder.tvName, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDistance;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name_run_friend_item_tv);
            tvDistance = (TextView) itemView.findViewById(R.id.distance_run_friend_item_tv);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
