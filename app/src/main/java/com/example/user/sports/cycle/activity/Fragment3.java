package com.example.user.sports.cycle.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sports.R;

/**
 * Created by user on 9/7/17.
 */

public class Fragment3 extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the empty_recyclerview_new_friend for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }



}
