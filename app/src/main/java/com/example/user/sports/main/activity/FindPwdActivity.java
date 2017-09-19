package com.example.user.sports.main.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
/**
 * Author : yufeng.cao
 * Time : 2017.09.2 17:46
 * Description : find password by phone;
 */
public class FindPwdActivity extends BaseActivity implements View.OnClickListener{

    private TextView mCancelTv;
    private EditText mPhoneEt, mConfirmEt, mPasswordEt;
    private Button mConfirmBtn, mCommitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_find_pwd);

        initView();
    }

    private void initView() {
        mCancelTv = (TextView) findViewById(R.id.cancel_findpwd_tv);
        mPasswordEt = (EditText) findViewById(R.id.password_findpwd_et);
        mPhoneEt = (EditText) findViewById(R.id.phone_findpwd_et);
        mConfirmEt = (EditText) findViewById(R.id.confirm_findpwd_et);
        mConfirmBtn = (Button) findViewById(R.id.confirm_findpwd_btn);
        mCommitBtn = (Button) findViewById(R.id.commit_findpwd_btn);

        mConfirmBtn.setOnClickListener(this);
        mCommitBtn.setOnClickListener(this);
        mCancelTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_findpwd_btn:

                break;
            case R.id.commit_findpwd_btn:

                break;
            case R.id.cancel_findpwd_tv:
                finish();
                break;
            default:
                break;
        }
    }
}
