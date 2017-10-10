package com.example.user.sports.view.mine.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.user.sports.R;
import com.example.user.sports.view.mine.activity.PersonalMessageActivity;

/**
 * Created by user on 9/22/17.
 */

public class SexDialogFragment extends DialogFragment {

    //回调的接口
    DialogListener mSexDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mSexDialogListener = (DialogListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"must implement Listener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder .setTitle("性别")
                .setItems(R.array.sex_mine, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] sexs = getResources().getStringArray(R.array.sex_mine);
                        mSexDialogListener.onDialogPositiveClick(sexs[which],PersonalMessageActivity.SexRequestCode);
                    }
                });


        return builder.create();
    }

}
