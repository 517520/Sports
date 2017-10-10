package com.example.user.sports.view.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:16
 * Desciption : The adapter of MainFragments bottom.
 */

public class MainFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MainFragmentPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
