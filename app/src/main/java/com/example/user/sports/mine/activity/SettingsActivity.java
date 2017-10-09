package com.example.user.sports.mine.activity;

import android.app.FragmentManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.main.activity.LoginActivity;
import com.example.user.sports.main.activity.MainActivity;
import com.example.user.sports.mine.Dialog.DeleteCacheDialogFragment;
import com.example.user.sports.mine.Dialog.DialogListener;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.DataCleanManager;
import com.example.user.sports.utils.IntentUtils;

public class SettingsActivity extends BaseActivity implements DialogListener, View.OnClickListener {
    private RelativeLayout mRelativeLayoutPersonalMessage;  //个人资料
    private RelativeLayout mRelativeLayoutTarget;           //我的步数目标
    private RelativeLayout mRelativeLayoutMessageNotice;    //消息通知
    private RelativeLayout mRelativeLayoutCache;            //清除缓存
    private TextView mTexiViewLogOut, mCacheTv;                       //退出登录
    private DeleteCacheDialogFragment dialog;

    private DataCleanManager dataCleanManager;

    private AppHeadView headView;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setallowFullScreen(true);
        setContentView(R.layout.activity_settings);

        initHeadView();
        initView();
        initData();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("设置");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        dataCleanManager = new DataCleanManager();
        app = (App) getApplicationContext();
        mRelativeLayoutPersonalMessage = (RelativeLayout)findViewById(R.id.personal_message_mine_relativelayout);
        mRelativeLayoutTarget = (RelativeLayout)findViewById(R.id.number_target_mine_relativelayout);
        mRelativeLayoutMessageNotice = (RelativeLayout)findViewById(R.id.message_notice_mine_realtivelayout);
        mRelativeLayoutCache = (RelativeLayout)findViewById(R.id.message_cache_mine_relativelayout);
        mTexiViewLogOut = (TextView)findViewById(R.id.log_out_mime_TV);
        mCacheTv = (TextView) findViewById(R.id.cache_setting_tv);

        if (app.getSp().getIsLogin()) {
            mTexiViewLogOut.setVisibility(View.VISIBLE);
        }

        mRelativeLayoutPersonalMessage.setOnClickListener(this);
        mRelativeLayoutTarget.setOnClickListener(this);
        mRelativeLayoutMessageNotice.setOnClickListener(this);
        mRelativeLayoutCache.setOnClickListener(this);
        mTexiViewLogOut.setOnClickListener(this);
    }

    private void initData() {
        try {
            mCacheTv.setText(dataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDialogPositiveClick(String data, int requestCode) {
        dataCleanManager.clearAllCache(this);
        initData();
        dialog.dismiss();
    }

    //显示Snackbar
    private void showSnackBar() {
        Snackbar.make(mCacheTv, "您还未登录，前往登录吧！", Snackbar.LENGTH_LONG)
                .setAction("登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.instance.finish();
                        IntentUtils.turnTo(SettingsActivity.this, LoginActivity.class, true);
                    }
                }).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_message_mine_relativelayout:
                if (app.getSp().getIsLogin()){
                    IntentUtils.turnTo(SettingsActivity.this,PersonalMessageActivity.class,false);
                }else {
                    showSnackBar();
                }
                break;
            case R.id.number_target_mine_relativelayout:
                if (app.getSp().getIsLogin()){
                    IntentUtils.turnTo(SettingsActivity.this,NumberTargerActivity.class,false);
                }else {
                    showSnackBar();
                }
                break;
            case R.id.message_notice_mine_realtivelayout:
                IntentUtils.turnTo(SettingsActivity.this,MessageNoticeActivity.class,false);
                break;
            case R.id.message_cache_mine_relativelayout:
                FragmentManager manager = getFragmentManager();
                dialog = new DeleteCacheDialogFragment();
                dialog.show(manager,null);
                break;
            case R.id.log_out_mime_TV:
                app.getSp().exit();
                MainActivity.instance.finish();
                IntentUtils.turnTo(SettingsActivity.this, LoginActivity.class, true);
                break;
            default:
                break;
        }
    }
}

