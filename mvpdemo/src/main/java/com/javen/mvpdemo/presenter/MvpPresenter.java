package com.javen.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;

import com.javen.mvpdemo.service.OnRequestListener;
import com.javen.mvpdemo.service.RequestBiz;
import com.javen.mvpdemo.service.impl.RequestBizIml;
import com.javen.mvpdemo.view.MvpView;

import java.util.List;

public class MvpPresenter {

    private MvpView mvpView;
    RequestBiz requestBiz;
    private Handler mHandler;

    public MvpPresenter(MvpView mvpView) {
        this.mvpView = mvpView;
        requestBiz = new RequestBizIml();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void onResume(){
        mvpView.showLoading();
        requestBiz.requestForData(new OnRequestListener() {
            @Override
            public void onSuccess(final List<String> data) {
               //由于请求开启了新线程，所以用handler去更新界面
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mvpView.hideLoading();
                        mvpView.setListItem(data);
                    }
                });

            }

            @Override
            public void onFailed() {
                mvpView.showMessage("请求失败");
            }
        });
    }


    public void onDestroy(){
        mvpView = null;
    }

    public void onItemClick(int position){
        mvpView.showMessage("点击了item" + position);
    }

}