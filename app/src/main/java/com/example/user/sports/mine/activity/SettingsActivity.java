package com.example.user.sports.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.user.sports.R;
import com.example.user.sports.utils.IntentUtils;

public class SettingsActivity extends AppCompatActivity {
    private View mUsersBind;
    private View mLanguages;
    private View mPushNotifications;
    private View mDeleteCache;
    private Button mExit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mDeleteCache = (View)findViewById(R.id.delete_cache);
        mDeleteCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheDialogFragment cacheDialogFragment = new CacheDialogFragment();
                cacheDialogFragment.show(getFragmentManager(),null);


            }
        });

        mExit = (Button)findViewById(R.id.exitApp);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitDialogFragment exitDialogFragment = new ExitDialogFragment();
                exitDialogFragment.show(getFragmentManager(),null);
            }
        });

        mUsersBind = (View)findViewById(R.id.user_bind);
        mUsersBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(SettingsActivity.this,BindActivity.class,false);
            }
        });


    }
}

