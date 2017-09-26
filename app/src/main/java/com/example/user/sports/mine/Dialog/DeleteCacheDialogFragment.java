package com.example.user.sports.mine.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.sports.R;

/**
 * Created by user on 9/22/17.
 */

public class DeleteCacheDialogFragment extends DialogFragment {
    private TextView mTextViewCancel;      //dialog取消
    private TextView mTextViewDetermine;   //dialog确定

    DialogListener mDeleteCacheDialogListener;
    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mDeleteCacheDialogListener = (DialogListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"must implement Listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_cache,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        initView();

        return builder.create();
    }

    private void initView() {
        mTextViewCancel = (TextView)view.findViewById(R.id.cancel_dialog_mine_tv);
        mTextViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mTextViewDetermine = (TextView)view.findViewById(R.id.determine_dialog_mine_tv);
        mTextViewDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteCacheDialogListener.onDialogPositiveClick(null,0);

            }
        });


    }
}
