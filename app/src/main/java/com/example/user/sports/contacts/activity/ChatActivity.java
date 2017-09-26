package com.example.user.sports.contacts.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.ChatAdapter;
import com.example.user.sports.contacts.model.Chat;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.MListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 20:15
 * Description :
 */
public class ChatActivity extends BaseActivity {

    private AppHeadView headView;
    private ImageView mSendIv;
    private EditText mChatEt;
    private MListView mListView;
    private ScrollView scrollView;

    private List<Chat> chatList;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setallowFullScreen(true);

        initHeadView();
        initView();
        initData();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("马云");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mSendIv = (ImageView) findViewById(R.id.send_chat_iv);
        mChatEt = (EditText) findViewById(R.id.chat_et);
        mListView = (MListView) findViewById(R.id.chat_mlv);
        scrollView = (ScrollView) findViewById(R.id.chat_sv);

        mSendIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    private void initData() {
        chatList = new ArrayList<>();
        Chat chat1 = new Chat();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_headview);
        chat1.setBitmap(bitmap);
        chat1.setMessage("去你妈的");
        chat1.setState("to");
        chatList.add(chat1);
        Chat chat2 = new Chat();
        chat2.setBitmap(bitmap);
        chat2.setMessage("草泥马的");
        chat2.setState("from");
        chatList.add(chat2);
        chatList.add(chat2);
        chatList.add(chat2);
        chatList.add(chat2);
        chatList.add(chat2);
        chatList.add(chat2);
        chatList.add(chat2);
        chatList.add(chat2);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);
        chatList.add(chat1);






        chatAdapter = new ChatAdapter(chatList, this);
        mListView.setAdapter(chatAdapter);
    }

    private void send() {

    }
}
