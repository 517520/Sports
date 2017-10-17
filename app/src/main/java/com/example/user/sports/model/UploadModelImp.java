package com.example.user.sports.model;

import android.os.Handler;
import android.os.Message;

import com.example.user.sports.view.UploadView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Author : user
 * Date : 17-10-10
 * Description :
 */
public class UploadModelImp implements UploadModel{

    public void upload(String url, final String request, final UploadListenner listenner) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(request)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        if (listenner != null) {
                            listenner.complete(response.body().string());
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
