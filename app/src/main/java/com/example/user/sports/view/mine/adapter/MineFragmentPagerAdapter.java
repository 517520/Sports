package com.example.user.sports.view.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.sports.view.mine.fragment.HistoryDetailsFragment;


/**
 * Created by user on 9/25/17.
 */
public class MineFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mStyleTitles = {"日","周","月","总"};

    public MineFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        return new HistoryDetailsFragment();
        else if (position == 1)
            return new HistoryDetailsFragment();
        else if (position == 2)
            return new HistoryDetailsFragment();
        else return new HistoryDetailsFragment();
    }

    @Override
    public int getCount() {
        return mStyleTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mStyleTitles[position];
    }
}
