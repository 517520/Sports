package com.example.user.sports.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.user.sports.model.UploadModel;
import com.example.user.sports.model.UploadModelImp;
import com.example.user.sports.view.UploadView;

import org.json.JSONException;

import java.io.IOException;

/**
 * Author : user
 * Date : 17-10-10
 * Description :
 */
public class UploadPresenterImp implements UploadPresenter{

    public UploadView uploadView;
    private UploadModel uploadModel;
    private String reback;

    public UploadPresenterImp(UploadView uploadView) {
        this.uploadView = uploadView;
        uploadModel = new UploadModelImp();
    }

    @Override
    public void upload(String url, final String request) throws IOException {
        uploadView.showDialog();
        uploadModel.upload(url, request, new UploadModel.UploadListenner() {
            @Override
            public void complete(String result) {
                reback = result;
                handler.sendEmptyMessage(1);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                uploadView.mResult(reback);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
