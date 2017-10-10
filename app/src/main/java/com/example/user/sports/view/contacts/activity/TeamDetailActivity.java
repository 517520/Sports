package com.example.user.sports.view.contacts.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.09.07 16:34
 * Description :
 */
public class TeamDetailActivity extends BaseActivity implements View.OnClickListener{

    private AppHeadView headView;
    private ImageView mHeadIv;
    private TextView mNameTv, mLocationTv, mDetailTv, mExitTv, mChatTv;


    private String name = "动次大次", location = "TCL科技大厦", power = "退出群聊", detail ="去你妈的垃圾东西";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        setallowFullScreen(true);
        initHeadView();
        initView();

    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("群组详情");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mHeadIv = (ImageView) findViewById(R.id.head_team_detail_iv);
        mNameTv = (TextView) findViewById(R.id.name_team_detail_tv);
        mLocationTv = (TextView) findViewById(R.id.location_team_detail_tv);
        mDetailTv = (TextView) findViewById(R.id.detail_team_detail_tv);
        mExitTv = (TextView) findViewById(R.id.exit_team_detail_tv);
        mChatTv = (TextView) findViewById(R.id.chat_team_detail_tv);

        mNameTv.setText(name);
        mLocationTv.setText(location);
        mDetailTv.setText(detail);
        mExitTv.setText(power);


        mExitTv.setOnClickListener(this);
        mChatTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_team_detail_tv:
                finish();
                break;
            case R.id.chat_team_detail_tv:
                IntentUtils.turnTo(this, ChatActivity.class, true);
                break;
            default:
                break;
        }
    }


}
