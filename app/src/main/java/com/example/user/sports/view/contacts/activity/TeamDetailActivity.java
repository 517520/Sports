package com.example.user.sports.view.contacts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : yufeng.cao
 * Date : 2017.09.07 16:34
 * Description :
 */
public class TeamDetailActivity extends BaseActivity implements View.OnClickListener{

    private AppHeadView headView;
    private ImageView mHeadIv;
    private TextView mNameTv, mLocationTv, mDetailTv, mExitTv, mChatTv;


    private String name, location, power, detail, icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        setallowFullScreen(true);

        Intent intent = getIntent();
        Bundle map = intent.getExtras();
        name = map.getString("name");
        location = map.getString("location");
        detail = map.getString("detail");
        icon = map.getString("icon");
        power = "退出群聊";

        initHeadView();
        initView();
        initData();
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

    private void initData() {
        String path = UrlUtils.HOST + icon;
        Glide.with(this).load(path).into(mHeadIv);
        mNameTv.setText(name);
        mLocationTv.setText(location);
        mDetailTv.setText(detail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_team_detail_tv:
                finish();
                break;
            case R.id.chat_team_detail_tv:
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                IntentUtils.turnTo(this, ChatActivity.class, true, map);
                break;
            default:
                break;
        }
    }


}
