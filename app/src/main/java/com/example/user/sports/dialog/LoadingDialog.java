package com.example.user.sports.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.sports.R;


public class LoadingDialog extends Dialog {

    private String message;
    private AppsSearchDialogListener listener;

    public LoadingDialog(Context context) {
        super(context);

    }

    public LoadingDialog(Context context, AppsSearchDialogListener listener) {
        super(context);
        this.listener = listener;

    }

    public LoadingDialog(Context context, int theme, AppsSearchDialogListener listener) {
        super(context, theme);
        this.listener = listener;

    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
        this.setCanceledOnTouchOutside(false);
    }

    public void show(String text) {
        super.show();
    }

    public void dismiss() {
        if (this.isShowing()) {
            super.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (this.listener != null) {
            this.listener.onCancelSearchDialog();
        }
    }

    public interface AppsSearchDialogListener {
        public void onCancelSearchDialog();
    }

}
