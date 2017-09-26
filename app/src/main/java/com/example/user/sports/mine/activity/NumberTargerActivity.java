package com.example.user.sports.mine.activity;

import android.app.FragmentManager;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.R;
import com.example.user.sports.mine.Dialog.CustomTargetDialogFragment;
import com.example.user.sports.mine.Dialog.DialogListener;
import com.example.user.sports.mine.Dialog.SexDialogFragment;

public class NumberTargerActivity extends AppCompatActivity implements DialogListener {
    private ImageView mImageViewChooseBaseTarget;       //基础目标
    private RelativeLayout mRelativeLayoutCustomTarget; //自定义目标
    private TextView mTextViewCustomTarget;             //自定义目标


    private CustomTargetDialogFragment dialog;
    private static boolean isBaseTarget = false;      //保存是否选择基础目标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_targer);
        initView();
    }

    private void initView() {

        //选择基础目标
        mImageViewChooseBaseTarget = (ImageView)findViewById(R.id.choose_base_target_mine_imageView);
        mImageViewChooseBaseTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBaseTarget){
                    mImageViewChooseBaseTarget.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(),R.color.choose_basr_target)));
                    isBaseTarget = true;
                }else{
                    mImageViewChooseBaseTarget.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(),R.color.white)));
                    isBaseTarget = false;

                }

            }
        });

        //设置自定义目标
        mTextViewCustomTarget = (TextView)findViewById(R.id.custom_target_mine_tv);
        mRelativeLayoutCustomTarget = (RelativeLayout)findViewById(R.id.custom_target_mine_relativelayout);
        mRelativeLayoutCustomTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBaseTarget){
                    Toast.makeText(getApplicationContext(),"抱歉,您已经选定了基础目标",Toast.LENGTH_SHORT).show();
                }else{
                    FragmentManager manager = getFragmentManager();
                    dialog = new CustomTargetDialogFragment();
                    dialog.show(manager,null);

                }

            }
        });


    }

    @Override
    public void onDialogPositiveClick(String data, int requestCode) {
        mTextViewCustomTarget.setText(data);
        dialog.dismiss();

    }
}
