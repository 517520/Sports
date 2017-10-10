package com.example.user.sports.main.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.sports.App;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.athletics.activity.AthleticsFragment;
import com.example.user.sports.athletics.activity.CountActivity;
import com.example.user.sports.contacts.activity.ContactsFragment;
import com.example.user.sports.cycle.activity.CycleFragment;
import com.example.user.sports.analysis.activity.AnalysisFragment;
import com.example.user.sports.dialog.ExitDialog;
import com.example.user.sports.mine.activity.MineFragment;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author : yufeng.cao
 * Time : 2017.08.16 16:24
 *
 * Description : MainActivity of the application;
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup radioGroup;
    private RadioButton analysisRbtn, athleticsRbtn, cycleRbtn, contactsRbtn, mineRbtn;
    private Button mAthleticsBtn;
    private FrameLayout mContainerFl;
    private FragmentTransaction transaction;

    private AnalysisFragment analysisFragment;
    private AthleticsFragment athleticsFragment;
    private ContactsFragment contactsFragment;
    private CycleFragment cycleFragment;
    private MineFragment mineFragment;
    private int state;

    public static MainActivity instance = null;
    private App app;

    //记录用户首次点击返回键的时间
    private long firstTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏导航栏、状态栏透明
        setallowFullScreen(true);

        initView();
        showFragment(3);
    }

    //每次回到这个页面要判断状态是运动还是运动中
    @Override
    protected void onResume() {
        super.onResume();

        //Test
        app.getSp().setIsLogin(true);
        state = app.getSp().getState();
        switch (state) {
            case 0:
                athleticsRbtn.setText("运动");
                break;
            case 1:
                athleticsRbtn.setText("步行中");
                break;
            case 2:
                athleticsRbtn.setText("跑步中");
                break;
            case 3:
                athleticsRbtn.setText("骑行中");
                break;
            default:
                break;
        }
    }

    //初始化控件
    protected void initView() {
        instance = this;
        app = (App) getApplicationContext();
        analysisRbtn = (RadioButton) findViewById(R.id.analysis_main_Rbtn);
        athleticsRbtn = (RadioButton) findViewById(R.id.athletics_main_Rbtn);
        cycleRbtn = (RadioButton) findViewById(R.id.cycle_main_Rbtn);
        contactsRbtn = (RadioButton) findViewById(R.id.contacts_main_Rbtn);
        mineRbtn = (RadioButton) findViewById(R.id.mine_main_Rbtn);
        mAthleticsBtn = (Button) findViewById(R.id.athletics_main_btn);

        mContainerFl = (FrameLayout) findViewById(R.id.container_main_fl);

        radioGroup = (RadioGroup) findViewById(R.id.main_RadioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        mAthleticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAthletics();
            }
        });
    }

    //是否正在运动
    public void onAthletics() {
        if (state == 0){
            showFragment(3);
        }else {
            IntentUtils.turnTo(MainActivity.this, CountActivity.class, false);
        }
        athleticsRbtn.setChecked(true);
    }

    //Radiogroup点击改变事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.contacts_main_Rbtn:
                if (app.getSp().getIsLogin()) {
                    showFragment(1);
                }else {
                    showSnackBar();
                }
                break;
            case R.id.analysis_main_Rbtn:
                if (app.getSp().getIsLogin()) {
                    showFragment(2);
                }else {
                    showSnackBar();
                }
                break;
            case R.id.athletics_main_Rbtn:
                onAthletics();
                break;
            case R.id.cycle_main_Rbtn:
                if (app.getSp().getIsLogin()) {
                    showFragment(4);
                }else {
                    showSnackBar();
                }
                break;
            case R.id.mine_main_Rbtn:
                showFragment(5);
                break;
            default:
                break;
        }
    }

    //显示Snackbar
    private void showSnackBar() {
        Snackbar.make(mContainerFl, "您还未登录，前往登录吧！", Snackbar.LENGTH_LONG)
                .setAction("登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtils.turnTo(MainActivity.this, LoginActivity.class, true);
                    }
                }).show();
    }

    //更换fragment
    private void showFragment(int i) {
        transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 1:
                if (contactsFragment == null) {
                    contactsFragment = new ContactsFragment();
                    transaction.add(R.id.container_main_fl, contactsFragment);
                }else {
                    transaction.show(contactsFragment);
                }
                break;
            case 2:
                if (analysisFragment == null) {
                    analysisFragment = new AnalysisFragment();
                    transaction.add(R.id.container_main_fl, analysisFragment);
                }else {
                    transaction.show(analysisFragment);
                }
                break;
            case 3:
                if (athleticsFragment == null) {
                    athleticsFragment = new AthleticsFragment();
                    transaction.add(R.id.container_main_fl, athleticsFragment);
                }else {
                    transaction.show(athleticsFragment);
                }
                break;
            case 4:
                if (cycleFragment == null) {
                    cycleFragment = new CycleFragment();
                    transaction.add(R.id.container_main_fl, cycleFragment);
                }else {
                    transaction.show(cycleFragment);
                }
                break;
            case 5:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.container_main_fl, mineFragment);
                }else {
                    transaction.show(mineFragment);
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
        if (analysisFragment != null) {
            fragmentTransaction.hide(analysisFragment);
        }
        if (athleticsFragment != null) {
            fragmentTransaction.hide(athleticsFragment);
        }
        if (contactsFragment != null) {
            fragmentTransaction.hide(contactsFragment);
        }
        if (cycleFragment != null) {
            fragmentTransaction.hide(cycleFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    //双击退出程序
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    if (state == 0) {
                        Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                        firstTime=secondTime;
                    }else {
                        ExitDialog exitDialog = new ExitDialog(this);
                        exitDialog.show();
                        exitDialog.setClickListener(new ExitDialog.ClickListenerInterface() {
                            @Override
                            public void doStop() {
                                CountActivity.instance.finish();
                                finish();
                            }

                            @Override
                            public void doContinue() {
                                IntentUtils.turnTo(MainActivity.this, CountActivity.class, false);
                            }
                        });
                    }
                    return true;
                }else{
                    finish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    //退出
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
