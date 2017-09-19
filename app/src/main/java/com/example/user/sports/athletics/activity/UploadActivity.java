package com.example.user.sports.athletics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.ExitDialog;
import com.example.user.sports.dialog.GiveUpDialog;
import com.example.user.sports.utils.SharePreferenceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : yufeng.cao
 * Date : 2017.09.18 10:26
 * Description :
 */

public class UploadActivity extends BaseActivity implements View.OnClickListener {

    private String distance, speed, time, calorie;
    private TextView mUnSaveTv, mDistanceTv, mSpeedTv, mTimeTv, mCalorieTv, mStateTv;
    private Button mContinueBtn, mFinishBtn, mUploadBtn;

    private int state;
    private SharePreferenceUtil sharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setallowFullScreen(true);
        setContentView(R.layout.activity_upload);

        Intent intent = getIntent();
        Bundle map = intent.getExtras();
        distance = map.getString("distance");
        speed = map.getString("speed");
        time = map.getString("time");
        calorie = map.getString("calorie");
        sharePreferenceUtil = new SharePreferenceUtil(this);
        state = sharePreferenceUtil.getState();

        initView();
        initData();
    }

    private void initView() {
        mStateTv = (TextView) findViewById(R.id.state_upload_tv);
        mUnSaveTv = (TextView) findViewById(R.id.unsave_upload_tv);
        mDistanceTv = (TextView) findViewById(R.id.distance_upload_tv);
        mSpeedTv = (TextView) findViewById(R.id.speed_upload_tv);
        mTimeTv = (TextView) findViewById(R.id.time_upload_tv);
        mCalorieTv = (TextView) findViewById(R.id.calorie_upload_tv);
        mContinueBtn = (Button) findViewById(R.id.continue_upload_btn);
        mFinishBtn = (Button) findViewById(R.id.finish_upload_btn);
        mUploadBtn = (Button) findViewById(R.id.upload_btn);

        mUnSaveTv.setOnClickListener(this);
        mContinueBtn.setOnClickListener(this);
        mFinishBtn.setOnClickListener(this);
        mUploadBtn.setOnClickListener(this);

        mFinishBtn.setVisibility(View.GONE);
        mContinueBtn.setVisibility(View.GONE);
    }

    private void initData() {
        mDistanceTv.setText(distance);
        mSpeedTv.setText(speed);
        mTimeTv.setText(time);
        mCalorieTv.setText(calorie);

        switch (state) {
            case 1:
                mStateTv.setText("步行结束");
                break;
            case 2:
                mStateTv.setText("跑步结束");
                break;
            case 3:
                mStateTv.setText("骑行结束");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unsave_upload_tv:
                giveUp();
                break;
            case R.id.continue_upload_btn:

                break;
            case R.id.upload_btn:
                upload();
                mUploadBtn.setVisibility(View.INVISIBLE);
                mContinueBtn.setVisibility(View.VISIBLE);
                mFinishBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.finish_upload_btn:
                finish();
                break;
            default:
                break;
        }
    }

    //上传数据
    private void upload() {

    }

    //是否要放弃询问弹出框
    private void giveUp() {
        final GiveUpDialog giveUpDialog = new GiveUpDialog(this);
        giveUpDialog.show();
        giveUpDialog.setClickListener(new GiveUpDialog.ClickListenerInterface() {
            @Override
            public void doConsider() {
                giveUpDialog.dismiss();
            }

            @Override
            public void doGiveUp() {
                giveUpDialog.dismiss();
                sharePreferenceUtil.setState(0);
                finish();
            }
        });
    }

    //点击返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            giveUp();
        }
        return super.onKeyDown(keyCode, event);
    }
}
