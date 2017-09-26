package com.example.user.sports.cycle.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.cycle.model.Activity;
import com.example.user.sports.cycle.model.ActivityLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 9/7/17.
 */

public class Fragment1 extends Fragment {

    //列表项还没有绑定
    //响应点击已添加,但是还没写逻辑.


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView mRecyclerView;
    private ActivityAdapter mActivityAdapter;


    private Spinner mSortSpinner;
    private List<String> sortList;
    private ArrayAdapter<String> sortspinnerAdapter;

    private Spinner mStyleSpinner;
    private List<String> styleList;
    private ArrayAdapter<String> stylespinnerAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the empty_recyclerview_new_friend for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.activity_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSortSpinner = (Spinner)view.findViewById(R.id.sort_cycle_spinner);
        mStyleSpinner = (Spinner)view.findViewById(R.id.style_cycle_spinner);
        initSpinner();

        UpdateUI();
        return view;
    }

    private void initSpinner() {
        sortList = new ArrayList<String>();
        sortList.add("按时间排序");
        sortList.add("离我最近");
        sortList.add("人气最高");
        styleList = new ArrayList<>();
        styleList.add("步行");
        styleList.add("骑行");
        styleList.add("跑步");
        styleList.add("其他");
        sortspinnerAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,sortList);
        sortspinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSortSpinner.setAdapter(sortspinnerAdapter);
        mSortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stylespinnerAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,styleList);
        stylespinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStyleSpinner.setAdapter(stylespinnerAdapter);
        mStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void UpdateUI() {
        ActivityLab activityLab = ActivityLab.get(getActivity());
        ArrayList<Activity> activities = activityLab.getActivitiesByLab();

        mActivityAdapter = new ActivityAdapter(activities);
        mRecyclerView.setAdapter(mActivityAdapter);
    }

    private class ActivityHolder extends RecyclerView.ViewHolder{

        private Activity mActivity;

        //先只绑定加入按钮,其他的之后添加
        private Button mAddActivityButton;



        public ActivityHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_activity,parent,false));
            mAddActivityButton = (Button)itemView.findViewById(R.id.add_cycle_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),mActivity.getActivity_name()+"列表子项按下去了",Toast.LENGTH_SHORT).show();

                }
            });
        }

        public void bind(Activity activity){
            mActivity = activity;
            mAddActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),mActivity.getActivity_name()+"..按钮按下去了",Toast.LENGTH_SHORT).show();
                }
            });
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