package com.example.user.sports.contacts.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.contacts.child.activity.ChildContactsFragment;
import com.example.user.sports.contacts.team.activity.TeamFragment;

/**
 * Author : yufeng.cao
 * Date : 2017.08.17 11:44
 * Description :
 */

public class ContactsFragment extends Fragment implements View.OnClickListener{

    private View view;
    private ImageView messageIv;
    private TextView mChildTv, mTeamTv;

    private FrameLayout mContactFl;
    private FragmentTransaction transaction;

    private ChildContactsFragment childContactsFragment;
    private TeamFragment teamFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        initView();
        initData();
        return view;
    }

    private void initView() {
        mChildTv = (TextView) view.findViewById(R.id.child_contacts_tv);
        mTeamTv = (TextView) view.findViewById(R.id.team_contacts_tv);
        mContactFl = (FrameLayout) view.findViewById(R.id.container_contacts_fl);

        mChildTv.setOnClickListener(this);
        mTeamTv.setOnClickListener(this);
    }

    private void initData() {
        showFragment(1);
    }

    //更换fragment
    private void showFragment(int i) {
        transaction = getChildFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 1:
                if (childContactsFragment == null) {
                    childContactsFragment = new ChildContactsFragment();
                    transaction.add(R.id.container_contacts_fl, childContactsFragment);
                }else {
                    transaction.show(childContactsFragment);
                }
                break;
            case 2:
                if (teamFragment == null) {
                    teamFragment = new TeamFragment();
                    transaction.add(R.id.container_contacts_fl, teamFragment);
                }else {
                    transaction.show(teamFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    //隐藏所有fragment
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果此fragment不为空的话就隐藏起来
        if (childContactsFragment != null) {
            fragmentTransaction.hide(childContactsFragment);
        }
        if (teamFragment != null) {
            fragmentTransaction.hide(teamFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.child_contacts_tv:
                showFragment(1);
                break;
            case R.id.team_contacts_tv:
                showFragment(2);
                break;
            default:
                break;
        }
    }
}
