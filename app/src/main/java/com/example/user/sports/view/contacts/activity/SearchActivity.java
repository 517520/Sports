package com.example.user.sports.view.contacts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_4_add_addpeople;
import com.example.user.sports.model.jsonModel.Json_4_add_findgroup;
import com.example.user.sports.model.jsonModel.Json_4_add_findpeople;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.ResultUtils;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.UploadView;
import com.example.user.sports.view.contacts.adapter.SearchAdapter;
import com.example.user.sports.view.contacts.model.Friend;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.utils.SharePreferenceUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class SearchActivity extends BaseActivity implements UploadView{

    private RecyclerView mSearchRv;
    private AppHeadView headView;
    private SearchAdapter searchAdapter;
    private List<Json_4_add_findpeople> friendList;
    private String phone, name;
    private int type;
    private int state = 0;

    private App app;
    private UploadPresenter presenter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setallowFullScreen(true);

        Intent intent = getIntent();
        Bundle map = intent.getExtras();
        type = map.getInt("state");
        if (type == 1) {
            phone = map.getString("tagPhone");
        }else if (type == 2) {
            name = map.getString("tagName");
        }

        initHeadView();
        initView();
        initData();

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
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        app = (App) getApplicationContext();

        mSearchRv = (RecyclerView) findViewById(R.id.friend_search_rv);
        mSearchRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        presenter = new UploadPresenterImp(this);
        if (type == 1) {
            try {
                presenter.upload(UrlUtils.ADD_FINDFRIEND, new Gson().toJson(new Json_4_add_findpeople(app.getSp().getPhone(), phone)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type == 2) {
            try {
                presenter.upload(UrlUtils.ADD_FINDGROUP, new Gson().toJson(new Json_4_add_findgroup(app.getSp().getPhone(), name)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mResult(String result) throws JSONException {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

        JSONObject jsonObject = new JSONObject(result);
        String toast = jsonObject.getString("result");

        if (state == 0) {
            //查找陌生人
            if (ResultUtils.LinkPeople.AFP_RESULT_SUCCESS.equals(toast)) {
                show(result);
            }else if (ResultUtils.LinkPeople.AFP_RESULT_FAIL_OTHERNOTEXIST.equals(toast)) {
                Toast.makeText(SearchActivity.this, "未找到该用户", Toast.LENGTH_LONG).show();
            }else if (ResultUtils.LinkPeople.AFP_RESULT_FAIL_OTHERALREADY.equals(toast)) {
                Toast.makeText(SearchActivity.this, "您已添加此人为好友！", Toast.LENGTH_LONG).show();
            }
        }else if (state == 1) {
            //添加此人
            if (ResultUtils.LinkPeople.AAP_RESULT_SUCCESS.equals(toast)) {
                Toast.makeText(SearchActivity.this, "添加成功,请等待对方接受", Toast.LENGTH_LONG).show();
                finish();
            }else if (ResultUtils.LinkPeople.AAP_RESULT_FAIL_DATABASEWRONG.equals(toast)) {
                Toast.makeText(SearchActivity.this, "添加失败！", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void show(String result) {
        friendList = new ArrayList<>();
        Json_4_add_findpeople json_4_add_findpeople = new Gson().fromJson(result, Json_4_add_findpeople.class);
        friendList.add(json_4_add_findpeople);
        searchAdapter = new SearchAdapter(this, friendList);
        mSearchRv.setAdapter(searchAdapter);
        mSearchRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        searchAdapter.setOnItemClickLitener(new SearchAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.add_search_item_btn:
                        state = 1;
                        addFriend(friendList.get(position).getFindPhoneNumber());
                        break;
                }
            }
        });
    }

    private void addFriend(String findPhoneNumber) {
        try {
            presenter.upload(UrlUtils.ADD_ADDFRIEND, new Gson().toJson(new Json_4_add_addpeople(app.getSp().getPhone(), findPhoneNumber)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDialog() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }
}
