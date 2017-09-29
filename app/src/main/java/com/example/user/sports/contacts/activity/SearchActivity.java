package com.example.user.sports.contacts.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.RecommendAdapter;
import com.example.user.sports.contacts.adapter.SearchAdapter;
import com.example.user.sports.contacts.model.Friend;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.utils.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class SearchActivity extends BaseActivity {

    private RecyclerView mSearchRv;
    private AppHeadView headView;
    private SearchAdapter searchAdapter;
    private List<Friend> friendList;
    private SharePreferenceUtil sharePreferenceUtil;
    private String phone, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setallowFullScreen(true);

        Intent intent = getIntent();
        Bundle map = intent.getExtras();
        if (map.getInt("state") == 1) {
            name = map.getString("tagPhone");
        }else if (map.getInt("state") == 2) {
            name = map.getString("tagName");
        }

        initHeadView();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharePreferenceUtil = new SharePreferenceUtil(this);
        phone = sharePreferenceUtil.getPhone();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("搜索结果");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mSearchRv = (RecyclerView) findViewById(R.id.friend_search_rv);
        mSearchRv.setLayoutManager(new LinearLayoutManager(this));
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

        searchAdapter = new SearchAdapter(this, friendList);
        mSearchRv.setAdapter(searchAdapter);
        mSearchRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        searchAdapter.setOnItemClickLitener(new SearchAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                add(position);
            }
        });
    }

    private void request() {
        OkHttpUtils
                .post()
                .url("")
                .addParams("phoneNumber", phone)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        JSONObject object = new JSONObject(response.body().string());

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

    private void add(int position) {
        Toast.makeText(this, "你添加了" + friendList.get(position).getName() + "为好友", Toast.LENGTH_LONG).show();
    }
}
