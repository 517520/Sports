package com.example.user.sports.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.sports.R;

/**
 * Created by ff on 2017/4/4.
 */

public class AppHeadView extends LinearLayout{

    private Context context;
    private TextView tvBack, tvName, tvIcon, tvRight;

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
        tvBack = (TextView) findViewById(R.id.tv_head_back);
        tvName = (TextView) findViewById(R.id.tv_head_name);
        tvIcon = (TextView) findViewById(R.id.tv_head_icon);
        tvRight = (TextView) findViewById(R.id.tv_head_right);
    }

    public void setVisibility(int icon, int right){
        tvIcon.setVisibility(icon);
        tvRight.setVisibility(right);
    }

    public void setTvName(int textID){
        if (textID != 0){
            tvName.setText(textID);
        }
    }

    public void setName(String text){
        if (!TextUtils.isEmpty(text)){
            tvName.setText(text);
        }
    }

    public void setTvRight(int textID){
        if (textID != 0){
            tvRight.setText(textID);
        }
    }

    public void setOnClickListenerBack(OnClickListener listener){
        if (listener != null){
            tvBack.setOnClickListener(listener);
        }
    }

    public void setOnClickListenerRight(OnClickListener listener){
        if (listener != null){
            tvRight.setOnClickListener(listener);
        }
    }

    public void setOnClickListenerIcon(OnClickListener listener){
        if (listener != null){
            tvIcon.setOnClickListener(listener);
        }
    }
}
