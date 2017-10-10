package com.example.user.sports.mine.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sports.R;
import com.example.user.sports.mine.fragment.HistoryChartFragment;
import com.example.user.sports.mine.fragment.HistoryDetailsFragment;


/**
 * Created by user on 9/25/17.
 */

public class SportsHistoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports_history, container, false);


        FragmentManager fragmentManager = getFragmentManager();
        Fragment chartFragment = fragmentManager.findFragmentById(R.id.chart_sports_history_mine);
        Fragment detailsFragent = fragmentManager.findFragmentById(R.id.details_spotrs_history_mine);

if (chartFragment==null){
    chartFragment = new HistoryChartFragment();
    fragmentManager.beginTransaction()
            .add(R.id.chart_sports_history_mine,chartFragment)
            .commit();

}


if (detailsFragent == null){
    detailsFragent = new HistoryDetailsFragment();
    fragmentManager.beginTransaction()
            .add(R.id.details_spotrs_history_mine,detailsFragent)
            .commit();

}

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
