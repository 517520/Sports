package com.example.user.sports.view.athletics.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.09.15 17:44
 * Description : 开始运动倒计时
 */
public class ReadyActivity extends BaseActivity {

    private TextView mNumberTv;
    private Button mPassBtn;

    private Animation animation;
    private int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        setallowFullScreen(true);

        initView();
        initData();
    }

    private void initView() {
        mNumberTv = (TextView) findViewById(R.id.number_ready_tv);
        mPassBtn = (Button) findViewById(R.id.pass_ready_btn);
        animation = AnimationUtils.loadAnimation(this, R.anim.number_to_big);

        mPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                IntentUtils.turnTo(ReadyActivity.this, CountActivity.class, true);
            }
        });
    }

    private void initData() {
        mNumberTv.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int i = getCount();
            if (count == 0) {
                mNumberTv.setText("");
            }else {
                mNumberTv.setText("" + i);
            }
            handler.sendEmptyMessageDelayed(0, 1000);
            big();
        }
    };

    private int getCount() {
        count--;
        if (count == 0) {
            IntentUtils.turnTo(ReadyActivity.this, CountActivity.class, true);
        }
        return count;
    }

    public void big() {
        animation.reset();
        mNumberTv.startAnimation(animation);
    }

}
