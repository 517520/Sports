package com.example.user.sports.view.main.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_0_sign_up;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Author : yufeng.cao
 * Time : 2017.09.1 20:06
 * Description : RegistActivity of the application;
 */

public class RegistActivity extends BaseActivity implements View.OnClickListener, UploadView {

    private TextView mLoginTv, mProtationTv;
    private Button mRegistBtn, mConfirmBtn;
    private EditText mPhoneEt, mPassWordEt, mConfirmEt;
    private UploadPresenter presenter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_regist);

        initView();
        presenter = new UploadPresenterImp(this);
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
                        presenter.upload(UrlUtils.REGIST, new Gson().toJson(new Json_0_sign_up(phone, passWord)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mPhoneEt.setText("");
                    mPassWordEt.setText("");
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
    public void mResult(String result) throws JSONException {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

        JSONObject jsonObject = new JSONObject(result);
        String toast = jsonObject.getString("result");
        if (ResultUtils.Login.SIGNUP_RESULT_SUCCESS.equals(toast)) {
            Toast.makeText(RegistActivity.this, "恭喜您！注册成功！", Toast.LENGTH_LONG).show();
            Map<String, Object> map = new HashMap<>();
            map.put("phone", mPhoneEt.getText().toString());
            IntentUtils.turnTo(RegistActivity.this, HeadActivity.class, true, map);
        }else if (ResultUtils.Login.SIGNUP_RESULT_FAIL_USERNAMEREPEAT.equals(toast)) {
            Toast.makeText(RegistActivity.this, "用户名重复，请重新输入！", Toast.LENGTH_LONG).show();
        }else if (ResultUtils.Login.SIGNUP_RESULT_FAIL_DATABASEWRONG.equals(toast)) {
            Toast.makeText(RegistActivity.this, "请求错误！", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showDialog() {
        loadingDialog.show();
    }
}
