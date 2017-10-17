package com.example.user.sports.view.mine.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.sports.App;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_5_detail;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.ui.CircleImageView;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.UploadView;
import com.example.user.sports.view.main.activity.LoginActivity;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.CheckInView;
import com.example.user.sports.utils.IntentUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class MineFragment extends Fragment implements View.OnClickListener, UploadView{
    /**
     * 界面布局元素
     */
    private View mView;//根布局
    private TextView mTextViewLogIn;//登录按钮
    private TextView mNickName;
    private LinearLayout mLinearLayoutSportsHistory;//运动历史
    private LinearLayout mLinearLayoutSettings;//设置
    private AppHeadView headView;
    private CheckInView mCheckInView;
    private Button mButtonCheckInView;
    private CircleImageView mHeadViewCiv;
    private RelativeLayout relativeLayout;

    private App app;
    private LoadingDialog loadingDialog;
    private UploadPresenter presenter;

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
        mCheckInView.setCheckInDayNumber(2);
        return mView;
    }

    private void initData() {
        if (app.getSp().getIsLogin()) {
            try {
                presenter.upload(UrlUtils.DETAIL, new Gson().toJson(new Json_5_detail(app.getSp().getPhone())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initHeadView() {
        headView = (AppHeadView) mView.findViewById(R.id.headview);
        headView.setVisibility(View.GONE,View.GONE,View.GONE,View.GONE);
        headView.setTitle("我");
    }

    private void initView() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this.getActivity());
        }
        app = (App) getActivity().getApplicationContext();
        presenter = new UploadPresenterImp(this);


        mNickName = (TextView)mView.findViewById(R.id.nickname_mine_tv);
        mTextViewLogIn = (TextView)mView.findViewById(R.id.log_in_mine_TV);
        relativeLayout = (RelativeLayout)mView.findViewById(R.id.my_message_mine_relativeLayout);
        mLinearLayoutSportsHistory = (LinearLayout)mView.findViewById(R.id.sports_history_mine_linearlayout);
        mLinearLayoutSettings = (LinearLayout)mView.findViewById(R.id.setting_mine_linearlayout);
        mCheckInView = (CheckInView)mView.findViewById(R.id.check_in_mine);
        mButtonCheckInView = (Button)mView.findViewById(R.id.check_in_mine_Btn);
        mHeadViewCiv = (CircleImageView) mView.findViewById(R.id.circle_image_cycle_imageView);

        mTextViewLogIn.setOnClickListener(this);
        mLinearLayoutSportsHistory.setOnClickListener(this);
        mLinearLayoutSettings.setOnClickListener(this);
        mButtonCheckInView.setOnClickListener(this);

        if (app.getSp().getIsLogin()) {
            mTextViewLogIn.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            initData();
        }else {
            mTextViewLogIn.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_in_mine_TV:
                IntentUtils.turnTo(getActivity(), LoginActivity.class, false);
                break;
            case R.id.sports_history_mine_linearlayout:
                IntentUtils.turnTo(getActivity(),HistoryActivity.class,false);
                break;
            case R.id.setting_mine_linearlayout:
                IntentUtils.turnTo(getActivity(),SettingsActivity.class,false);
                break;
            case R.id.check_in_mine_Btn:
                mCheckInView.setCheckInDayNumber(3);
                mButtonCheckInView.setBackground(getResources().getDrawable(R.drawable.circle_button_click));
                mButtonCheckInView.setText("已签到");
                break;
            default:
                break;
        }
    }

    @Override
    public void mResult(String result) throws JSONException {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

        JSONObject jsonObject = new JSONObject(result);
        String url = jsonObject.getString("icon");
        String path = UrlUtils.HOST + url;
        app.getSp().setIcon(path);
        Glide.with(this).load(path).into(mHeadViewCiv);

        mNickName.setText(jsonObject.getString("nickname"));
        mCheckInView.setCheckInDayNumber(0);
    }

    @Override
    public void showDialog() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }
}