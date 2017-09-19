package com.example.user.sports.cycle.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.cycle.model.Comment;

import java.util.ArrayList;

public class ActivityDetails extends AppCompatActivity {
    private ListView mCommentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment(0,"123",null);
        Comment comment2 = new Comment(0,"456",null);
        comments.add(comment1);
        comments.add(comment2);
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));
        comments.add(new Comment(0,"123",null));




        CommentAdapter adapter = new CommentAdapter(this,comments);
        ListView listView = (ListView)findViewById(R.id.comments_listView);
        listView.setAdapter(adapter);

        //添加活动头部
        final View header = View.inflate(this,R.layout.activity_details_head,null);
        listView.addHeaderView(header);
    }


    private class CommentAdapter extends ArrayAdapter<Comment>{

        /**
         * 首先ViewHolder和getView的视图有关的部分还需要修改,因为视图还没有完整..
         * @param context
         * @param comments
         */

        public CommentAdapter(Context context, ArrayList<Comment> comments) {
            super(context, 0, comments);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Comment comment = getItem(position);
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_item_comment,parent,false);
                viewHolder.mHeadPortrait = (ImageView)convertView.findViewById(R.id.head_Portrait_iv);
                viewHolder.mUserName     = (TextView) convertView.findViewById(R.id.user_name_comment_tv);
                viewHolder.mCommentPicture = (ImageView)convertView.findViewById(R.id.comment_picture_iv);
                convertView.setTag(viewHolder);

            }else {
                viewHolder = (ViewHolder)convertView.getTag();
            }
//            viewHolder.mHeadPortrait.setImageResource(comment.getHeadPortraitResourceID());
//            viewHolder.mUserName.setText(comment.getUserName());
//
//            //预先替代的用法
//            viewHolder.mCommentPicture.setImageResource(0);


            //准确的用法
//            viewHolder.mCommentPicture.setImageResource(comment.getCommentPictureResourceIDs().get(1));
            return convertView;
        }


    }

    private static class ViewHolder{
        ImageView mHeadPortrait;
        TextView  mUserName;
        ImageView mCommentPicture;

    }
}
