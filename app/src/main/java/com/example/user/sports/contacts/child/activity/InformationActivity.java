package com.example.user.sports.contacts.child.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 19:37
 * Description :
 */
public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private AppHeadView headView;
    private Button mSendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

//        initHead();
        initView();
    }

//    private void initHead() {
//        headView = (AppHeadView) findViewById(R.id.headview);
//        headView.setTvName(R.string.detail_information);
//        headView.setOnClickListenerBack(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }

    private void initView() {
        mSendBtn = (Button) findViewById(R.id.send_information_btn);
        mSendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_information_btn:
                IntentUtils.turnTo(InformationActivity.this, ChatActivity.class, false);
                break;
            default:
                break;
        }
    }
}
