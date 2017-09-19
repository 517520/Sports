package com.example.user.sports.cycle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.cycle.model.Activity;
import com.example.user.sports.cycle.model.ActivityLab;
import com.example.user.sports.cycle.model.Comment;

import java.util.ArrayList;

/**
 * Created by user on 9/7/17.
 */
public class Fragment2 extends Fragment {

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment2, container, false);
        ActivityLab activityLab = ActivityLab.get(getActivity());
        ArrayList<Activity> activities = activityLab.getActivitiesByLab();
        AddByMeAdapter addByMeAdapter = new AddByMeAdapter(getActivity(),activities);
        mListView = (ListView)view.findViewById(R.id.activity_add_by_user_lv);
        mListView.setAdapter(addByMeAdapter);
        mListView.setEmptyView(view.findViewById(R.id.empty_view));
        return view;

    }


    private class AddByMeAdapter extends ArrayAdapter<Activity> {

        /**
         * 首先ViewHolder和getView的视图有关的部分还需要修改,因为视图还没有完整..
         * @param context
         * @param activities
         */

        public AddByMeAdapter(Context context, ArrayList<Activity> activities) {
            super(context,0, activities);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Activity activity = getItem(position);
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_item_activity,parent,false);
//                viewHolder.mHeadPortrait = (ImageView)convertView.findViewById(R.id.head_Portrait_iv);
//                viewHolder.mUserName     = (TextView) convertView.findViewById(R.id.user_name_comment_tv);
//                viewHolder.mCommentPicture = (ImageView)convertView.findViewById(R.id.comment_picture_iv);
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
