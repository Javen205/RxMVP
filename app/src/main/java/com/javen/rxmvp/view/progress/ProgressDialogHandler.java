package com.javen.rxmvp.view.progress;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private SweetAlertDialog pd;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context,
                                 ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog(){
        if (pd == null) {
            pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pd.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pd.setTitleText("Loading");
            pd.setCancelable(cancelable);
            if (cancelable) {
                pd.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog(){
        if (pd != null) {
            pd.dismiss();
            mProgressCancelListener.onCancelProgress();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}