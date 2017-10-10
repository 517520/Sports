package com.example.user.sports.view.athletics.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.view.athletics.stepservice.StepService;
import com.example.user.sports.view.athletics.stepservice.UpdateUiCallBack;
import com.example.user.sports.ui.StepArcView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.SharePreferenceUtil;

/**
 * Author : yufeng.cao
 * Date : 2017.08.30 10:44
 * Description :
 */

public class WalkFragment extends Fragment {

    private View view;
    private StepArcView mWalkArv;
    private LinearLayout mBeginLl;
    private TextView mWalkTv;

    private boolean isBind = false;
    private Context context;
    private SharePreferenceUtil sharePreferenceUtil;
    private int state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_walk, container, false);

        initView();
        initData();
        return view;
    }

    //初始化控件
    private void initView() {
        context = this.getActivity();
        mWalkArv = (StepArcView) view.findViewById(R.id.step_walk_arv);
        mBeginLl = (LinearLayout) view.findViewById(R.id.begin_walk_ll);
        mWalkTv = (TextView) view.findViewById(R.id.begin_walk_tv);

        mBeginLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReckon();
            }
        });
    }

    //初始化数据
    private void initData() {
        mWalkArv.setParams("今日步数", "", "Walking", "等级：轻度活跃");
        mWalkArv.setCurrentCount(1000,0);
        sharePreferenceUtil = new SharePreferenceUtil(getActivity());

        if (isBind == false){
            //开启记步
            Intent intent = new Intent(context, StepService.class);
            isBind = this.getContext().bindService(intent, conn, Context.BIND_AUTO_CREATE);
            context.startService(intent);
        }
    }

    //判断当前状态
    public void startReckon() {
        state = sharePreferenceUtil.getState();
        switch (state) {
            case 0:
                sharePreferenceUtil.setState(1);
                IntentUtils.turnTo(getActivity(), ReadyActivity.class, false);
                break;
            case 1:
                IntentUtils.turnTo(this.getActivity(), CountActivity.class, false);
                break;
            case 2:
                Toast.makeText(this.getActivity(), "正在跑步中，请结束跑步再开始步行！", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(this.getActivity(), "正在骑行中，请结束骑行再开始步行！", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            //设置初始化数据
            mWalkArv.setCurrentCount(1000, stepService.getStepCount());

            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    mWalkArv.setCurrentCount(1000, stepCount);
                }
            });
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //服务解绑
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) {
            context.unbindService(conn);
            isBind = false;
        }
    }
}
