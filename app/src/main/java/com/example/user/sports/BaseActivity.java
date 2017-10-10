package com.example.user.sports;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Author : yufeng.cao
 * Time : 2017.08.16 16:24
 * Description : The base of all Activity.
 */

public abstract class BaseActivity extends AppCompatActivity {

    //Context
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
    }

    /**
     * whether allow fullscreen
     */
    public void setallowFullScreen(boolean allowFullScreen) {
        if (allowFullScreen) {
            setFullScreen();
        }
    }

    /**
     * window fullscreen
     */
    private void setFullScreen() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
