package com.example.user.sports.view.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Time : 2017.09.1 18:06
 * Description : WelcomeActivity of the application;
 */
public class WelcomeActivity extends BaseActivity implements View.OnClickListener{

    private Button mRegistBtn, mLoginBtn;
    private App app;
    public static  WelcomeActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_welcome);

        initView();
        initData();
    }

    private void initView() {
        mRegistBtn = (Button) findViewById(R.id.regist_welcome_btn);
        mLoginBtn = (Button) findViewById(R.id.login_welcome_btn);
        app = (App) getApplicationContext();

        mRegistBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
    }

    private void initData() {
        instance = this;

        if (app.getSp().getIsLogin()) {
            Toast.makeText(this, "您已经登录，欢迎回来！", Toast.LENGTH_LONG).show();
            IntentUtils.turnTo(this, MainActivity.class, true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_welcome_btn:
                IntentUtils.turnTo(WelcomeActivity.this, RegistActivity.class, false);
                break;
            case R.id.login_welcome_btn:
                IntentUtils.turnTo(WelcomeActivity.this, LoginActivity.class, false);
                break;
            default:
                break;
        }
    }

}
