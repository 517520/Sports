package com.example.user.sports.mine.activity;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.cycle.activity.MyFragmentPagerAdapter;

public class SportsHistoryActivity extends AppCompatActivity {

    private TabLayout mTabLayout;                                //滑动的导航条
    private ViewPager mViewPager;                                //滑动
    private MineFragmentPagerAdapter mMineFragmentPagerAdapter;  //适配器

    private TextView mTextViewHeader;
    private RelativeLayout mRelativeLayoutChooseStyle;
    private RadioGroup mRadioGroupViewStyle;                     //显示查看的运动类型
    private ImageView mImageViewArrowDownOrUp;
    private int MarkForListener = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_history);
        initView();
    }

    private void initView() {
        //设置适配器
        mViewPager = (ViewPager)findViewById(R.id.viewPager_mine);
        mMineFragmentPagerAdapter = new MineFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMineFragmentPagerAdapter);

        //关联TableLayout和ViewPager;
        mTabLayout = (TabLayout)findViewById(R.id.tablayout_date_mine);
        mTabLayout.setupWithViewPager(mViewPager);

        //选择要查看的运动类型
        mImageViewArrowDownOrUp = (ImageView)findViewById(R.id.arrow_down_mine_iv);
        mRadioGroupViewStyle = (RadioGroup)findViewById(R.id.radioGroupStyle);
        mRelativeLayoutChooseStyle = (RelativeLayout)findViewById(R.id.choose_style_mine_relativelayout);
        mRelativeLayoutChooseStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MarkForListener==0){
                    MarkForListener=1;
                    mImageViewArrowDownOrUp.setImageResource(R.drawable.ic_keyboard_arrow_up);
                    mTabLayout.setVisibility(View.GONE);
                    mRadioGroupViewStyle.setVisibility(View.VISIBLE);
                }else {
                    MarkForListener=0;
                    mImageViewArrowDownOrUp.setImageResource(R.drawable.ic_keyboard_arrow_down);
                    mTabLayout.setVisibility(View.VISIBLE);
                    mRadioGroupViewStyle.setVisibility(View.GONE);
                }
            }
        });

        //RadioGroup的点击事件
        mTextViewHeader = (TextView) findViewById(R.id.header_run_mine_tv);
        mRadioGroupViewStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.all_sports_style:
                        mTextViewHeader.setText("所有运动");
                        HeaderClick();

                        break;
                    case R.id.walk_sports_style:
                        mTextViewHeader.setText("步行");
                        HeaderClick();

                        break;
                    case R.id.ride_sports_style:
                        mTextViewHeader.setText("骑行");
                        HeaderClick();

                        break;
                    case R.id.run_sports_style:
                        mTextViewHeader.setText("跑步");
                        HeaderClick();

                        break;
                }
            }
        });



    }

    //头部点击之后的执行步骤
    private void HeaderClick(){
        MarkForListener=0;
        mImageViewArrowDownOrUp.setImageResource(R.drawable.ic_keyboard_arrow_down);
        mTabLayout.setVisibility(View.VISIBLE);
        mRadioGroupViewStyle.setVisibility(View.GONE);
    }
}
