package com.example.user.sports.athletics.activity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.athletics.presenter.MyOrientationListener;
import com.example.user.sports.athletics.receive.AdminManageReceiver;
import com.example.user.sports.athletics.stepservice.StepService;
import com.example.user.sports.athletics.stepservice.UpdateUiCallBack;
import com.example.user.sports.dialog.AbandonDialog;
import com.example.user.sports.dialog.ExitDialog;
import com.example.user.sports.ui.StepArcView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.SharePreferenceUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author : yufeng.cao
 * Date : 2017.08.30 12:10
 * Description :
 */

public class CountActivity extends BaseActivity implements View.OnClickListener {

    private Button mStopBtn, mPauseBtn, mContinueBtn;
    private TextView mTimeTv, mHideTV, mStateTv, mSpeedTv, mCalorieTv, mDistanceTv;
    private ImageView mLockIv, mCameraIv;
    private LinearLayout mPauseLl;

    //地图
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirst = true;
    private List<LatLng> latLngList;
    private PolylineOptions options;

    //定位
    public LocationClient mLocationClient = new LocationClient(this);
    public BDAbstractLocationListener myListener = new MyLocationListener();
    public BitmapDescriptor mCurrentMarker;

    //方向传感器
    private MyOrientationListener myOrientationListener;
    private int mLastX;
    private float mCurrentAccracy;
    private double mCurrentLantitude;
    private double mCurrentLongitude;

    //测试用的
    private double x = 0.0;
    private double y = 0.0;

    //距离
    private LatLng mOldNode, mNewNode;
    private double mDistance;

    //计时器
    private Timer timer;
    private TimerTask timerTask;
    int cnt = 0;

    //锁屏
    final static int ENABLE_ADMIN = 1;
    private ComponentName mAdminName = null;

    private int state;
    public static  CountActivity instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_count);
        setallowFullScreen(true);

        initView();
        initMap();
        initData();
    }

    //获取当前状态显示标题栏
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(this);
        state = sharePreferenceUtil.getState();
        switch (state) {
            case 1:
                mStateTv.setText("步行中");
                break;
            case 2:
                mStateTv.setText("跑步中");
                break;
            case 3:
                mStateTv.setText("骑行中");
                break;
            default:
                break;
        }
    }

    //初始化控件
    private void initView() {
        mStopBtn = (Button) findViewById(R.id.stop_count_btn);
        mPauseBtn = (Button) findViewById(R.id.pause_count_btn);
        mContinueBtn = (Button) findViewById(R.id.continue_count_btn);
        mTimeTv = (TextView) findViewById(R.id.time_count_tv);
        mHideTV = (TextView) findViewById(R.id.hide_count_tv);
        mStateTv = (TextView) findViewById(R.id.state_count_tv);
        mSpeedTv = (TextView) findViewById(R.id.speed_count_tv);
        mCalorieTv = (TextView) findViewById(R.id.calorie_count_tv);
        mDistanceTv = (TextView) findViewById(R.id.distance_count_tv);
        mLockIv = (ImageView) findViewById(R.id.lock_count_iv);
        mCameraIv = (ImageView) findViewById(R.id.camera_count_iv);
        mPauseLl = (LinearLayout) findViewById(R.id.pause_count_ll);

        mHideTV.setOnClickListener(this);
        mLockIv.setOnClickListener(this);
        mCameraIv.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);
        mContinueBtn.setOnClickListener(this);

        mStopBtn.setVisibility(View.INVISIBLE);
        mContinueBtn.setVisibility(View.INVISIBLE);
    }

    //初始化地图
    private void initMap() {
        mapView = (MapView) findViewById(R.id.count_map);
        baiduMap = mapView.getMap();

        //隐藏百度地图logo
        View child = mapView.getChildAt(1);
        if (child != null && child instanceof ImageView || child instanceof ZoomControls) {
            child.setVisibility(View.INVISIBLE);
        }

        //隐藏放大缩小控件
        mapView.showZoomControls(false);

        //设置当前比例尺
        MapStatus mapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        baiduMap.setMapStatus(mapStatusUpdate);
        baiduMap.setIndoorEnable(true);

        //注册定位监听
        mLocationClient = new LocationClient(getApplicationContext());


        //方向传感器
        myOrientationListener = new MyOrientationListener(getApplicationContext());
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mLastX = (int) x;
                MyLocationData data = new MyLocationData.Builder()
                        .accuracy(mCurrentAccracy)
                        .direction(mLastX)
                        .latitude(mCurrentLantitude)
                        .longitude(mCurrentLongitude)
                        .build();
                baiduMap.setMyLocationData(data);

                mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_point);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,
                        true, mCurrentMarker);
                baiduMap.setMyLocationConfiguration(config);
            }
        });
        myOrientationListener.start();

        //定位参数
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setScanSpan(5000); //1000ms定位
        option.setIsNeedAddress(true); //需要地址信息
        option.setOpenGps(true);  //使用GPS
        option.setLocationNotify(true); //gps有效时以1S/次的频率输出结果
        option.setIsNeedLocationDescribe(true);  //是否需要地址位置语义化的结果
        option.setIsNeedLocationPoiList(true); //是否需要POI结果
        option.setNeedDeviceDirect(true);
        option.setIgnoreKillProcess(false); //在stop的时候是否杀死定位服务进程
        mLocationClient.setLocOption(option);

        //开启定位
        baiduMap.setMyLocationEnabled(true);

        //轨迹点集合
        latLngList= new ArrayList<LatLng>();
    }


    //定位监听器
    private class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(mLastX)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            baiduMap.setMyLocationData(data);
            mCurrentAccracy = bdLocation.getRadius();
            mCurrentLantitude = bdLocation.getLatitude();
            mCurrentLongitude = bdLocation.getLongitude();
            mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_point);
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,
                    true, mCurrentMarker);
            baiduMap.setMyLocationConfiguration(config);


            if (isFirst) {
                LatLng  ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                mOldNode = ll;
                latLngList.add(ll);
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_first);
                MarkerOptions options = new MarkerOptions().icon(bitmap).position(ll);
                baiduMap.addOverlay(options);


                baiduMap.animateMapStatus(status);
                isFirst = false;
            }
            LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            adapter(latLng);
        }
    }

    //画轨迹
    private void adapter(LatLng latLng) {
        latLngList.add(latLng);
        mNewNode = latLng;
        if (options == null) {
            options =  new PolylineOptions().width(15)
                    .points(latLngList).color(R.color.black_no_transparent);
        }else {
            options.points(latLngList);
        }
        baiduMap.addOverlay(options);

        mDistance += DistanceUtil.getDistance(mOldNode, mNewNode);
        mSpeedTv.setText(new DecimalFormat("0.00").format(mDistance/cnt));
        mDistanceTv.setText(new DecimalFormat("0.00").format(mDistance/1000));
        mCalorieTv.setText(new DecimalFormat("0.00").format((mDistance/1000)*80*1.036));
        mOldNode = latLng;
    }

    //初始化数据
    private void initData() {
        //用于销毁此activity
        instance = this;

        //初始化计时器
        timer = new Timer();
        startReckon();
    }

    //开始计时
    public void startReckon() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTimeTv.setText(getStringTime(cnt++));
                    }
                });
            }
        };
        timer.schedule(timerTask,0,1000);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
    }

    //停止计时
    public void stopReckon() {
        if (!timerTask.cancel()) {
            timerTask.cancel();
        }
        if (myListener != null) {
            mLocationClient.unRegisterLocationListener(myListener);
        }
        if (cnt <= 30) {
            final AbandonDialog abandonDialog = new AbandonDialog(this);
            abandonDialog.show();
            abandonDialog.setCanceledOnTouchOutside(false);
            abandonDialog.setClickListener(new AbandonDialog.ClickListenerInterface() {
                @Override
                public void doStop() {
                    timer.cancel();
                    changeState();
                    abandonDialog.dismiss();
                }

                @Override
                public void doContinue() {
                    abandonDialog.dismiss();
                    startReckon();
                }
            });
        }else {
            haveStop();
        }
    }

    //修改状态
    private void changeState() {
        SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(this);
        sharePreferenceUtil.setState(0);
        finish();
    }

    //跳转上传数据页面
    private void haveStop() {
        Map<String, Object> map = new HashMap<>();
        map.put("distance", mDistanceTv.getText().toString());
        map.put("speed", mSpeedTv.getText().toString());
        map.put("time", mTimeTv.getText().toString());
        map.put("calorie", mCalorieTv.getText().toString());
        map.put("state",state);
        map.put("list", latLngList);
        IntentUtils.turnTo(CountActivity.this, UploadActivity.class, true, map);
    }

    //暂停计时
    public void pausePeckon() {
        if (!timerTask.cancel()) {
            timerTask.cancel();
        }
        if (myListener != null) {
            mLocationClient.unRegisterLocationListener(myListener);
            mLocationClient.stop();
        }
    }

    //继续计时
    public void continueReckon() {
        startReckon();
    }

    private String getStringTime(int cnt) {
        int hour = cnt/3600;
        int min = cnt % 3600 / 60;
        int second = cnt % 60;
        return String.format(Locale.CHINA,"%02d:%02d:%02d",hour,min,second);
    }

    //显示设备管理器
    private void showAdminManagement() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);//打开手机设备管理器的intent
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "HAHA");
        startActivityForResult(intent, ENABLE_ADMIN);

    }

    //关屏
    private void lockScreen() {
        mAdminName = new ComponentName(this, AdminManageReceiver.class);
        DevicePolicyManager mDPM =  (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

        if (!mDPM.isAdminActive(mAdminName)) {//如果未激活
            showAdminManagement();//打开手机设备管理器
        }

        if (mDPM.isAdminActive(mAdminName)) {
            mDPM.lockNow();//执行锁屏
        }
    }

    //监听点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pause_count_btn:
                mPauseLl.setVisibility(View.INVISIBLE);
                mStopBtn.setVisibility(View.VISIBLE);
                mContinueBtn.setVisibility(View.VISIBLE);
                pausePeckon();
                break;
            case R.id.continue_count_btn:
                mPauseLl.setVisibility(View.VISIBLE);
                mStopBtn.setVisibility(View.INVISIBLE);
                mContinueBtn.setVisibility(View.INVISIBLE);
                continueReckon();
                break;
            case R.id.stop_count_btn:
                stopReckon();
                break;
            case R.id.hide_count_tv:
                moveTaskToBack(true);
                break;
            case R.id.lock_count_iv:
                lockScreen();
                break;
            case R.id.camera_count_iv:

                break;
            default:

                break;
        }
    }

    //点击返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final ExitDialog exitDialog = new ExitDialog(this);
            exitDialog.show();
            exitDialog.setClickListener(new ExitDialog.ClickListenerInterface() {
                @Override
                public void doStop() {
                    changeState();
                    exitDialog.dismiss();
                }

                @Override
                public void doContinue() {
                    exitDialog.dismiss();
                }
            });
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
        baiduMap.setMyLocationEnabled(false);
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
        baiduMap.clear();
        mapView.onDestroy();
        mapView = null;

        myOrientationListener.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
