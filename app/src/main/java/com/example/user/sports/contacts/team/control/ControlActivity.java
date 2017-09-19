package com.example.user.sports.contacts.team.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.contacts.team.create.CreateActivity;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class ControlActivity extends AppCompatActivity {

    private AppHeadView headView;
    private RecyclerView mControlRv;
    private TextView mCreateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        initHeadView();
        initView();
        initData();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setTvName(R.string.control_team);
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
//        mControlRv = (RecyclerView) findViewById(R.id.control_rv);
        mCreateTv = (TextView) findViewById(R.id.create_control_tv);
        mCreateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(ControlActivity.this, CreateActivity.class, false);
            }
        });
    }

    private void initData() {

    }
}
