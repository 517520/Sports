package com.example.user.sports.contacts.child.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.ContactsAdapter;
import com.example.user.sports.contacts.adapter.NewFriendAdapter;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 20:24
 * Description :
 */
public class NewFriendActivity extends AppCompatActivity {

    private AppHeadView headView;
    private RecyclerView mNewFriendRv;
    private NewFriendAdapter newFriendAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);

        initHeadView();
        initView();
        initData();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setTvName(R.string.new_friend);
        headView.setTvRight(R.string.add_friend);
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        headView.setOnClickListenerRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(NewFriendActivity.this, AddFriendActivity.class, false);
            }
        });
    }

    private void initView() {
        mNewFriendRv = (RecyclerView) findViewById(R.id.add_new_friend_rv);
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'F'; i++)
        {
            list.add("" + (char) i);
        }
        mNewFriendRv.setLayoutManager(new LinearLayoutManager(this));
        newFriendAdapter = new NewFriendAdapter(this, list);
        mNewFriendRv.setAdapter(newFriendAdapter);
        mNewFriendRv.addItemDecoration(new DividerItemDecoration(NewFriendActivity.this, DividerItemDecoration.VERTICAL_LIST));
        newFriendAdapter.setOnItemClickLitener(new NewFriendAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.add_new_friend_item_btn:
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
