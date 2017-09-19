package com.example.user.sports.athletics.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.athletics.adapter.MFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class AthleticsFragment extends Fragment {

    private View view;
    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;

    private ViewPager viewPager;
    private TextView mRunTv, mWalkTv, mRideTv;
    private ImageView cursor;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;

    //动画图片宽度
    private int bmpW;

    //横线宽度
    private int lineW;

    //当前页卡编号
    private int currIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_athletics, container, false);

        initView();
        initImageView();
        initFragments();
        initViewPager();

        return view;
    }

    //初始化控件
    private void initView() {
        mWalkTv = (TextView)view.findViewById(R.id.walk_athletics_tv);
        mRunTv = (TextView) view.findViewById(R.id.run_athletics_tv);
        mRideTv = (TextView) view.findViewById(R.id.ride_athletics_tv);
    }

    //初始化横线布局
    private void initImageView() {
        cursor = (ImageView) view.findViewById(R.id.line_athletics_iv);
        DisplayMetrics dm = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        currIndex = 0;
        lineW = (screenW / 6);
        //设置动画图片宽度
//        this.setBmpW(cursor, bmpW);
        this.setBmpW(cursor,lineW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) ((screenW / 12.0)*4);
        position_two = (int) ((screenW / 12.0)*8);
    }

    //初始化fragments
    private void initFragments(){
        fragments = new ArrayList<Fragment>();
        fragments.add(new WalkFragment());
        fragments.add(new RunFragment());
        fragments.add(new RideFragment());
        fragmentManager = getChildFragmentManager();
    }

    //初始化viewpage
    private void initViewPager() {
        viewPager = (ViewPager) view.findViewById(R.id.content_athletics_vp);
        viewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragments));

        //让ViewPager缓存3个页面
        viewPager.setOffscreenPageLimit(2);

        viewPager.setCurrentItem(currIndex);

        //将顶部文字恢复默认值
        resetTextView();
        if (currIndex == 0){
            mWalkTv.setTextColor(getResources().getColor(R.color.white));
        }else if (currIndex == 1){
            mRunTv.setTextColor(getResources().getColor(R.color.white));
        }else if (currIndex == 2){
            mRideTv.setTextColor(getResources().getColor(R.color.white));
        }
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    //设置横线样式
    private void setBmpW(ImageView imageView, int mWidth) {
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }

    //重置字体颜色
    private void resetTextView() {
        mWalkTv.setTextColor(this.getResources().getColor(R.color.false_white));
        mRunTv.setTextColor(this.getResources().getColor(R.color.false_white));
        mRideTv.setTextColor(this.getResources().getColor(R.color.false_white));
    }

    //监听页面改变事件
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            resetTextView();
            switch (position) {
                //当前为页卡1
                case 0:
                    if (currIndex == 1) {//从页卡2跳转转到页卡1
                        animation = new TranslateAnimation(position_one, offset, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(position_two, offset, 0, 0);
                    } else if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, offset, 0, 0);
                    }
                    mWalkTv.setTextColor(getResources().getColor(R.color.white));
                    break;

                //当前为页卡2
                case 1:
                    if (currIndex == 0) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    }
                    mRunTv.setTextColor(getResources().getColor(R.color.white));
                    break;

                //当前为页卡3
                case 2:
                    if (currIndex == 0) {//从页卡1跳转到页卡3
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                    }
                    mRideTv.setTextColor(getResources().getColor(R.color.white));
                    break;
            }
            currIndex = position;
            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(100);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
