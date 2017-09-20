package com.example.user.sports.contacts.team.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class ChooseBgActivity extends AppCompatActivity {

    private AppHeadView headview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_bg);

//        initHeadView();
    }

//    private void initHeadView() {
//        headview = (AppHeadView) findViewById(R.id.headview);
//        headview.setTvName(R.string.choose_team_background);
//        headview.setOnClickListenerBack(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
}
