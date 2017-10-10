package com.example.user.sports.model;

/**
 * Author : user
 * Date : 17-10-10
 * Description :
 */
public interface UploadModel {
    void upload(String url, String request, UploadListenner listenner);

    interface UploadListenner {
        void complete(String result);
    }
}
