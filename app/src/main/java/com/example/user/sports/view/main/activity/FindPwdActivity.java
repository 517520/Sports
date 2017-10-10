package com.example.user.sports.view.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_0_forget_passwd;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.utils.ResultUtils;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.UploadView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Author : yufeng.cao
 * Time : 2017.09.2 17:46
 * Description : find password by phone;
 */
public class FindPwdActivity extends BaseActivity implements View.OnClickListener, UploadView{

    private TextView mCancelTv;
    private EditText mPhoneEt, mConfirmEt, mPasswordEt;
    private Button mConfirmBtn, mCommitBtn;

    //网络请求
    private UploadPresenter uploadPresenter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);
        setContentView(R.layout.activity_find_pwd);

        initView();
        uploadPresenter = new UploadPresenterImp(this);
    }

    private void initView() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
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
                String phone = mPhoneEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                try {
                    uploadPresenter.upload(UrlUtils.FORGET_PASSWORD, new Gson().toJson(new Json_0_forget_passwd(phone, password)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mPhoneEt.setText("");
                mPasswordEt.setText("");
                break;
            case R.id.cancel_findpwd_tv:
                finish();
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

        if (ResultUtils.Login.FORGETPASSWORD_RESULT_SUCCESS.equals(toast)) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_LONG).show();
            finish();
        }else if (ResultUtils.Login.FORGETPASSWORD_RESULT_FAIL_USERNOTEXIT.equals(toast)) {
            Toast.makeText(this, "未找到该用户", Toast.LENGTH_LONG).show();
        }else if (ResultUtils.Login.FORGETPASSWORD_RESULT_FAIL_DATABASEWRONG.equals(toast)) {
            Toast.makeText(this, "修改失败", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showDialog() {
        loadingDialog.show();
    }
}
