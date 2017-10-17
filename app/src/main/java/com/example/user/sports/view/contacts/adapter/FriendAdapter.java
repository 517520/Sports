package com.example.user.sports.view.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.view.contacts.model.Friend;

import java.util.List;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private Context mContext;
    private List<Friend> friendList;
    private LayoutInflater mInflater;


    private FriendAdapter.OnItemClickLitener mOnItemClickLitener;

    public FriendAdapter(Context mContext, List<Friend> friendList) {
        this.mContext = mContext;
        this.friendList = friendList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_friend, parent, false));
    }

    @Override
    public void onBindViewHolder(final FriendAdapter.ViewHolder holder, final int position) {
        final Friend friend = friendList.get(position);
        holder.tvName.setText(friend.getName());
        holder.imageView.setImageBitmap(friend.getPhoto());

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getPosition();
                    mOnItemClickLitener.onItemClick(holder.tvName.getText().toString(), position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return friendList != null ? friendList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name_friend_item_tv);
            imageView = (ImageView) itemView.findViewById(R.id.head_friend_item_iv);
        }
    }
    public interface OnItemClickLitener {
        void onItemClick(String name, int position);

    }

    public void setOnItemClickLitener(FriendAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
