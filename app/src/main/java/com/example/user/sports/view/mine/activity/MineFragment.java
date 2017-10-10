package com.example.user.sports.view.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.view.main.activity.LoginActivity;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.CheckInView;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class MineFragment extends Fragment implements View.OnClickListener{
    /**
     * 界面布局元素
     */
    private View mView;//根布局
    private TextView mTextViewLogIn;//登录按钮
    private LinearLayout mLinearLayoutSportsHistory;//运动历史
    private LinearLayout mLinearLayoutSettings;//设置
    private AppHeadView headView;
    private CheckInView mCheckInView;
    private Button mButtonCheckInView;

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, container, false);

        initHeadView();
        initView();
        return mView;
    }

    private void initHeadView() {
        headView = (AppHeadView) mView.findViewById(R.id.headview);
        headView.setVisibility(View.GONE,View.GONE,View.GONE,View.GONE);
        headView.setTitle("我");
    }

    private void initView() {
        mTextViewLogIn = (TextView)mView.findViewById(R.id.log_in_mine_TV);
        mLinearLayoutSportsHistory = (LinearLayout)mView.findViewById(R.id.sports_history_mine_linearlayout);
        mLinearLayoutSettings = (LinearLayout)mView.findViewById(R.id.setting_mine_linearlayout);
        mCheckInView = (CheckInView)mView.findViewById(R.id.check_in_mine);
        mButtonCheckInView = (Button)mView.findViewById(R.id.check_in_mine_Btn);

        mTextViewLogIn.setOnClickListener(this);
        mLinearLayoutSportsHistory.setOnClickListener(this);
        mLinearLayoutSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_in_mine_TV:
                IntentUtils.turnTo(getActivity(), LoginActivity.class, false);
                break;
            case R.id.sports_history_mine_linearlayout:
                IntentUtils.turnTo(getActivity(),SportsHistoryActivity.class,false);
                break;
            case R.id.setting_mine_linearlayout:
                IntentUtils.turnTo(getActivity(),SettingsActivity.class,false);
                break;
            default:
                break;
        }
    }
}