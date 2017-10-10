package com.example.user.sports.view.athletics.activity;

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

public class RunFragment extends Fragment {
    private View view;
    private StepArcView mRunArv;
    private LinearLayout mRunll;

    private int state;
    private SharePreferenceUtil sharePreferenceUtil;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_run, container, false);

        initView();
        initData();
        return view;
    }


    //初始化控件
    private void initView() {
        mRunArv = (StepArcView) view.findViewById(R.id.step_run_arv);
        mRunll = (LinearLayout) view.findViewById(R.id.begin_run_ll);

        mRunll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReckon();
            }
        });
    }


    private void initData() {
        mRunArv.setParams("今日跑步", "Km", "Running", "等级：轻度活跃");
        mRunArv.setCurrentCount(5000,0);

        sharePreferenceUtil = new SharePreferenceUtil(this.getActivity());
    }

    //判断当前状态
    public void startReckon() {
        state = sharePreferenceUtil.getState();
        switch (state) {
            case 0:
                sharePreferenceUtil.setState(2);
                IntentUtils.turnTo(getActivity(), ReadyActivity.class, false);
                break;
            case 1:
                Toast.makeText(this.getActivity(), "正在步行中，请结束步行再开始跑步！", Toast.LENGTH_LONG).show();
                break;
            case 2:
                IntentUtils.turnTo(this.getActivity(), CountActivity.class, false);
                break;
            case 3:
                Toast.makeText(this.getActivity(), "正在骑行中，请结束骑行再开始跑步！", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
