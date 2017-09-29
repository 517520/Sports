package com.example.user.sports.contacts.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.RecommendAdapter;
import com.example.user.sports.contacts.adapter.TeamAdapter;
import com.example.user.sports.contacts.model.Team;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.ui.EmptyRecyclerView;
import com.example.user.sports.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 20:24
 * Description :
 */
public class TeamActivity extends BaseActivity {

    private AppHeadView headView;
    private EmptyRecyclerView mTeamRv;
    private RecyclerView recommendRv;
    private TextView mMoreTv;

    private View mEmptyView;


    private TeamAdapter teamAdapter;
    private List<Team> teamList;

    private RecommendAdapter recommendAdapter;
    private List<Team> emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setallowFullScreen(true);

        initHeadView();
        initView();
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
        mTeamRv = (EmptyRecyclerView) findViewById(R.id.team_rv);
        mEmptyView = findViewById(R.id.empty_team);
        mTeamRv.setLayoutManager(new LinearLayoutManager(this));
        recommendRv = (RecyclerView) mEmptyView.findViewById(R.id.recommend_empty_team_rv);
        recommendRv.setLayoutManager(new LinearLayoutManager(this));
        mMoreTv = (TextView) mEmptyView.findViewById(R.id.more_empty_team_tv);

        mMoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initData() {
        teamList = new ArrayList<>();
        Team team1 = new Team();
        team1.setName("健身吧");
        team1.setDetail("一起动起来啊草泥马");
        teamList.add(team1);
        Team team2 = new Team();
        team2.setName("李毅吧");
        team2.setDetail("帝吧随时出征");
        teamList.add(team2);

        teamAdapter = new TeamAdapter(this, teamList);
        mTeamRv.setAdapter(teamAdapter);
        mTeamRv.setEmptyView(mEmptyView);
        mTeamRv.addItemDecoration(new DividerItemDecoration(TeamActivity.this, DividerItemDecoration.VERTICAL_LIST));

        teamAdapter.setOnItemClickLitener(new TeamAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                IntentUtils.turnTo(TeamActivity.this, TeamDetailActivity.class, false);
            }
        });

        emptyList = new ArrayList<>();
        Team team3 = new Team();
        team3.setName("健身吧");
        team3.setDetail("一起动起来啊草泥马");
        emptyList.add(team3);
        Team team4 = new Team();
        team4.setName("李毅吧");
        team4.setDetail("帝吧随时出征");
        emptyList.add(team4);

        recommendAdapter = new RecommendAdapter(this, emptyList);
        recommendRv.setAdapter(recommendAdapter);
        recommendRv.addItemDecoration(new DividerItemDecoration(TeamActivity.this, DividerItemDecoration.VERTICAL_LIST));

        recommendAdapter.setOnItemClickLitener(new RecommendAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
}
