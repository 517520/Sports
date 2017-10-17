package com.example.user.sports.view.contacts.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_4_group;
import com.example.user.sports.model.jsonModel.Json_4_newFriend_list;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.utils.UrlUtils;
import com.example.user.sports.view.UploadView;
import com.example.user.sports.view.contacts.adapter.RecommendAdapter;
import com.example.user.sports.view.contacts.adapter.TeamAdapter;
import com.example.user.sports.view.contacts.model.Team;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.ui.EmptyRecyclerView;
import com.example.user.sports.utils.IntentUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 20:24
 * Description :
 */
public class TeamActivity extends BaseActivity implements UploadView{

    private AppHeadView headView;
    private EmptyRecyclerView mTeamRv;
    private RecyclerView recommendRv;
    private TextView mMoreTv;

    private View mEmptyView;

    private RecommendAdapter recommendAdapter;
    private List<Team> recommendList;

    private TeamAdapter teamAdapter;
    private List<Json_4_group> teamList;

    private App app;
    private UploadPresenter presenter;
    private LoadingDialog loadingDialog;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setallowFullScreen(true);

        initHeadView();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE);
        headView.setTitle("群组");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        headView.setOnClickListenerAdd(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(TeamActivity.this, CreateActivity.class, false);
            }
        });
    }

    private void initView() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        app = (App) getApplicationContext();

        presenter = new UploadPresenterImp(this);

        mTeamRv = (EmptyRecyclerView) findViewById(R.id.team_rv);
        mEmptyView = findViewById(R.id.empty_team);
        mTeamRv.setLayoutManager(new LinearLayoutManager(this));
        recommendRv = (RecyclerView) mEmptyView.findViewById(R.id.recommend_empty_team_rv);
        recommendRv.setLayoutManager(new LinearLayoutManager(this));
        mMoreTv = (TextView) mEmptyView.findViewById(R.id.more_empty_team_tv);

        recommendList = new ArrayList<>();
        Team team1 = new Team();
        team1.setName("仲恺交友群");
        team1.setDetail("单身男女一起来");
        team1.setBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.run_man));
        recommendList.add(team1);
        Team team2 = new Team();
        team2.setName("TCL大厦交友群");
        team2.setDetail("下班聚会");
        team2.setBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tcl));
        recommendList.add(team2);

        recommendAdapter = new RecommendAdapter(this, recommendList);
        recommendRv.setAdapter(recommendAdapter);
        recommendRv.addItemDecoration(new DividerItemDecoration(TeamActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initData() {
        try {
            presenter.upload(UrlUtils.GROUP, new Gson().toJson(new Json_4_group(app.getSp().getPhone())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mResult(String result) throws JSONException {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

        JSONArray jsonArray = new JSONArray(result);
        show(jsonArray);
    }

    private void show(JSONArray jsonArray) {
        teamList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Json_4_group json_4_group = new Gson().fromJson(jsonArray.get(i).toString(), Json_4_group.class);
                teamList.add(json_4_group);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        teamAdapter = new TeamAdapter(this, teamList);
        mTeamRv.setAdapter(teamAdapter);
        mTeamRv.setEmptyView(mEmptyView);
        mTeamRv.addItemDecoration(new DividerItemDecoration(TeamActivity.this, DividerItemDecoration.VERTICAL_LIST));

        teamAdapter.setOnItemClickLitener(new TeamAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", teamList.get(position).getGroupName());
                map.put("location", teamList.get(position).getLocation());
                map.put("detail", teamList.get(position).getGroupIntroduce());
                map.put("icon", teamList.get(position).getGroupIcon());
                IntentUtils.turnTo(TeamActivity.this, TeamDetailActivity.class, false, map);
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
