package com.example.user.sports.view.contacts.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.ui.DividerItemDecoration2;
import com.example.user.sports.view.contacts.adapter.MessageAdapter;
import com.example.user.sports.ui.AppHeadView;

import com.example.user.sports.view.contacts.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.20 19:53
 * Description :
 */
public class MessageActivity extends BaseActivity {

    private AppHeadView headview;
    private RecyclerView mMessageRv;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        setallowFullScreen(true);

        initHeadView();
        initView();
        initData();
    }

    private void initHeadView() {
        headview = (AppHeadView) findViewById(R.id.headview);
        headview.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headview.setTitle("消息");
        headview.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mMessageRv = (RecyclerView) findViewById(R.id.message_rv);
        mMessageRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        messageList = new ArrayList<>();

        messageAdapter = new MessageAdapter(this, messageList);
        mMessageRv.setAdapter(messageAdapter);
        mMessageRv.addItemDecoration(new DividerItemDecoration2(this, DividerItemDecoration2.HORIZONTAL_LIST));
        messageAdapter.setOnItemClickLitener(new MessageAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                toChat(position);
            }
        });
    }

    private void toChat(int position) {
        Toast.makeText(this,messageList.get(position).getName(), Toast.LENGTH_LONG).show();
    }
}
