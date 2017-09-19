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

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.main.presenter.SignUpPresenter;
import com.example.user.sports.main.presenter.SignUpPresenterCompl;
import com.example.user.sports.utils.IntentUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author : yufeng.cao
 * Time : 2017.09.1 20:06
 * Description : RegistActivity of the application;
 */

public class RegistActivity extends BaseActivity implements View.OnClickListener, SignUpView {

    private TextView mLoginTv, mProtationTv;
    private Button mRegistBtn, mConfirmBtn;
    private EditText mPhoneEt, mPassWordEt, mConfirmEt;
    private SignUpPresenter presenter;
    private LoadingDialog loadingDialog;

    private final static String SUCCEED = "注册成功";
    private final static String REPEAT = "用户名重复";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_regist);

        initView();
        presenter = new SignUpPresenterCompl(this);
    }

    private void initView() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this, R.style.Dialog_Fullscreen);
        }
        mLoginTv = (TextView) findViewById(R.id.login_regist_tv);
        mProtationTv = (TextView) findViewById(R.id.probation_regist_tv);
        mConfirmBtn = (Button) findViewById(R.id.confirm_regist_btn);
        mRegistBtn = (Button) findViewById(R.id.regist_btn);
        mPassWordEt = (EditText) findViewById(R.id.password_regist_et);
        mPhoneEt = (EditText) findViewById(R.id.phone_regist_et);
        mConfirmEt = (EditText) findViewById(R.id.confirm_regist_et);

        mLoginTv.setOnClickListener(this);
        mProtationTv.setOnClickListener(this);
        mRegistBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_regist_tv:
                IntentUtils.turnTo(RegistActivity.this, LoginActivity.class, true);
                break;
            case R.id.probation_regist_tv:
                IntentUtils.turnTo(RegistActivity.this, MainActivity.class, true);
                break;

            case R.id.regist_btn:
                String phone = mPhoneEt.getText().toString();
                String passWord = mPassWordEt.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(passWord)) {
                    try {
                        presenter.regist(phone, passWord);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(RegistActivity.this, "手机号或密码不能为空", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.confirm_regist_btn:

                break;
            default:
                break;
        }
    }

    @Override
    public void showDialog() {
        loadingDialog.show();
    }


    @Override
    public void mResult(String toast) {
        Message message = new Message();
        if (SUCCEED.equals(toast)) {
            message.what = 1;
        }else if (REPEAT.equals(toast)) {
            message.what = 0;
        }
        handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
            if (msg.what == 0) {
                Toast.makeText(RegistActivity.this, "用户名重复，请重新输入！", Toast.LENGTH_LONG).show();
                mPhoneEt.setText("");
                mPassWordEt.setText("");
            }else if (msg.what == 1) {
                Toast.makeText(RegistActivity.this, "恭喜您！注册成功！", Toast.LENGTH_LONG).show();
                Map<String, Object> map = new HashMap<>();
                map.put("phone", mPhoneEt.getText().toString());
                IntentUtils.turnTo(RegistActivity.this, HeadActivity.class, true, map);
            }
        }
    };
}
