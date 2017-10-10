package com.example.user.sports.view.mine.activity;

import android.os.Bundle;
import android.view.View;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;

public class MessageNoticeActivity extends BaseActivity {

    private AppHeadView headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setallowFullScreen(true);
        setContentView(R.layout.activity_message_notice);

        initHeadView();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("消息通知");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
