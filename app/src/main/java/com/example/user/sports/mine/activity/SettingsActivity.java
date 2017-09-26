package com.example.user.sports.mine.activity;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.main.activity.LoginActivity;
import com.example.user.sports.mine.Dialog.DeleteCacheDialogFragment;
import com.example.user.sports.mine.Dialog.DialogListener;
import com.example.user.sports.utils.IntentUtils;

public class SettingsActivity extends AppCompatActivity implements DialogListener {
    private RelativeLayout mRelativeLayoutPersonalMessage;  //个人资料
    private RelativeLayout mRelativeLayoutTarget;           //我的步数目标
    private RelativeLayout mRelativeLayoutMessageNotice;    //消息通知
    private RelativeLayout mRelativeLayoutCache;            //清除缓存

    private TextView mTexiViewLogOut;                       //退出登录

    private DeleteCacheDialogFragment dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }




    private void initView() {
        //个人资料
        mRelativeLayoutPersonalMessage = (RelativeLayout)findViewById(R.id.personal_message_mine_relativelayout);
        mRelativeLayoutPersonalMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(SettingsActivity.this,PersonalMessageActivity.class,false);
            }
        });

        //我的步数目标
        mRelativeLayoutTarget = (RelativeLayout)findViewById(R.id.number_target_mine_relativelayout);
        mRelativeLayoutTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(SettingsActivity.this,NumberTargerActivity.class,false);
            }
        });

        //消息通知
        mRelativeLayoutMessageNotice = (RelativeLayout)findViewById(R.id.message_notice_mine_realtivelayout);
        mRelativeLayoutMessageNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(SettingsActivity.this,MessageNoticeActivity.class,false);

            }
        });

        //清除缓存
        mRelativeLayoutCache = (RelativeLayout)findViewById(R.id.message_cache_mine_relativelayout);
        mRelativeLayoutCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                dialog = new DeleteCacheDialogFragment();
                dialog.show(manager,null);

            }
        });

        //退出登录
        mTexiViewLogOut = (TextView)findViewById(R.id.log_out_mime_TV);
        mTexiViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(SettingsActivity.this, LoginActivity.class,false);

            }
        });


    }

    @Override
    public void onDialogPositiveClick(String data, int requestCode) {

        /**
         * 用来执行清楚缓存的方法实现
         */
        dialog.dismiss();
    }
}

