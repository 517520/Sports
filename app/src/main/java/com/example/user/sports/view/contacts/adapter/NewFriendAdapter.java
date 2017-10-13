package com.example.user.sports.view.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.model.jsonModel.Json_4_newFriend_list;
import com.example.user.sports.view.contacts.model.Friend;

import java.util.List;

/**
 * Author : user
 * Date : 17-9-7
 * Description :
 */
public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendAdapter.MyViewHolder> {

    private Context context;
    private List<Json_4_newFriend_list> list;

    private NewFriendAdapter.OnItemClickLitener mOnItemClickLitener;

    public NewFriendAdapter(Context context, List<Json_4_newFriend_list> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_new_friend,
                viewGroup, false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        myViewHolder.tvName.setText(list.get(i).getLinkNickname());
        myViewHolder.tvDetail.setText(list.get(i).getLinkBrief());
        if (mOnItemClickLitener != null) {
            myViewHolder.mAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = myViewHolder.getPosition();
                    mOnItemClickLitener.onItemClick(myViewHolder.mAddBtn, position);
                }
            });

            myViewHolder.mRefuseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = myViewHolder.getPosition();
                    mOnItemClickLitener.onItemClick(myViewHolder.mRefuseBtn, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail;
        ImageView imageView;
        Button mAddBtn, mRefuseBtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name_new_friend_item_tv);
            tvDetail = (TextView) itemView.findViewById(R.id.detail_new_friend_item_tv);
            imageView = (ImageView) itemView.findViewById(R.id.headview_new_friend_item_civ);
            mAddBtn = (Button) itemView.findViewById(R.id.accept_new_friend_item_btn);
            mRefuseBtn = (Button) itemView.findViewById(R.id.refuse_new_friend_item_btn);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickLitener(NewFriendAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
