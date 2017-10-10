package com.example.user.sports.view.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.view.contacts.model.Message;

import java.util.List;

/**
 * Author : user
 * Date : 17-9-6
 * Description :
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{

    private Context context;
    private List<Message> list;

    private OnItemClickLitener mOnItemClickLitener;


    public MessageAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        myViewHolder.tvName.setText(list.get(i).getName());
        myViewHolder.tvMessage.setText(list.get(i).getMessage());
        myViewHolder.tvTime.setText(list.get(i).getTime());
        myViewHolder.tvnumber.setText(""+list.get(i).getNumber());

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
        TextView tvName, tvMessage, tvTime, tvnumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name_message_item_tv);
            tvMessage = (TextView) itemView.findViewById(R.id.message_item_tv);
            tvTime = (TextView) itemView.findViewById(R.id.time_message_item_tv);
            tvnumber = (TextView) itemView.findViewById(R.id.number_message_item_tv);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
