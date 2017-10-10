package com.example.user.sports.view.cycle.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.model.jsonModel.Json_3_createActivity;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.UrlUtils;
import com.google.gson.Gson;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

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
                upload();
            }
        });
    }

    //上传数据
    private void upload() {
        OkHttpUtils
                .postString()
                .url(UrlUtils.CREATE_ACTIVITY)
                .content(new Gson().toJson(new Json_3_createActivity("55","", "哈哈", "2017-10-10 14:00:00", "2017-10-10 14:00:30", "中开", "哈哈")))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        handler.sendEmptyMessage(1);
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Object o, int i) {

                    }
                });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(ExerciseCreateActivity.this , "hah ", Toast.LENGTH_LONG).show();
        }
    };

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
