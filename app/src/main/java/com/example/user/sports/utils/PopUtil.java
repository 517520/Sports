package com.example.user.sports.utils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.user.sports.R;


/**
 * 弹出框
 * Created by user on 2016/5/23.
 */
public class PopUtil extends PopupWindow {
    private LinearLayout ll_popup;
    private boolean isAnimation;
    private Activity activity;
    private int layout;
    private View bgview;
    private View view;

    public PopUtil(Activity activity, int layout, boolean isAnimation) {
        this.activity = activity;
        this.layout = layout;
        this.isAnimation = isAnimation;
        initPop();
    }

    public LinearLayout getLl_popup() {
        return ll_popup;
    }


    public void initPop() {
        view = activity.getLayoutInflater().inflate(layout, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);

        bgview = ll_popup.findViewById(R.id.view);
        bgview.setAlpha(0.5f);

        ll_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
