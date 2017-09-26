package com.example.user.sports.contacts.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.09.07 16:34
 * Description :
 */
public class TeamDetailActivity extends AppCompatActivity {

    private AppHeadView headView;
    private Button mAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

//        initHeadView();
        initView();

    }

    private void initView() {
        mAddBtn = (Button) findViewById(R.id.add_team_detail_btn);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(TeamDetailActivity.this, ChatActivity.class, false);
            }
        });
    }

//    private void initHeadView() {
//        headView = (AppHeadView) findViewById(R.id.headview);
//        headView.setTvName(R.string.team_detail);
//        headView.setOnClickListenerBack(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
}
