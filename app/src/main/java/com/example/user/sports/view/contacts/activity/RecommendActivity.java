package com.example.user.sports.view.contacts.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.user.sports.R;
import com.example.user.sports.view.contacts.adapter.RecommendAdapter;
import com.example.user.sports.ui.AppHeadView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class RecommendActivity extends AppCompatActivity {

    private AppHeadView headView;
    private RecyclerView mRecommendRv;
    private RecommendAdapter recommendAdapter;
    private List<String> list;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        
//        initHeadView();
        initView();
        initData();
    }

//    private void initHeadView() {
//        headView = (AppHeadView) findViewById(R.id.headview);
//        headView.setTvName(R.string.recommend_team);
//        headView.setOnClickListenerBack(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
    
    private void initView() {
        mRecommendRv = (RecyclerView) findViewById(R.id.recommend_rv);
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'F'; i++)
        {
            list.add("" + (char) i);
        }
//        mRecommendRv.setLayoutManager(new LinearLayoutManager(this));
//        recommendAdapter = new RecommendAdapter(this, list);
//        mRecommendRv.setAdapter(recommendAdapter);
//        mRecommendRv.addItemDecoration(new DividerItemDecoration2(RecommendActivity.this, DividerItemDecoration2.VERTICAL_LIST));
//        recommendAdapter.setOnItemClickLitener(new RecommendAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                switch (view.getId()) {
//                    case R.id.add_item_recommend_btn:
//                        IntentUtils.turnTo(RecommendActivity.this, TeamDetailActivity.class, false);
//                        break;
//                    default:
//
//                        break;
//                }
//            }
//        });
    }
}
