package com.example.user.sports.view.contacts.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_0_sign_in;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.ui.DividerItemDecoration2;
import com.example.user.sports.view.UploadView;
import com.example.user.sports.view.contacts.adapter.RunFriendAdapter;
import com.example.user.sports.view.contacts.model.RunFriend;
import com.example.user.sports.model.jsonModel.Json_4_add_findpeople;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.main.activity.LoginActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Author : yufeng.cao
 * Date : 2017.09.07 12:00
 * Description :
 */
public class AddFriendActivity extends BaseActivity implements View.OnClickListener{

    private AppHeadView headView;
    private EditText mSearchEt, mSearchTeamEt;
    private ImageView mSearchIv, mSearchTeamIv;
    private TextView mWeiboTv, mWechatTv, mQqTv, mContactsTv;
    private RecyclerView mRunFriendRv;
    private RunFriendAdapter runFriendAdapter;
    private List<RunFriend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        setallowFullScreen(true);

        initHeadView();
        initView();
        initData();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("添加");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mSearchEt = (EditText) findViewById(R.id.search_add_friend_et);
        mSearchIv = (ImageView) findViewById(R.id.search_add_friend_iv);
        mSearchTeamEt = (EditText) findViewById(R.id.search_add_team_et);
        mSearchTeamIv = (ImageView) findViewById(R.id.search_add_team_iv);
        mWeiboTv = (TextView) findViewById(R.id.weibo_add_friend_tv);
        mWechatTv = (TextView) findViewById(R.id.wechat_add_friend_tv);
        mQqTv = (TextView) findViewById(R.id.qq_add_friend_tv);
        mContactsTv = (TextView) findViewById(R.id.contacts_add_friend_tv);
        mRunFriendRv = (RecyclerView) findViewById(R.id.add_friend_rv);
        mRunFriendRv.setLayoutManager(new LinearLayoutManager(this));
        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,KeyEvent event)  {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(mSearchEt.getText().toString())) {
                        searchFriend();
                    }else {
                        Toast.makeText(AddFriendActivity.this, "输入手机号不能为空", Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });

        mSearchTeamEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!TextUtils.isEmpty(mSearchTeamEt.getText().toString())) {
                    searchTeam();
                }else {
                    Toast.makeText(AddFriendActivity.this, "输入群名称不能为空", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        mSearchIv.setOnClickListener(this);
        mSearchTeamIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_add_friend_iv:
                if (!TextUtils.isEmpty(mSearchEt.getText().toString())) {
                    searchFriend();
                }else {
                    Toast.makeText(AddFriendActivity.this, "输入手机号不能为空", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.search_add_team_iv:
                if (!TextUtils.isEmpty(mSearchTeamEt.getText().toString())) {
                    searchTeam();
                }else {
                    Toast.makeText(AddFriendActivity.this, "输入群名称不能为空", Toast.LENGTH_LONG).show();
                }
                break;


            case R.id.weibo_add_friend_tv:

                break;
            case R.id.wechat_add_friend_tv:

                break;
            case R.id.qq_add_friend_tv:

                break;
            case R.id.contacts_add_friend_tv:

                break;
            default:
                break;
        }
    }

    private void searchFriend() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1);
        map.put("tagPhone", mSearchEt.getText().toString());
        mSearchEt.setText("");
        IntentUtils.turnTo(AddFriendActivity.this, SearchActivity.class, false, map);
    }

    private void searchTeam() {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("state", 2);
        map2.put("tagName", mSearchTeamEt.getText().toString());
        mSearchTeamEt.setText("");
        IntentUtils.turnTo(AddFriendActivity.this, SearchActivity.class, false, map2);
    }

    private void initData() {
        friendList = new ArrayList<>();
        RunFriend runFriend1 = new RunFriend();
        runFriend1.setName("博尔特");
        runFriend1.setDistance("100.55");
        friendList.add(runFriend1);
        RunFriend runFriend2 = new RunFriend();
        runFriend2.setName("刘翔");
        runFriend2.setDistance("10.66");
        friendList.add(runFriend2);

        runFriendAdapter = new RunFriendAdapter(this, friendList);
        mRunFriendRv.setAdapter(runFriendAdapter);
        mRunFriendRv.addItemDecoration(new DividerItemDecoration2(this, DividerItemDecoration2.HORIZONTAL_LIST));
        runFriendAdapter.setOnItemClickLitener(new RunFriendAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                details(position);
            }
        });
    }

    private void details(int position) {
        Toast.makeText(this, friendList.get(position).getName(), Toast.LENGTH_LONG).show();
    }

}
