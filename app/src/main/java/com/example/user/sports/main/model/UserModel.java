package com.example.user.sports.main.model;

import android.util.Log;

import com.example.user.sports.utils.UrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;


import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Author : user
 * Date : 17-9-4
 * Description :
 */
public class UserModel implements User {

    private String phone;
    private String password;
    private String result;


    public UserModel() {
    }

    @Override
    public String getUserName() {
        return phone;
    }

    @Override
    public String getPassWord() {
        return password;
    }

    @Override
    public void regist(String phone, String password, final UserSignUpListenner listenner) {
        OkHttpUtils
                .post()
                .url(UrlUtils.REGIST)
                .addParams("phoneNumber", phone)
                .addParams("password", password)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        JSONObject object = new JSONObject(response.body().string());
                        result = object.getString("result");
                        if (listenner != null) {
                            listenner.complete(result);
                        }
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        result = e.getMessage().toString();
                        if (listenner != null) {
                            listenner.complete(result);
                        }
                    }

                    @Override
                    public void onResponse(Object o, int i) {

                    }
                });
    }

    @Override
    public void login(String phone, String password, final UserSignUpListenner listenner) {
        OkHttpUtils
                .post()
                .url(UrlUtils.LOGIN)
                .addParams("phoneNumber", phone)
                .addParams("password", password)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        JSONObject object = new JSONObject(response.body().string());
                        result = object.getString("result");
                        if (listenner != null) {
                            listenner.complete(result);
                        }
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        result = e.getMessage().toString();
                        if (listenner != null) {
                            listenner.complete(result);
                        }
                    }

                    @Override
                    public void onResponse(Object o, int i) {

                    }
                });
    }
}
