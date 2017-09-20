package com.example.user.sports.contacts.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.FriendAdapter;
import com.example.user.sports.contacts.child.activity.ChildContactsFragment;
import com.example.user.sports.contacts.model.Friend;
import com.example.user.sports.contacts.team.activity.TeamFragment;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.IndexBar;
import com.example.user.sports.ui.decoration.DividerItemDecoration;
import com.example.user.sports.ui.decoration.TitleItemDecoration;

import java.util.ArrayList;
import java.util.List;

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
    private LinearLayoutManager mManager;
    private List<Friend> friendList;
    private RecyclerView.Adapter mAdapter;
    private TitleItemDecoration mDecoration;
    
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

    private void initHeadView() {
        headView = (AppHeadView) view.findViewById(R.id.headview);
        headView.setVisibility(View.GONE, View.VISIBLE, View.GONE);
        headView.setTitle("联系人");
        headView.setOnClickListenerMessage(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        headView.setOnClickListenerAdd(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        mContactsRv.setLayoutManager(mManager = new LinearLayoutManager(this.getActivity()));
        //初始化数据
        String[] strings = new String[]{"河源","惠州","广州","潮州","东莞","深圳","佛山","湛江","梅州"};
        friendList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            Friend friend = new Friend();
            friend.setName(strings[i]);//设置城市名称
            friendList.add(friend);
        }

        mContactsRv.setAdapter(mAdapter = new FriendAdapter(this.getActivity(), friendList));
        mContactsRv.addItemDecoration(mDecoration = new TitleItemDecoration(this.getActivity(), friendList));
        mContactsRv.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        //使用indexBar
        mIndexBar.setmPressedShowTextView(mSideBarHintTv)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(friendList);//设置数据源

    }

    @Override
    public void onClick(View v) {

    }
}
