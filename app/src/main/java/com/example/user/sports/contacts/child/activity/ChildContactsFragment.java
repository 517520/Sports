package com.example.user.sports.contacts.child.activity;

import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.contacts.adapter.ContactsAdapter;
import com.example.user.sports.ui.DividerItemDecoration;
import com.example.user.sports.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */


public class ChildContactsFragment extends Fragment {

    private View view;
    private RecyclerView mFriendsRv;
    private TextView mNewFriendTv;
    private ContactsAdapter contactsAdapter;
    private List<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child_contacts, container, false);

        initView();
        initData();
        return view;
    }

    private void initView() {
        mFriendsRv = (RecyclerView) view.findViewById(R.id.friends_child_rv);
        mNewFriendTv = (TextView) view.findViewById(R.id.new_friend_child_tv);
        mNewFriendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.turnTo(getActivity(), NewFriendActivity.class, false);
            }
        });
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'F'; i++)
        {
            list.add("" + (char) i);
        }
        mFriendsRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        contactsAdapter = new ContactsAdapter(this.getContext(), list);
        mFriendsRv.setAdapter(contactsAdapter);
        mFriendsRv.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        contactsAdapter.setOnItemClickLitener(new ContactsAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                IntentUtils.turnTo(getActivity(), InformationActivity.class, false);
            }
        });
    }
}
