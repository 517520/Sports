package com.example.user.sports.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.main.activity.LoginActivity;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class MineFragment extends Fragment {
    /**
     * 界面布局元素
     */
    private View mView;//根布局
    private TextView mTextViewLogIn;//登录按钮
    private LinearLayout mLinearLayoutSportsHistory;//运动历史
    private LinearLayout mLinearLayoutSettings;//设置




    //其他变量






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, container, false);

        initView();

        return mView;
    }

    private void initView() {
        //  登录 转到login界面
        mTextViewLogIn = (TextView)mView.findViewById(R.id.log_in_mine_TV);
        mTextViewLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(), LoginActivity.class,false);
            }
        });

        //运动历史
        mLinearLayoutSportsHistory = (LinearLayout)mView.findViewById(R.id.sports_history_mine_linearlayout);
        mLinearLayoutSportsHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(),SportsHistoryActivity.class,false);
            }
        });

        //设置 转到SettingsActivity
        mLinearLayoutSettings = (LinearLayout)mView.findViewById(R.id.setting_mine_linearlayout);
        mLinearLayoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(),SettingsActivity.class,false);
            }
        });



    }

}