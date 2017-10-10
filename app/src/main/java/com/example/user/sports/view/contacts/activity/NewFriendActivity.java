package com.example.user.sports.view.contacts.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.view.contacts.adapter.NewFriendAdapter;
import com.example.user.sports.view.contacts.model.Friend;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.ui.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 20:24
 * Description :
 */
public class NewFriendActivity extends BaseActivity {

    private AppHeadView headView;
    private EmptyRecyclerView mNewFriendRv;
    private View mEmptyView;

    private NewFriendAdapter newFriendAdapter;
    private List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        setallowFullScreen(true);

        initHeadView();
        initView();
        initData();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("新的朋友");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mEmptyView = findViewById(R.id.empty_new_friend);
        mNewFriendRv = (EmptyRecyclerView) findViewById(R.id.add_new_friend_rv);
        mNewFriendRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        friendList = new ArrayList<>();
        Friend friend1 = new Friend();
        friend1.setName("原子弹");
        friend1.setDetail("傻逼就是我!");
        friendList.add(friend1);
        Friend friend2 = new Friend();
        friend2.setName("贝尔格里尔斯");
        friend2.setDetail("这个世界上压根就没有没有我不敢吃的东西");
        friendList.add(friend2);

        newFriendAdapter = new NewFriendAdapter(this, friendList);
        mNewFriendRv.setAdapter(newFriendAdapter);
        mNewFriendRv.setEmptyView(mEmptyView);
        mNewFriendRv.addItemDecoration(new DividerItemDecoration(NewFriendActivity.this, DividerItemDecoration.VERTICAL_LIST));
        newFriendAdapter.setOnItemClickLitener(new NewFriendAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.accept_new_friend_item_btn:
                        Toast.makeText(NewFriendActivity.this, "添加"+position, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.refuse_new_friend_item_btn:
                        Toast.makeText(NewFriendActivity.this, "拒绝"+position, Toast.LENGTH_LONG).show();
                        break;
                    default:

                        break;
                }
            }
        });
    }
}
