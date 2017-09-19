package com.example.user.sports.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_welcome);

        initView();
    }

    private void initView() {
        mRegistBtn = (Button) findViewById(R.id.regist_welcome_btn);
        mLoginBtn = (Button) findViewById(R.id.login_welcome_btn);

        mRegistBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
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
