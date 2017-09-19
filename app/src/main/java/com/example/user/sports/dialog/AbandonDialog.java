package com.example.user.sports.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.sports.R;

/**
 * Author : user
 * Date : 17-9-18
 * Description :
 */
public class AbandonDialog extends Dialog {

    private Context context;
    private View view;
    private TextView mStopTv, mContinueTv;

    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface{
        public void doStop();
        public void doContinue();
    }

    public AbandonDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_abandon, null);
        setContentView(view);

        initView();
    }

    private void initView() {
        mStopTv = (TextView) view.findViewById(R.id.stop_abandon_tv);
        mContinueTv = (TextView) view.findViewById(R.id.continue_abandon_tv);

        mStopTv.setOnClickListener(new clickListener());
        mContinueTv.setOnClickListener(new clickListener());

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
                case R.id.stop_abandon_tv:
                    clickListenerInterface.doStop();
                    break;
                case R.id.continue_abandon_tv:
                    clickListenerInterface.doContinue();
                    break;
                default:
                    break;
            }
        }
    }
}
