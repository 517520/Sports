package com.example.user.sports.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class MineFragment extends Fragment implements View.OnClickListener{

    private View view;
    private TextView mCheckInTv;
    private Button mCheckInBtn;
    private LinearLayout mSettingsLl, mMyExerciseLl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);

        initView();

        return view;
    }

    private void initView() {
        mCheckInTv = (TextView) view.findViewById(R.id.chech_in_mine_tv);
        mCheckInBtn = (Button) view.findViewById(R.id.chech_in_mine_btn);
        mSettingsLl = (LinearLayout) view.findViewById(R.id.setting_mine_ll);
        mMyExerciseLl = (LinearLayout) view.findViewById(R.id.my_exercise_mine_ll);

        mCheckInTv.setOnClickListener(this);
        mCheckInBtn.setOnClickListener(this);
        mSettingsLl.setOnClickListener(this);
        mMyExerciseLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chech_in_mine_tv:
                IntentUtils.turnTo(getActivity(), RuleActivity.class, false);
                break;
            case R.id.setting_mine_ll:
                IntentUtils.turnTo(getActivity(), SettingsActivity.class, false);
                break;
            case R.id.my_exercise_mine_ll:

                break;
            case R.id.chech_in_mine_btn:
                mCheckInBtn.setText("已签到3天");
                break;
            default:

                break;
        }
    }
}