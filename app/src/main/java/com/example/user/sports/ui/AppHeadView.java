package com.example.user.sports.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.R;

/**
 * Created by ff on 2017/4/4.
 */

public class AppHeadView extends LinearLayout{

    private Context context;
    private TextView tvBack, tvTitle, tvMessage, tvNumber, tvAdd;
    private RelativeLayout mRelativeLayoutBack;
    private RelativeLayout mRelativeLayoutAdd;




    public AppHeadView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public AppHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView(){
        LayoutInflater.from(context).inflate(R.layout.include_head, this);
        tvBack = (TextView) findViewById(R.id.back_headview_tv);
        tvMessage = (TextView) findViewById(R.id.message_headview_tv);
        tvNumber = (TextView) findViewById(R.id.number_headview_tv);
        tvTitle = (TextView) findViewById(R.id.title_headview_tv);
        tvAdd = (TextView) findViewById(R.id.add_headview_tv);
        mRelativeLayoutBack = (RelativeLayout)findViewById(R.id.back_headview_relativelayout);
        mRelativeLayoutAdd = (RelativeLayout)findViewById(R.id.add_headview_relativelayout);

    }

    public void setVisibility(int back, int message, int number, int add){
        tvBack.setVisibility(back);
        tvMessage.setVisibility(message);
        tvNumber.setVisibility(number);
        tvAdd.setVisibility(add);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setNumber(int number) {
        tvNumber.setText(""+number);
    }

    public void setLeftImage(int ResourceId){
        tvBack.setBackgroundResource(ResourceId);
    }


    public void setOnClickListenerBack(OnClickListener listener){
        if (listener != null){
            mRelativeLayoutBack.setOnClickListener(listener);
        }
    }

    public void setOnClickListenerMessage(OnClickListener listener){
        if (listener != null){
            tvMessage.setOnClickListener(listener);
        }
    }

    public void setOnClickListenerAdd(OnClickListener listener){
        if (listener != null){
            mRelativeLayoutAdd.setOnClickListener(listener);
        }
    }
}
