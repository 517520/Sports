package com.example.user.sports.contacts.team.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.TeamAdapter;
import com.example.user.sports.contacts.team.control.ControlActivity;
import com.example.user.sports.contacts.team.create.CreateActivity;
import com.example.user.sports.contacts.team.recommend.RecommendActivity;
import com.example.user.sports.contacts.team.search.SearchActivity;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class TeamFragment extends Fragment implements View.OnClickListener{

    private View view;
    private TextView mCreateTv, mRecommendTv, mSearchTv, mControlTv;
    private RecyclerView mTeamRv;

    private TeamAdapter teamAdapter;
    private List<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);

        initView();
        initData();
        return view;
    }

    private void initView() {
        mCreateTv = (TextView) view.findViewById(R.id.create_team_tv);
        mRecommendTv = (TextView) view.findViewById(R.id.recommend_team_tv);
        mSearchTv = (TextView) view.findViewById(R.id.search_team_tv);
        mControlTv = (TextView) view.findViewById(R.id.control_team_tv);
        mTeamRv = (RecyclerView) view.findViewById(R.id.team_rv);

        mCreateTv.setOnClickListener(this);
        mRecommendTv.setOnClickListener(this);
        mSearchTv.setOnClickListener(this);
        mControlTv.setOnClickListener(this);
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'F'; i++)
        {
            list.add("" + (char) i);
        }
        mTeamRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        teamAdapter = new TeamAdapter(this.getContext(), list);
        mTeamRv.setAdapter(teamAdapter);
        mTeamRv.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        teamAdapter.setOnItemClickLitener(new TeamAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_team_tv:
                IntentUtils.turnTo(this.getActivity(), CreateActivity.class, false);
                break;
            case R.id.recommend_team_tv:
                IntentUtils.turnTo(this.getActivity(), RecommendActivity.class, false);
                break;
            case R.id.search_team_tv:
                IntentUtils.turnTo(this.getActivity(), SearchActivity.class, false);
                break;
            case R.id.control_team_tv:
                IntentUtils.turnTo(this.getActivity(), ControlActivity.class, false);
                break;
            default:
                break;
        }
    }
}
