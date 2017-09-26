package com.example.user.sports.contacts.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.RunFriendAdapter;
import com.example.user.sports.contacts.model.Friend;
import com.example.user.sports.contacts.model.RunFriend;
import com.example.user.sports.main.activity.MainActivity;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.decoration.DividerItemDecoration;
import com.example.user.sports.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.07 12:00
 * Description :
 */
public class AddFriendActivity extends BaseActivity implements View.OnClickListener{

    private AppHeadView headView;
    private EditText mSearchEt;
    private ImageView mSearchIv;
    private TextView mCreateTv, mWeiboTv, mWechatTv, mQqTv, mContactsTv;
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
        mCreateTv = (TextView) findViewById(R.id.create_add_friend_tv);
        mWeiboTv = (TextView) findViewById(R.id.weibo_add_friend_tv);
        mWechatTv = (TextView) findViewById(R.id.wechat_add_friend_tv);
        mQqTv = (TextView) findViewById(R.id.qq_add_friend_tv);
        mContactsTv = (TextView) findViewById(R.id.contacts_add_friend_tv);
        mRunFriendRv = (RecyclerView) findViewById(R.id.add_friend_rv);
        mRunFriendRv.setLayoutManager(new LinearLayoutManager(this));
        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,KeyEvent event)  {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    IntentUtils.turnTo(AddFriendActivity.this, SearchActivity.class, false);
                }
                return false;
            }
        });
        
        mSearchIv.setOnClickListener(this);
        mCreateTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_add_friend_iv:
                IntentUtils.turnTo(AddFriendActivity.this, SearchActivity.class, false);
                break;
            case R.id.create_add_friend_tv:
                IntentUtils.turnTo(AddFriendActivity.this, CreateActivity.class, false);
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
        mRunFriendRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
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
