package com.example.user.sports.mine.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.user.sports.R;

/**
 * Created by user on 8/31/17.
 */

public class CacheDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.chooseIfDeleteCache)
                .setPositiveButton(R.string.determine,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Window window =getDialog().getWindow();
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.gravity = Gravity.BOTTOM;
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        window.setAttributes(layoutParams);
//    }
}
