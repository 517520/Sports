package com.example.user.sports.main.model;

import android.util.Log;

import com.example.user.sports.jsonModel.Json_0_sign_in;
import com.example.user.sports.jsonModel.Json_0_sign_up;
import com.example.user.sports.utils.UrlUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;


import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;


/**
 * Author : user
 * Date : 17-9-4
 * Description :
 */
public class UserModel implements User {
    private String result;

    @Override
    public void regist(String phone, String password, final UserSignUpListenner listenner) throws IOException {
        OkHttpUtils
                .postString()
                .url(UrlUtils.REGIST)
                .content(new Gson().toJson(new Json_0_sign_up(phone, password)))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        result = jsonObject.getString("result");
                        if (listenner != null) {
                            listenner.complete(result);
                        }
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Object o, int i) {

                    }
                });
    }

    @Override
    public void login(String phone, String password, final UserSignUpListenner listenner) {
        OkHttpUtils
                .postString()
                .url(UrlUtils.LOGIN)
                .content(new Gson().toJson(new Json_0_sign_in(phone, password)))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
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
                    }

                    @Override
                    public void onResponse(Object o, int i) {

                    }
                });
    }
}
