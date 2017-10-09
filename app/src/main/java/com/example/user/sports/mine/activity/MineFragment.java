package com.example.user.sports.mine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.App;
import com.example.user.sports.R;
import com.example.user.sports.bean.Json_5_detail;
import com.example.user.sports.main.activity.LoginActivity;
import com.example.user.sports.main.activity.MainActivity;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.CircleImageView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.UrlUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private View mView;//根布局
    private TextView mTextViewLogIn, mNameTv;//登录按钮
    private LinearLayout mLinearLayoutSportsHistory;//运动历史
    private LinearLayout mLinearLayoutSettings;//设置
    private AppHeadView headView;
    private RelativeLayout mMyMessageRl;
    private CircleImageView mHeadCiv;

    private App app;
    private JSONObject jsonObject;

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

    @Override
    public void onResume() {
        super.onResume();
        if (app.getSp().getIsLogin()) {
            request();
            mTextViewLogIn.setVisibility(View.GONE);
            mMyMessageRl.setVisibility(View.VISIBLE);
        } else {
            mTextViewLogIn.setVisibility(View.VISIBLE);
            mMyMessageRl.setVisibility(View.GONE);
        }
    }

    private void initHeadView() {
        headView = (AppHeadView) mView.findViewById(R.id.headview);
        headView.setVisibility(View.GONE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("我");
    }

    private void initView() {
        app = (App) this.getActivity().getApplicationContext();

        mTextViewLogIn = (TextView) mView.findViewById(R.id.log_in_mine_TV);
        mLinearLayoutSportsHistory = (LinearLayout) mView.findViewById(R.id.sports_history_mine_linearlayout);
        mLinearLayoutSettings = (LinearLayout) mView.findViewById(R.id.setting_mine_linearlayout);
        mMyMessageRl = (RelativeLayout) mView.findViewById(R.id.my_message_mine_relativeLayout);
        mNameTv = (TextView) mView.findViewById(R.id.nickname_mine_tv);
        mHeadCiv = (CircleImageView) mView.findViewById(R.id.circle_image_cycle_imageView);

        mTextViewLogIn.setOnClickListener(this);
        mLinearLayoutSportsHistory.setOnClickListener(this);
        mLinearLayoutSettings.setOnClickListener(this);
    }

    //请求登录的个人信息
    private void request() {
        OkHttpUtils.postString()
                .url(UrlUtils.MIME_DETAIL)
                .content(new Gson().toJson(new Json_5_detail(app.getSp().getPhone())))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        jsonObject = new JSONObject(response.body().string());
                        handler.sendEmptyMessage(1);
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Object o, int i) {

                    }
                });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                try {
                    app.getSp().setIcon(jsonObject.getString("user_icon"));
                    app.getSp().setUsername(jsonObject.getString("user_nickname"));
                    app.getSp().setDay(jsonObject.getInt("continue_sign_day"));
                    app.getSp().setSex(jsonObject.getInt("user_sex"));
                    app.getSp().setBirthday(jsonObject.getString("user_birthday"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter();
            }
        }
    };

    private void adapter() {
        mNameTv.setText(app.getSp().getUsername());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_in_mine_TV:
                IntentUtils.turnTo(getActivity(), LoginActivity.class, true);
                break;
            case R.id.sports_history_mine_linearlayout:
                if (app.getSp().getIsLogin()) {
                    IntentUtils.turnTo(getActivity(), SportsHistoryActivity.class, false);
                } else {
                    showSnackBar();
                }
                break;
            case R.id.setting_mine_linearlayout:
                IntentUtils.turnTo(getActivity(), SettingsActivity.class, false);
                break;
            default:
                break;
        }
    }

    //显示Snackbar
    private void showSnackBar() {
        Snackbar.make(mHeadCiv, "您还未登录，前往登录吧！", Snackbar.LENGTH_LONG)
                .setAction("登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtils.turnTo(getActivity(), LoginActivity.class, true);
                    }
                }).show();

    }
}