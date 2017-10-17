package com.example.user.sports.view.cycle.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sports.R;
import com.example.user.sports.view.cycle.fragment.AllExerciseFragment;
import com.example.user.sports.view.cycle.fragment.NearByExerciseFragment;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class CycleFragment extends Fragment {

    private AppHeadView headView;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cycle, container, false);

        initHeaderView();
        initViews();
        return view;
    }

    private void initHeaderView() {
        headView = (AppHeadView)view.findViewById(R.id.header_cycle);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE);
        headView.setTitle("运动圈");
        headView.setLeftImage(R.drawable.ic_header_cycle);
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(),MyExerciseActivity.class,false);
            }
        });
        headView.setOnClickListenerAdd(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(),ExerciseCreateActivity.class,false);
            }
        });

    }

    private void initViews() {

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), FragmentPagerItems.with(getContext())
                .add("活动",AllExerciseFragment.class)
                .add("附近活动",NearByExerciseFragment.class)
                .create());
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.ViewPager_cycle);
        viewPager.setAdapter(adapter);
        SmartTabLayout smartTabLayout = (SmartTabLayout)view.findViewById(R.id.viewpagertab_cycle);
        smartTabLayout.setViewPager(viewPager);
    }

}
