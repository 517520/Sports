package com.example.user.sports.view.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.sports.App;
import com.example.user.sports.R;
import com.example.user.sports.view.contacts.model.Chat;

import java.util.List;


/**
 * Created by ff on 2016/9/19.
 * 聊天内容
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<Chat> list;
    private App app;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout, rightLayout;
        TextView leftTv, rightTv;
        ImageView leftIv, rightIv;

        public ViewHolder(View itemView) {
            super(itemView);

            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_chat_item_layout);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.right_chat_item_layout);
            leftTv = (TextView) itemView.findViewById(R.id.message_chat_left_item_tv);
            rightTv = (TextView) itemView.findViewById(R.id.message_chat_right_item_tv);
            leftIv = (ImageView) itemView.findViewById(R.id.headview_chat_left_item_iv);
            rightIv = (ImageView) itemView.findViewById(R.id.headview_chat_right_item_iv);
        }
    }

    public ChatAdapter(Context context, List<Chat> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Chat chat = list.get(i);
        if (chat.getType() == Chat.RECEIVED) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftIv.setImageBitmap(chat.getBitmap());
            viewHolder.leftTv.setText(chat.getMessage());
        }else if (chat.getType() == Chat.Send) {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);

            app = (App) context.getApplicationContext();
            Glide.with(this.context).load(app.getSp().getIcon()).into(viewHolder.rightIv);
//            viewHolder.rightIv.setImageBitmap(chat.getBitmap());
            viewHolder.rightTv.setText(chat.getMessage());
        }
    }
}
