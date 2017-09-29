package com.example.user.sports.contacts.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.athletics.activity.CountActivity;
import com.example.user.sports.athletics.presenter.MyOrientationListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yufeng.cao
 * Date : 2017.09.26 14:43
 * Description :
 */
public class LocationActivity extends BaseActivity implements View.OnClickListener{

    private TextView mCancelTv, mSavaTv;
    //地图
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirst = true;
    private PolylineOptions options;

    //定位
    public LocationClient mLocationClient = new LocationClient(this);
    public BDAbstractLocationListener myListener = new MyLocationListener();
    private String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setallowFullScreen(true);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location);

        initView();
        initMap();
    }

    private void initView() {
        mCancelTv = (TextView) findViewById(R.id.cancel_location_tv);
        mSavaTv = (TextView) findViewById(R.id.sava_location_tv);

        mCancelTv.setOnClickListener(this);
        mSavaTv.setOnClickListener(this);
    }

    private void initMap() {
        mapView = (MapView) findViewById(R.id.location_mv);
        baiduMap = mapView.getMap();

        //隐藏百度地图logo
        View child = mapView.getChildAt(1);
        if (child != null && child instanceof ImageView || child instanceof ZoomControls) {
            child.setVisibility(View.INVISIBLE);
        }

        //隐藏放大缩小控件
        mapView.showZoomControls(false);

        //设置当前比例尺
        MapStatus mapStatus = new MapStatus.Builder().zoom(16).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        baiduMap.setMapStatus(mapStatusUpdate);
        baiduMap.setIndoorEnable(true);

        //注册定位监听
        mLocationClient = new LocationClient(getApplicationContext());

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
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_location_tv:
                Intent mIntent = new Intent();
                this.setResult(0, mIntent);
                finish();
                break;
            case R.id.sava_location_tv:
                Intent intent = new Intent();
                intent.putExtra("location", location);
                this.setResult(1, intent);
                finish();
                break;
            default:
                break;
        }
    }

    //定位监听器
    private class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(100)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            baiduMap.setMyLocationData(data);

            if (isFirst) {
                LatLng  ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                location = bdLocation.getCity()+bdLocation.getDistrict()+bdLocation.getStreet();
                Toast.makeText(LocationActivity.this, location, Toast.LENGTH_LONG).show();
                baiduMap.animateMapStatus(status);
                isFirst = false;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
