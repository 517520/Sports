package com.example.user.sports.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.contacts.model.Chat;

import java.util.List;


/**
 * Created by ff on 2016/9/19.
 * 聊天内容
 */

public class ChatAdapter extends BaseAdapter {

    private List<Chat> list;
    private LayoutInflater inflater;
    private Context context;

    public ChatAdapter(List<Chat> list, Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        String s = list.get(position).getState();
        if ("from".equals(s)){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mHolderMake = null;
        if (getItemViewType(position) == 0){
            if (convertView == null){
                mHolderMake = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_chat_left, null);
                mHolderMake.imageView = (ImageView) convertView.findViewById(R.id.headview_chat_left_item_iv);
                mHolderMake.tvMessage = (TextView) convertView.findViewById(R.id.message_chat_left_item_tv);
                convertView.setTag(mHolderMake);
            }else {
                mHolderMake = (ViewHolder) convertView.getTag();
            }
            mHolderMake.imageView.setImageBitmap(list.get(position).getBitmap());
            mHolderMake.tvMessage.setText(list.get(position).getMessage());
        }else {
            if (convertView == null) {
                mHolderMake = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_chat_right, null);
                mHolderMake.imageView = (ImageView) convertView.findViewById(R.id.headview_chat_right_item_iv);
                mHolderMake.tvMessage = (TextView) convertView.findViewById(R.id.message_chat_right_item_tv);
                convertView.setTag(mHolderMake);
            } else {
                mHolderMake = (ViewHolder) convertView.getTag();
            }
            mHolderMake.imageView.setImageBitmap(list.get(position).getBitmap());
            mHolderMake.tvMessage.setText(list.get(position).getMessage());
        }
        return convertView;
    }
    private class ViewHolder{
        private TextView tvMessage;
        private ImageView imageView;
    }
}
