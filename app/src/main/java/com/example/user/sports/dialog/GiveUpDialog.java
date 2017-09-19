package com.example.user.sports.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.user.sports.R;

/**
 * Author : user
 * Date : 17-9-18
 * Description :
 */
public class GiveUpDialog extends Dialog {

    private Context context;
    private View view;
    private TextView mConsiderTv, mGiveUpTv;

    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface{
        public void doConsider();
        public void doGiveUp();
    }

    public GiveUpDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_give_up, null);
        setContentView(view);

        initView();
    }

    private void initView() {
        mConsiderTv = (TextView) view.findViewById(R.id.consider_give_up_tv);
        mGiveUpTv = (TextView) view.findViewById(R.id.give_up_tv);

        mConsiderTv.setOnClickListener(new clickListener());
        mGiveUpTv.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.85); // 高度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);
    }

    public void setClickListener(ClickListenerInterface clickListenerInterface){
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.consider_give_up_tv:
                    clickListenerInterface.doConsider();
                    break;
                case R.id.give_up_tv:
                    clickListenerInterface.doGiveUp();
                    break;
                default:
                    break;
            }
        }
    }
}
