package com.example.user.sports.contacts.team.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.RecommendAdapter;
import com.example.user.sports.contacts.team.recommend.RecommendActivity;
import com.example.user.sports.contacts.team.recommend.TeamDetailActivity;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class SearchActivity extends AppCompatActivity {

    private RecyclerView mSearchRv;
    private RecommendAdapter recommendAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initData();
    }

    private void initView() {
        mSearchRv = (RecyclerView) findViewById(R.id.team_search_rv);
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'F'; i++)
        {
            list.add("" + (char) i);
        }
        mSearchRv.setLayoutManager(new LinearLayoutManager(this));
        recommendAdapter = new RecommendAdapter(this, list);
        mSearchRv.setAdapter(recommendAdapter);
        mSearchRv.addItemDecoration(new DividerItemDecoration(SearchActivity.this, DividerItemDecoration.VERTICAL_LIST));
        recommendAdapter.setOnItemClickLitener(new RecommendAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.add_item_recommend_btn:

                        break;
                    default:

                        break;
                }
            }
        });
    }
}
