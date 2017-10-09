package com.example.user.sports.main.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.main.presenter.SignUpPresenter;
import com.example.user.sports.main.presenter.SignUpPresenterCompl;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.SharePreferenceUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : yufeng.cao
 * Time : 2017.09.1 20:06
 * Description : LoginActivity of the application;
 */


public class LoginActivity extends BaseActivity implements View.OnClickListener, SignUpView {

    private TextView mRegistTv, mFindPwdTv, mProbationTv;
    private EditText mPhoneEt, mPasswordEt;
    private Button mLoginBtn;
    private SignUpPresenter presenter;

    private final static String SUCCEED = "登录成功";
    private final static String FALSE = "密码错误";
    private final static String NULL = "用户不存在";

    private App app;
    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_login);

        initView();
        presenter = new SignUpPresenterCompl(this);
    }

    private void initView() {
        app = (App) getApplicationContext();
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this, R.style.Dialog_Fullscreen);
        }

        mRegistTv = (TextView) findViewById(R.id.regist_login_tv);
        mFindPwdTv = (TextView) findViewById(R.id.findpwd_login_tv);
        mProbationTv = (TextView) findViewById(R.id.probation_login_tv);
        mPhoneEt = (EditText) findViewById(R.id.phone_login_et);
        mPasswordEt = (EditText) findViewById(R.id.password_login_et);
        mLoginBtn = (Button) findViewById(R.id.login_btn);

        mRegistTv.setOnClickListener(this);
        mFindPwdTv.setOnClickListener(this);
        mProbationTv.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_login_tv:
                IntentUtils.turnTo(LoginActivity.this, RegistActivity.class, true);
                break;
            case R.id.findpwd_login_tv:
                IntentUtils.turnTo(LoginActivity.this, FindPwdActivity.class, false);
                break;
            case R.id.probation_login_tv:
                IntentUtils.turnTo(LoginActivity.this, MainActivity.class, true);
                break;
            case R.id.login_btn:
                String phone = mPhoneEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                    try {
                        presenter.login(phone, password);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "手机号或密码不能为空", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void mResult(int result) {
        Message message = new Message();
        message.what = result;
        handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }

            switch (msg.what) {
                case -1:
                    Toast.makeText(LoginActivity.this, "用户不存在！请重新登录!", Toast.LENGTH_LONG).show();
                    mPhoneEt.setText("");
                    mPasswordEt.setText("");
                    break;
                case 0:
                    Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_LONG).show();
                    mPhoneEt.setText("");
                    mPasswordEt.setText("");
                    break;
                case 1:
                    rememberToSP();
                    Toast.makeText(LoginActivity.this, "欢迎您!", Toast.LENGTH_LONG).show();
                    IntentUtils.turnTo(LoginActivity.this, MainActivity.class, true);
                    WelcomeActivity.instance.finish();
                    break;
                default:
                    break;
            }
        }
    };

    //保存登录信息到本地
    private void rememberToSP() {
        app.getSp().setIsLogin(true);
        app.getSp().setPhone(mPhoneEt.getText().toString());
        app.getSp().setPassword(mPasswordEt.getText().toString());
        app.getSp().setState(0);
    }

    @Override
    public void showDialog() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }
}
