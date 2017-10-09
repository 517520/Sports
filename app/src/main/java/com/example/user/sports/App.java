package com.example.user.sports;

import android.app.Application;
import android.content.Context;

import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.utils.SharePreferenceUtil;

/**
 * Author : user
 * Date : 17-9-29
 * Description :
 */
public class App extends Application {
    private static App instance = null;
    private static SharePreferenceUtil sharePreferenceUtil;

    public static App getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        sharePreferenceUtil = new SharePreferenceUtil(instance);
    }

    public SharePreferenceUtil getSp() {
        return sharePreferenceUtil;
    }

}
