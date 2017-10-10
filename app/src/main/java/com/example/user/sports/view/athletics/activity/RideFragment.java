package com.example.user.sports.view.athletics.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.ui.StepArcView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.SharePreferenceUtil;

/**
 * Author : yufeng.cao
 * Date : 2017.08.30 10:44
 * Description :
 */

public class RideFragment extends Fragment {

    private View view;
    private Context context;
    private StepArcView mRideArv;
    private LinearLayout mRideLl;

    private int state;
    private SharePreferenceUtil sharePreferenceUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ride, container, false);

        initView();
        initData();
        return view;
    }

    //初始化控件
    private void initView() {
        mRideArv = (StepArcView) view.findViewById(R.id.step_ride_arv);
        mRideLl = (LinearLayout) view.findViewById(R.id.begin_ride_ll);

        mRideLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReckon();
            }
        });
    }

    //初始化数据
    private void initData() {
        mRideArv.setParams("今日骑行", "Km", "Riding", "等级：轻度活跃");
        mRideArv.setCurrentCount(5000,0);

        sharePreferenceUtil = new SharePreferenceUtil(this.getActivity());
    }

    //判断当前状态
    public void startReckon() {
        state = sharePreferenceUtil.getState();
        switch (state) {
            case 0:
                sharePreferenceUtil.setState(3);
                IntentUtils.turnTo(getActivity(), ReadyActivity.class, false);
                break;
            case 1:
                Toast.makeText(this.getActivity(), "正在步行中，请结束步行再开始骑行！", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(this.getActivity(), "正在跑步中，请结束跑步再开始骑行！", Toast.LENGTH_LONG).show();
                break;
            case 3:
                IntentUtils.turnTo(this.getActivity(), CountActivity.class, false);
                break;
            default:
                break;
        }
    }
}
