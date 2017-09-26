package com.example.user.sports.contacts.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class CreateActivity extends BaseActivity implements View.OnClickListener{

    private AppHeadView headView;
    private ImageView mHeadIv;
    private EditText mNameEt, mDetailEt;
    private Button mCreateBtn;
    private TextView mLocationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setallowFullScreen(true);

        initHeadView();
        initView();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("新建群组");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mHeadIv = (ImageView) findViewById(R.id.head_create_iv);
        mNameEt = (EditText) findViewById(R.id.name_create_et);
        mDetailEt = (EditText) findViewById(R.id.detail_create_et);
        mCreateBtn = (Button) findViewById(R.id.create_btn);
        mLocationTv = (TextView) findViewById(R.id.location_create_tv);

        mHeadIv.setOnClickListener(this);
        mCreateBtn.setOnClickListener(this);
        mLocationTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_create_iv:

                break;
            case R.id.create_btn:

                break;
            case R.id.location_create_tv:

                break;
            default:
                break;
        }
    }
}
