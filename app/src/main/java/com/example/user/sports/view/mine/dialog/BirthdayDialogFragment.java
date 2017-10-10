package com.example.user.sports.view.mine.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.user.sports.R;
import com.example.user.sports.view.mine.activity.PersonalMessageActivity;


/**
 * Created by user on 9/21/17.
 */

public class BirthdayDialogFragment extends DialogFragment{
    public DatePicker mDatePicker;

    //回调的接口
    DialogListener mBirthdayDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mBirthdayDialogListener = (DialogListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"must implement Listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_birthday,null);

        mDatePicker = (DatePicker) view.findViewById(R.id.birthday_mine_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder .setTitle("出生年月")
                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        String birthday = String.valueOf(year)+"."+String.valueOf(month+1)+"."+String.valueOf(day);
                        mBirthdayDialogListener.onDialogPositiveClick(birthday, PersonalMessageActivity.BirthdayRequestCode);

                    }
                })
                .setView(view);

        return builder.create();
    }
}
