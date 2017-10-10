package com.example.user.sports.view.analysis.activity;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sports.R;
import com.example.user.sports.view.analysis.fragment.BodyWeightFragment;
import com.example.user.sports.view.analysis.fragment.CaloriesFragment;
import com.example.user.sports.view.analysis.fragment.KiloFragment;
import com.example.user.sports.view.analysis.fragment.WalkNumberFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class AnalysisFragment extends Fragment {

    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_analysis, container, false);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), FragmentPagerItems.with(getContext())
                .add("步数",WalkNumberFragment.class)
                .add("大卡",CaloriesFragment.class)
                .add("体重",BodyWeightFragment.class)
                .add("公里",KiloFragment.class)
                .create());
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.viewPagerAna);
        viewPager.setAdapter(adapter);

        SmartTabLayout smartTabLayout = (SmartTabLayout)view.findViewById(R.id.SmartTablelayout);
        smartTabLayout.setViewPager(viewPager);
        SmartTabLayout smartTabLayout2 = (SmartTabLayout)view.findViewById(R.id.SmartTablelayout2);
        smartTabLayout2.setViewPager(viewPager);


        return view;
    }

}
