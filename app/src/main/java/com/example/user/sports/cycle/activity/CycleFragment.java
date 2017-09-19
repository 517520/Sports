package com.example.user.sports.cycle.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.sports.R;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class CycleFragment extends Fragment {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;
    private ImageView mAddActivityImageView;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;


    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cycle, container, false);

        initViews();

        mAddActivityImageView = (ImageView)view.findViewById(R.id.activity_add_iv);
        mAddActivityImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(),ActivityAdd.class,false);
            }
        });


        return view;
    }

    private void initViews() {
        mViewPager = (ViewPager)view.findViewById(R.id.viewPager);
        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mMyFragmentPagerAdapter);

        mTabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);

//        one.setIcon(R.mipmap.ic_launcher);
//        two.setIcon(R.mipmap.ic_launcher);
//        three.setIcon(R.mipmap.ic_launcher);

    }

}
