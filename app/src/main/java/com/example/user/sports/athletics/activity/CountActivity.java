package com.example.user.sports.athletics.activity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.athletics.receive.AdminManageReceiver;
import com.example.user.sports.athletics.stepservice.StepService;
import com.example.user.sports.athletics.stepservice.UpdateUiCallBack;
import com.example.user.sports.dialog.AbandonDialog;
import com.example.user.sports.dialog.ExitDialog;
import com.example.user.sports.ui.StepArcView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.SharePreferenceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author : yufeng.cao
 * Date : 2017.08.30 12:10
 * Description :
 */

public class CountActivity extends BaseActivity implements View.OnClickListener {

    private Button mStopBtn, mPauseBtn, mContinueBtn;
    private TextView mTimeTv, mHideTV, mStateTv, mSpeedTv, mCalorieTv, mDistanceTv;
    private ImageView mMapIv, mLockIv, mCameraIv;
    private LinearLayout mPauseLl;

    //计时器
    private Timer timer;
    private TimerTask timerTask;
    int cnt = 0;

    //锁屏
    final static int ENABLE_ADMIN = 1;
    private ComponentName mAdminName = null;

    private int state;
    public static  CountActivity instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        setallowFullScreen(true);

        initView();
        initData();
    }

    //获取当前状态显示标题栏
    @Override
    protected void onResume() {
        super.onResume();
        SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(this);
        state = sharePreferenceUtil.getState();
        switch (state) {
            case 1:
                mStateTv.setText("步行中");
                break;
            case 2:
                mStateTv.setText("跑步中");
                break;
            case 3:
                mStateTv.setText("骑行中");
                break;
            default:
                break;
        }
    }

    //初始化控件
    private void initView() {
        mStopBtn = (Button) findViewById(R.id.stop_count_btn);
        mPauseBtn = (Button) findViewById(R.id.pause_count_btn);
        mContinueBtn = (Button) findViewById(R.id.continue_count_btn);
        mTimeTv = (TextView) findViewById(R.id.time_count_tv);
        mHideTV = (TextView) findViewById(R.id.hide_count_tv);
        mStateTv = (TextView) findViewById(R.id.state_count_tv);
        mSpeedTv = (TextView) findViewById(R.id.speed_count_tv);
        mCalorieTv = (TextView) findViewById(R.id.calorie_count_tv);
        mDistanceTv = (TextView) findViewById(R.id.distance_count_tv);
        mMapIv = (ImageView) findViewById(R.id.map_count_iv);
        mLockIv = (ImageView) findViewById(R.id.lock_count_iv);
        mCameraIv = (ImageView) findViewById(R.id.camera_count_iv);
        mPauseLl = (LinearLayout) findViewById(R.id.pause_count_ll);

        mHideTV.setOnClickListener(this);
        mMapIv.setOnClickListener(this);
        mLockIv.setOnClickListener(this);
        mCameraIv.setOnClickListener(this);

        mStopBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);
        mContinueBtn.setOnClickListener(this);

        mStopBtn.setVisibility(View.INVISIBLE);
        mContinueBtn.setVisibility(View.INVISIBLE);
    }

    //初始化数据
    private void initData() {
        //用于销毁此activity
        instance = this;

        //初始化计时器
        timer = new Timer();
        startReckon();
    }

    //开始计时
    public void startReckon() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTimeTv.setText(getStringTime(cnt++));
                    }
                });
            }
        };
        timer.schedule(timerTask,0,1000);
    }

    //停止计时
    public void stopReckon() {
        if (!timerTask.cancel()) {
            timerTask.cancel();
        }
        if (cnt <= 30) {
            final AbandonDialog abandonDialog = new AbandonDialog(this);
            abandonDialog.show();
            abandonDialog.setCanceledOnTouchOutside(false);
            abandonDialog.setClickListener(new AbandonDialog.ClickListenerInterface() {
                @Override
                public void doStop() {
                    timer.cancel();
                    changeState();
                    abandonDialog.dismiss();
                }

                @Override
                public void doContinue() {
                    abandonDialog.dismiss();
                    startReckon();
                }
            });
        }else {
            haveStop();
        }
    }

    //修改状态
    private void changeState() {
        SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(this);
        sharePreferenceUtil.setState(0);
        finish();
    }

    //跳转上传数据页面
    private void haveStop() {
        Map<String, Object> map = new HashMap<>();
        map.put("distance", mDistanceTv.getText().toString());
        map.put("speed", mSpeedTv.getText().toString());
        map.put("time", mTimeTv.getText().toString());
        map.put("calorie", mCalorieTv.getText().toString());
        map.put("state",state);
        IntentUtils.turnTo(CountActivity.this, UploadActivity.class, true, map);
    }

    //暂停计时
    public void pausePeckon() {
        if (!timerTask.cancel()) {
            timerTask.cancel();
        }
    }

    //继续计时
    public void continueReckon() {
        startReckon();
    }

    private String getStringTime(int cnt) {
        int hour = cnt/3600;
        int min = cnt % 3600 / 60;
        int second = cnt % 60;
        return String.format(Locale.CHINA,"%02d:%02d:%02d",hour,min,second);
    }

    //显示设备管理器
    private void showAdminManagement() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);//打开手机设备管理器的intent
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "HAHA");
        startActivityForResult(intent, ENABLE_ADMIN);

    }

    //关屏
    private void lockScreen() {
        mAdminName = new ComponentName(this, AdminManageReceiver.class);
        DevicePolicyManager mDPM =  (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

        if (!mDPM.isAdminActive(mAdminName)) {//如果未激活
            showAdminManagement();//打开手机设备管理器
        }

        if (mDPM.isAdminActive(mAdminName)) {
            mDPM.lockNow();//执行锁屏
        }
    }

    //监听点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pause_count_btn:
                mPauseLl.setVisibility(View.INVISIBLE);
                mStopBtn.setVisibility(View.VISIBLE);
                mContinueBtn.setVisibility(View.VISIBLE);
                pausePeckon();
                break;
            case R.id.continue_count_btn:
                mPauseLl.setVisibility(View.VISIBLE);
                mStopBtn.setVisibility(View.INVISIBLE);
                mContinueBtn.setVisibility(View.INVISIBLE);
                continueReckon();
                break;
            case R.id.stop_count_btn:
                stopReckon();
                break;
            case R.id.hide_count_tv:
                moveTaskToBack(true);
                break;
            case R.id.map_count_iv:

                break;
            case R.id.lock_count_iv:
                lockScreen();
                break;
            case R.id.camera_count_iv:

                break;
            default:

                break;
        }
    }

    //点击返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final ExitDialog exitDialog = new ExitDialog(this);
            exitDialog.show();
            exitDialog.setClickListener(new ExitDialog.ClickListenerInterface() {
                @Override
                public void doStop() {
                    changeState();
                    exitDialog.dismiss();
                }

                @Override
                public void doContinue() {
                    exitDialog.dismiss();
                }
            });
        }
        return super.onKeyDown(keyCode, event);
    }
}
