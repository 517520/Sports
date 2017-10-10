package com.example.user.sports.view.athletics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.dialog.GiveUpDialog;
import com.example.user.sports.dialog.LoadingDialog;
import com.example.user.sports.model.jsonModel.Json_2_state_walk;
import com.example.user.sports.presenter.UploadPresenter;
import com.example.user.sports.presenter.UploadPresenterImp;
import com.example.user.sports.view.UploadView;
import com.example.user.sports.utils.SharePreferenceUtil;
import com.example.user.sports.model.UploadModelImp;
import com.example.user.sports.utils.UrlUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.18 10:26
 * Description :
 */

public class UploadActivity extends BaseActivity implements View.OnClickListener, UploadView {

    //控件
    private String distance, speed, time, calorie;
    private List<LatLng> latLngList;
    private TextView mUnSaveTv, mDistanceTv, mSpeedTv, mTimeTv, mCalorieTv, mStateTv;
    private Button mContinueBtn, mFinishBtn, mUploadBtn;

    //地图
    private MapView mapView;
    private PolylineOptions options;
    private BaiduMap baiduMap;
    public LocationClient mLocationClient = new LocationClient(this);

    //上传数据
    private int state;
    private SharePreferenceUtil sharePreferenceUtil;
    private LoadingDialog loadingDialog;
    private UploadPresenter uploadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setallowFullScreen(true);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_upload);

        Intent intent = getIntent();
        Bundle map = intent.getExtras();
        distance = map.getString("distance");
        speed = map.getString("speed");
        time = map.getString("time");
        calorie = map.getString("calorie");
        latLngList = map.getParcelableArrayList("list");
        sharePreferenceUtil = new SharePreferenceUtil(this);
        state = sharePreferenceUtil.getState();

        initView();
        initData();
        uploadPresenter = new UploadPresenterImp(this);
    }

    private void initView() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this, R.style.Dialog_Fullscreen);
        }

        mStateTv = (TextView) findViewById(R.id.state_upload_tv);
        mUnSaveTv = (TextView) findViewById(R.id.unsave_upload_tv);
        mDistanceTv = (TextView) findViewById(R.id.distance_upload_tv);
        mSpeedTv = (TextView) findViewById(R.id.speed_upload_tv);
        mTimeTv = (TextView) findViewById(R.id.time_upload_tv);
        mCalorieTv = (TextView) findViewById(R.id.calorie_upload_tv);
        mContinueBtn = (Button) findViewById(R.id.continue_upload_btn);
        mFinishBtn = (Button) findViewById(R.id.finish_upload_btn);
        mUploadBtn = (Button) findViewById(R.id.upload_btn);

        mapView = (MapView) findViewById(R.id.upload_mv);
        baiduMap = mapView.getMap();
        //设置比例尺
        MapStatus mapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        baiduMap.setMapStatus(mapStatusUpdate);
        baiduMap.setIndoorEnable(true);
        //隐藏百度地图logo
        View child = mapView.getChildAt(1);
        if (child != null && child instanceof ImageView || child instanceof ZoomControls) {
            child.setVisibility(View.INVISIBLE);
        }
        //隐藏放大缩小控件
        mapView.showZoomControls(false);

        mLocationClient = new LocationClient(getApplicationContext());

        mUnSaveTv.setOnClickListener(this);
        mContinueBtn.setOnClickListener(this);
        mFinishBtn.setOnClickListener(this);
        mUploadBtn.setOnClickListener(this);

        mFinishBtn.setVisibility(View.GONE);
        mContinueBtn.setVisibility(View.GONE);
    }

    private void initData() {
        mDistanceTv.setText(distance);
        mSpeedTv.setText(speed);
        mTimeTv.setText(time);
        mCalorieTv.setText(calorie);

        switch (state) {
            case 1:
                mStateTv.setText("步行结束");
                break;
            case 2:
                mStateTv.setText("跑步结束");
                break;
            case 3:
                mStateTv.setText("骑行结束");
                break;
        }

        options =  new PolylineOptions().width(15)
                .points(latLngList).color(R.color.black_no_transparent);
        baiduMap.addOverlay(options);
        MyLocationData data = new MyLocationData.Builder()
                .accuracy(0.0f)
                .direction(100)
                .latitude(latLngList.get(latLngList.size()-1).latitude)
                .longitude(latLngList.get(latLngList.size()-1).longitude)
                .build();
        baiduMap.setMyLocationData(data);

        LatLng  ll = new LatLng(latLngList.get(latLngList.size()-1).latitude, latLngList.get(latLngList.size()-1).longitude);
        MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
        baiduMap.animateMapStatus(status);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unsave_upload_tv:
                giveUp();
                break;
            case R.id.continue_upload_btn:

                break;
            case R.id.upload_btn:
                try {
                    uploadPresenter.upload(UrlUtils.STATE_WALK, new Gson().toJson(new Json_2_state_walk("55","2017-10-18 14:00:00", "2017-10-18 14:00:30", "w", 10.0f, 20.0f, 30.0f, 10000, 50.0f)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mUploadBtn.setVisibility(View.INVISIBLE);
                mContinueBtn.setVisibility(View.VISIBLE);
                mFinishBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.finish_upload_btn:
                finish();
                break;
            default:
                break;
        }
    }

    //是否要放弃询问弹出框
    private void giveUp() {
        final GiveUpDialog giveUpDialog = new GiveUpDialog(this);
        giveUpDialog.show();
        giveUpDialog.setClickListener(new GiveUpDialog.ClickListenerInterface() {
            @Override
            public void doConsider() {
                giveUpDialog.dismiss();
            }

            @Override
            public void doGiveUp() {
                giveUpDialog.dismiss();
                sharePreferenceUtil.setState(0);
                finish();
            }
        });
    }

    //点击返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            giveUp();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baiduMap.clear();
        mapView.onDestroy();
        mapView = null;
    }

    //网络请求回调结果
    @Override
    public void mResult(String result) throws JSONException {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        JSONObject jsonObject = new JSONObject(result);
        Toast.makeText(this, jsonObject.getString("result"), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialog() {
        loadingDialog.show();
    }
}
