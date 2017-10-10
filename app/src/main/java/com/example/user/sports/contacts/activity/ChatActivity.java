package com.example.user.sports.contacts.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView mChatRv;

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
        mChatRv = (RecyclerView) findViewById(R.id.chat_rv);

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
        chat1.setType(0);
        chatList.add(chat1);
        Chat chat2 = new Chat();
        chat2.setBitmap(bitmap);
        chat2.setMessage("草泥马的");
        chat2.setType(1);
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mChatRv.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(chatList);
        mChatRv.setAdapter(chatAdapter);
        mChatRv.scrollToPosition(chatList.size() - 1);
    }

    private void send() {
        String content = mChatEt.getText().toString();
        if (!"".equals(content)) {
            Chat chat = new Chat();
            chat.setType(0);
            chat.setMessage(content);
            chat.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_headview));
            chatList.add(chat);
            chatAdapter.notifyItemInserted(chatList.size() - 1);
            mChatRv.scrollToPosition(chatList.size() - 1);
            mChatEt.setText("");
        }
    }
}
