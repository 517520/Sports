package com.example.user.sports.cycle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.ui.AppHeadView;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;

public class ExerciseCreateActivity extends AppCompatActivity {
    private AppHeadView headView;
    private RelativeLayout mRelativeLayoutExerciseDate;
    private TextView mTextViewExerciseDate;
    private Button mButtonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_create);
        initHeadView();
        initView();
    }

    private void initView() {
        mTextViewExerciseDate = (TextView)findViewById(R.id.exercise_time_cycle_tv);
        mRelativeLayoutExerciseDate = (RelativeLayout)findViewById(R.id.exercise_time_cycle_relativelayout);
        mRelativeLayoutExerciseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmoothDateRangePickerFragment smoothDateRangePickerFragment = SmoothDateRangePickerFragment.newInstance(
                        new SmoothDateRangePickerFragment.OnDateRangeSetListener(){
                            @Override
                            public void onDateRangeSet(SmoothDateRangePickerFragment smoothDateRangePickerFragment, int yearStart, int monthStart,
                                                       int dayStart, int yearEnd, int monthEnd, int dayEnd) {
                                mTextViewExerciseDate.setText(yearStart+"/"+monthStart+"/"+dayStart+"至"+yearEnd+"/"+monthEnd+"/"+dayEnd);
                            }
                        }
                );
                smoothDateRangePickerFragment.show(getFragmentManager(),"smooth");
            }
        });


        mButtonCreate = (Button)findViewById(R.id.create_btn);
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"创建成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                finish();
            }
        });
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("创建活动");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
