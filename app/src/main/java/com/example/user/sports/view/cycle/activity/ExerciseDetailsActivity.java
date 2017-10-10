package com.example.user.sports.view.cycle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;


public class ExerciseDetailsActivity extends AppCompatActivity {
    private AppHeadView headView;
    private TextView mTextViewJoinIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initHeaderView();
        initView();


    }

    private void initView() {
        mTextViewJoinIn = (TextView)findViewById(R.id.join_in_detail_cycle_tv);
        mTextViewJoinIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"报名成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

                mTextViewJoinIn.setEnabled(false);
                mTextViewJoinIn.setText("已报名");
                mTextViewJoinIn.setBackgroundResource(R.color.already_join_in);
            }
        });

    }

    private void initHeaderView() {
        headView = (AppHeadView)findViewById(R.id.header_cycle_details);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("每日晨跑打卡");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
