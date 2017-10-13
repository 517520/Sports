package com.example.user.sports.view.main.activity;

import android.os.Bundle;
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
import com.example.user.sports.model.jsonModel.Json_0_sign_in;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.ResultUtils;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.UploadView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Author : yufeng.cao
 * Time : 2017.09.1 20:06
 * Description : LoginActivity of the application;
 */


public class LoginActivity extends BaseActivity implements View.OnClickListener, UploadView {

    private TextView mRegistTv, mFindPwdTv, mProbationTv;
    private EditText mPhoneEt, mPasswordEt;
    private Button mLoginBtn;

    private App app;

    private UploadPresenter presenter;
    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_login);

        initView();
        presenter = new UploadPresenterImp(this);
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
                        presenter.upload(UrlUtils.LOGIN, new Gson().toJson(new Json_0_sign_in(phone, password)));
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
    public void mResult(String result) throws JSONException {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

        JSONObject jsonObject = new JSONObject(result);
        String toast = jsonObject.getString("result");

        if (ResultUtils.Login.SIGNIN_RESULT_SUCCESS.equals(toast)) {
            rememberToSP();
            Toast.makeText(LoginActivity.this, "欢迎您!", Toast.LENGTH_LONG).show();
            IntentUtils.turnTo(LoginActivity.this, MainActivity.class, true);
            WelcomeActivity.instance.finish();
        }else if (ResultUtils.Login.SIGNIN_RESULT_FAIL_USERNOTEXIT.equals(toast)) {
            Toast.makeText(LoginActivity.this, "用户不存在！请重新登录!", Toast.LENGTH_LONG).show();
        }else if (ResultUtils.Login.SIGNIN_RESULT_FAIL_WRONGPASSWORD.equals(toast)) {
            Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_LONG).show();
        }
    }

    //保存登录信息到本地
    private void rememberToSP() {
        app.getSp().setIsLogin(true);
        app.getSp().setPhone(mPhoneEt.getText().toString());
        app.getSp().setPassword(mPasswordEt.getText().toString());
        app.getSp().setState(0);
        mPhoneEt.setText("");
        mPasswordEt.setText("");
    }

    @Override
    public void showDialog() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }
}
