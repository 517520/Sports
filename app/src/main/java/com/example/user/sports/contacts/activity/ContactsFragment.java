package com.example.user.sports.contacts.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.FriendAdapter;
import com.example.user.sports.contacts.adapter.MessageAdapter;
import com.example.user.sports.contacts.model.Friend;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.IndexBar;
import com.example.user.sports.ui.decoration.DividerItemDecoration;
import com.example.user.sports.ui.decoration.TitleItemDecoration;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.SharePreferenceUtil;
import com.example.user.sports.utils.UrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class ContactsFragment extends Fragment implements View.OnClickListener{

    private View view;
    private AppHeadView headView;
    private RelativeLayout mNewFriendRl, mTeamRl, mCreateRl;
    private EditText mSearchEt;

    private RecyclerView mContactsRv;
    private IndexBar mIndexBar;
    private TextView mSideBarHintTv;
    private List<Friend> friendList;
    private FriendAdapter friendAdapter;
    private LinearLayoutManager mManager;
    private SharePreferenceUtil sharePreferenceUtil;
    private String phone;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        initHeadView();
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        requestData();
    }

    private void initHeadView() {
        headView = (AppHeadView) view.findViewById(R.id.headview);
        headView.setVisibility(View.GONE, View.VISIBLE, View.GONE, View.VISIBLE);
        headView.setTitle("联系人");
        headView.setOnClickListenerMessage(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(), MessageActivity.class, false);
            }
        });

        headView.setOnClickListenerAdd(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(), AddFriendActivity.class, false);
            }
        });
    }

    private void initView() {
        mNewFriendRl = (RelativeLayout) view.findViewById(R.id.new_friend_contacts_rl);
        mTeamRl = (RelativeLayout) view.findViewById(R.id.team_contacts_rl);
        mCreateRl = (RelativeLayout) view.findViewById(R.id.create_team_contacts_rl);
        mSearchEt = (EditText) view.findViewById(R.id.search_contacts_et);
        mContactsRv = (RecyclerView) view.findViewById(R.id.friend_contacts_rv);
        mIndexBar = (IndexBar) view.findViewById(R.id.contacts_indexBar);
        mSideBarHintTv = (TextView) view.findViewById(R.id.sideBar_contacts_tv);
        
        mNewFriendRl.setOnClickListener(this);
        mTeamRl.setOnClickListener(this);
        mCreateRl.setOnClickListener(this);
    }

    private void initData() {
        sharePreferenceUtil = new SharePreferenceUtil(this.getActivity());
        phone = sharePreferenceUtil.getPhone();
        mContactsRv.setLayoutManager(mManager = new LinearLayoutManager(this.getActivity()));
    }

    //请求数据
    private void requestData() {
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

    private void adapterData() {
        //初始化数据
        String[] strings = new String[]{"河源","惠州","广州","潮州","东莞","深圳","佛山","湛江","梅州"};
        friendList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            Friend friend = new Friend();
            friend.setName(strings[i]);//设置城市名称
            friendList.add(friend);
        }
        friendAdapter = new FriendAdapter(this.getActivity(), friendList);

        mContactsRv.setAdapter(friendAdapter);
        mContactsRv.addItemDecoration(new TitleItemDecoration(this.getActivity(), friendList));
        mContactsRv.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        friendAdapter.setOnItemClickLitener(new MessageAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                IntentUtils.turnTo(getActivity(), ChatActivity.class, false);
            }
        });


        //使用indexBar
        mIndexBar.setmPressedShowTextView(mSideBarHintTv)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(friendList);//设置数据源
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_friend_contacts_rl:
                IntentUtils.turnTo(getActivity(), NewFriendActivity.class, false);
                break;
            case R.id.team_contacts_rl:
                IntentUtils.turnTo(getActivity(), TeamActivity.class, false);
                break;
            case R.id.create_team_contacts_rl:
                IntentUtils.turnTo(getActivity(), CreateActivity.class, false);
                break;
            default:
                break;
        }
    }
}
