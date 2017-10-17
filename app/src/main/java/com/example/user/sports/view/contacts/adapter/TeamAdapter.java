package com.example.user.sports.view.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.sports.R;
import com.example.user.sports.model.jsonModel.Json_4_group;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.contacts.model.Team;

import java.util.List;

/**
 * Author : user
 * Date : 17-9-6
 * Description :
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder>{

    private Context context;
    private List<Json_4_group> list;

    private OnItemClickLitener mOnItemClickLitener;


    public TeamAdapter(Context context, List<Json_4_group> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        myViewHolder.tvName.setText(list.get(i).getGroupName());
        myViewHolder.tvDetail.setText(list.get(i).getGroupIntroduce());
        myViewHolder.tvLocation.setText(list.get(i).getLocation());
        String path = UrlUtils.HOST + list.get(i).getGroupIcon();
        Glide.with(this.context).load(path).into(myViewHolder.imageView);

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
        TextView tvName, tvDetail, tvLocation;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name_team_item_tv);
            tvDetail = (TextView) itemView.findViewById(R.id.detail_team_item_tv);
            tvLocation = (TextView) itemView.findViewById(R.id.location_team_item_tv);
            imageView = (ImageView) itemView.findViewById(R.id.headview_team_item_civ);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
