package com.example.user.sports.view.cycle.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.view.cycle.fragment.MyExerciseFragment;
import com.example.user.sports.BaseActivity;

public class MyExerciseActivity extends BaseActivity {
    private AppHeadView headView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exercise);
        setallowFullScreen(true);
        initHeaderView();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_my_cycle);
        if (fragment == null){
            fragment = new MyExerciseFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_my_cycle,fragment)
                    .commit();
        }

    }


    private void initHeaderView() {
        headView = (AppHeadView)findViewById(R.id.header_cycle);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("我的活动");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
