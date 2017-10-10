package com.example.user.sports.view.cycle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sports.R;
import com.example.user.sports.view.cycle.model.Activity;
import com.example.user.sports.view.cycle.model.ActivityLab;
import com.example.user.sports.view.cycle.model.User;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 9/28/17.
 */

public class AllExerciseFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    private ActivityAdapter mActivityAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cycle_all_exercise, container, false);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.RecyclerView_cycle_all_exercise);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));                //设置为Linear的方式.
        UpdateUI();
    }

    private void UpdateUI() {
        ActivityLab activityLab = ActivityLab.get(getActivity());
        ArrayList<Activity> activities = activityLab.getActivitiesByLab();
        for(int i=0; i<5;i++){
            Calendar a = Calendar.getInstance();
            Calendar b = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            a.set(2013,12,11,1,23);
            b.set(2014,12,11,2,23);
            c.set(2015,12,11,3,23);
            activities.add(new Activity("Knight"+i,"跑步"+i,a,b,"惠州"+i,0,"人在塔在",new ArrayList<User>()));
        }


        mActivityAdapter = new ActivityAdapter(activities);
        mRecyclerView.setAdapter(mActivityAdapter);

    }


    private class ActivityHolder extends RecyclerView.ViewHolder{

        private Activity mActivity;

        //先只绑定加入按钮,其他的之后添加




        public ActivityHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_activity,parent,false));
//            mAddActivityButton = (Button)itemView.findViewById(R.id.add_cycle_button);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getActivity(),mActivity.getActivity_name()+"列表子项按下去了",Toast.LENGTH_SHORT).show();
//
//                }
//            });
        }

        public void bind(Activity activity){
            mActivity = activity;
//            mAddActivityButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getActivity(),mActivity.getActivity_name()+"..按钮按下去了",Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder>{
        private ArrayList<Activity> mActivities;

        public ActivityAdapter(ArrayList<Activity> activities){
            mActivities = activities;
        }

        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ActivityHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(ActivityHolder holder, int position) {
            Activity activity = mActivities.get(position);
            holder.bind(activity);

        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }
    }

}
