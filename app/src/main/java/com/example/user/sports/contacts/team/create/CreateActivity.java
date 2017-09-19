package com.example.user.sports.contacts.team.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.dialog.PrivacyDialog;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.ui.CircleImageView;
import com.example.user.sports.utils.IntentUtils;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class CreateActivity extends AppCompatActivity implements View.OnClickListener{

    private AppHeadView headView;
    private ImageView mBackgroundIv;
    private CircleImageView mHeadCiv;
    private EditText mNameEt, mLocationEt;
    private TextView mPrivacyTv;
    private Button mCreateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        initHeadView();
        initView();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setTvName(R.string.new_team);
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mBackgroundIv = (ImageView) findViewById(R.id.background_create_iv);
        mHeadCiv = (CircleImageView) findViewById(R.id.head_create_civ);
        mNameEt = (EditText) findViewById(R.id.name_create_et);
        mLocationEt = (EditText) findViewById(R.id.location_create_et);
        mPrivacyTv = (TextView) findViewById(R.id.privacy_create_tv);
        mCreateBtn = (Button) findViewById(R.id.create_btn);

        mBackgroundIv.setOnClickListener(this);
        mHeadCiv.setOnClickListener(this);
        mPrivacyTv.setOnClickListener(this);
        mCreateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.background_create_iv:
                IntentUtils.turnTo(CreateActivity.this, ChooseBgActivity.class, false);
                break;
            case R.id.head_create_civ:

                break;
            case R.id.privacy_create_tv:
                PrivacyDialog privacyDialog = new PrivacyDialog(this);
                privacyDialog.show();
                break;
            case R.id.create_btn:
                PrivacyDialog privacyDialog2 = new PrivacyDialog(this);
                privacyDialog2.show();
                break;
            default:
                break;
        }
    }
}
