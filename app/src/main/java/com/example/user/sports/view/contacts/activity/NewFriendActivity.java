package com.example.user.sports.view.contacts.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_4_newFriend_handle;
import com.example.user.sports.model.jsonModel.Json_4_newFriend_list;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.utils.ResultUtils;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.UploadView;
import com.example.user.sports.view.contacts.adapter.NewFriendAdapter;
import com.example.user.sports.view.contacts.model.Friend;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.ui.EmptyRecyclerView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 20:24
 * Description :
 */
public class NewFriendActivity extends BaseActivity implements UploadView{

    private AppHeadView headView;
    private EmptyRecyclerView mNewFriendRv;
    private View mEmptyView;

    private NewFriendAdapter newFriendAdapter;
    private List<Json_4_newFriend_list> friendList;

    private App app;
    private UploadPresenter presenter;
    private LoadingDialog loadingDialog;
    private int state = 0;

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
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        app = (App) getApplicationContext();
        presenter = new UploadPresenterImp(this);

        mEmptyView = findViewById(R.id.empty_new_friend);
        mNewFriendRv = (EmptyRecyclerView) findViewById(R.id.add_new_friend_rv);
        mNewFriendRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        try {
            presenter.upload(UrlUtils.NEWFRIEND_LIST, new Gson().toJson(new Json_4_newFriend_list(app.getSp().getPhone())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mResult(String result) throws JSONException {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

        if (state == 0) {
            JSONArray jsonArray = new JSONArray(result);
            show(jsonArray);
        }else if (state == 1){
            Toast.makeText(NewFriendActivity.this, "操作成功", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void show(JSONArray jsonArray) {
        friendList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Json_4_newFriend_list json_4_newFriend_list = new Gson().fromJson(jsonArray.get(i).toString(), Json_4_newFriend_list.class);
                friendList.add(json_4_newFriend_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        newFriendAdapter = new NewFriendAdapter(this, friendList);
        mNewFriendRv.setAdapter(newFriendAdapter);
        mNewFriendRv.setEmptyView(mEmptyView);
        mNewFriendRv.addItemDecoration(new DividerItemDecoration(NewFriendActivity.this, DividerItemDecoration.VERTICAL_LIST));
        newFriendAdapter.setOnItemClickLitener(new NewFriendAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.accept_new_friend_item_btn:
                        try {
                            state = 1;
                            presenter.upload(UrlUtils.NEWFRIEND_HANDLE, new Gson().toJson(new Json_4_newFriend_handle(app.getSp().getPhone(),
                                    friendList.get(position).getLinkPhoneNumber(), 1)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.refuse_new_friend_item_btn:
                        try {
                            state = 1;
                            presenter.upload(UrlUtils.NEWFRIEND_HANDLE, new Gson().toJson(new Json_4_newFriend_handle(app.getSp().getPhone(),
                                    friendList.get(position).getLinkPhoneNumber(), 2)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:

                        break;
                }
            }
        });
    }

    @Override
    public void showDialog() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }
}
