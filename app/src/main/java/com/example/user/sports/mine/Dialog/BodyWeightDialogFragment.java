package com.example.user.sports.mine.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.sports.R;
import com.example.user.sports.mine.activity.PersonalMessageActivity;

/**
 * Created by user on 9/25/17.
 */

public class BodyWeightDialogFragment extends DialogFragment {
    private View view;
    DialogListener mBodyWeightDialogListener;     //回调借口

    private EditText mEditTextBodyWeigthNumber;   //输入体重数字
    private Button mButtonSave;                   //保存按钮

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mBodyWeightDialogListener = (DialogListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"must implement Listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_body_weight,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        initView();

        return builder.create();
    }

    private void initView() {
        mEditTextBodyWeigthNumber = (EditText) view.findViewById(R.id.body_weight_number_mine_EditText);
        mButtonSave = (Button)view.findViewById(R.id.body_weigth_save_mine_Btn);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = mEditTextBodyWeigthNumber.getText().toString()+"Kg";
                mBodyWeightDialogListener.onDialogPositiveClick(data, PersonalMessageActivity.BodyWeightCode);
            }
        });

    }
}
