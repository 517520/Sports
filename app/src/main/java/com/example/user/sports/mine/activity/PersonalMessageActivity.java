package com.example.user.sports.mine.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.R;
import com.example.user.sports.mine.Dialog.BirthdayDialogFragment;
import com.example.user.sports.mine.Dialog.BodyWeightDialogFragment;
import com.example.user.sports.mine.Dialog.DialogListener;
import com.example.user.sports.mine.Dialog.SexDialogFragment;
import com.example.user.sports.ui.AppHeadView;

public class PersonalMessageActivity extends BaseActivity implements DialogListener, View.OnClickListener{
    private RelativeLayout mRelativeLayoutSex;          //性别
    private RelativeLayout mRelativeLayoutBirthday;     //出生年月
    private RelativeLayout mRelativeLayoutBodyWeight;   //体重
    private RelativeLayout mRelativeLayoutBodyHeight;   //身高

    private TextView mTextViewSex;                       //性别显示
    private TextView mTextViewBirthday;                  //出生年月显示
    private TextView mTextViewBodyWeight;                //体重显示
    private TextView mTextViewBodyHeight;                //身高显示

    //区分不同的Dialog请求
    public static final int BirthdayRequestCode = 1;
    public static final int SexRequestCode = 2;
    public static final int BodyWeightCode = 3;

    public BodyWeightDialogFragment bodyWeightDialogFragment;

    private AppHeadView headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setallowFullScreen(true);
        setContentView(R.layout.activity_personal_message);

        initHeadView();
        initView();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("个人资料");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mRelativeLayoutSex = (RelativeLayout)findViewById(R.id.sex_mine_relativelayout);
        mRelativeLayoutBirthday = (RelativeLayout)findViewById(R.id.birthday_mine_relativelayout);
        mRelativeLayoutBodyWeight = (RelativeLayout)findViewById(R.id.body_weight_mine_relativelayout);
        mRelativeLayoutBodyHeight = (RelativeLayout)findViewById(R.id.body_height_mine_relativelayout);
        mRelativeLayoutSex.setOnClickListener(this);
        mRelativeLayoutBirthday.setOnClickListener(this);
        mRelativeLayoutBodyWeight.setOnClickListener(this);
        mRelativeLayoutBodyHeight.setOnClickListener(this);

        mTextViewSex =(TextView)findViewById(R.id.sex_mine_tv);
        mTextViewBirthday = (TextView)findViewById(R.id.birthday_mine_tv);
        mTextViewBodyWeight = (TextView)findViewById(R.id.body_weight_mine_tv);
        mTextViewBodyHeight = (TextView)findViewById(R.id.body_height_mine_tv);
    }

    @Override
    public void onDialogPositiveClick(String data, int requestCode) {
        switch (requestCode){
            case BirthdayRequestCode:
                mTextViewBirthday.setText(data);
                break;
            case SexRequestCode:
                mTextViewSex.setText(data);
                break;
            case BodyWeightCode:
                mTextViewBodyWeight.setText(data);
                bodyWeightDialogFragment.dismiss();
            default:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sex_mine_relativelayout:
                FragmentManager manageer = getFragmentManager();
                SexDialogFragment sexDialogFragment = new SexDialogFragment();
                sexDialogFragment.show(manageer,null);
                break;
            case R.id.birthday_mine_relativelayout:
                FragmentManager manageer1 = getFragmentManager();
                BirthdayDialogFragment dialog = new BirthdayDialogFragment();
                dialog.show(manageer1,null);
                break;
            case R.id.body_weight_mine_relativelayout:
                FragmentManager manager = getFragmentManager();
                bodyWeightDialogFragment = new BodyWeightDialogFragment();
                bodyWeightDialogFragment.show(manager,null);
                break;
            case R.id.body_height_mine_relativelayout:

                break;
            default:
                break;
        }
    }
}

