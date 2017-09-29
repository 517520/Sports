package com.example.user.sports.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.contacts.activity.ChatActivity;
import com.example.user.sports.contacts.model.Friend;
import com.example.user.sports.utils.IntentUtils;

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
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
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

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name_friend_item_tv);
        }
    }
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickLitener(FriendAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
