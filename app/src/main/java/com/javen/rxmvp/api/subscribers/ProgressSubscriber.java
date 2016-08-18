package com.javen.rxmvp.api.subscribers;

import android.content.Context;
import android.widget.Toast;

import com.javen.rxmvp.view.progress.ProgressCancelListener;
import com.javen.rxmvp.view.progress.ProgressDialogHandler;

import rx.Subscriber;

public abstract class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private Context context;
    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressSubscriber(Context context) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    @Override
    public void onStart() {
        showProgressDialog();
//        Toast.makeText(context, "Get Jokes Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
//        Toast.makeText(context, "Get Jokes Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        onCustomError(e);
    }

    @Override
    public void onNext(T t) {
        onDoNext(t);
    }

    public abstract void onDoNext(T t);

    protected void onCustomError(Throwable e){
        dismissProgressDialog();
        Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }
}