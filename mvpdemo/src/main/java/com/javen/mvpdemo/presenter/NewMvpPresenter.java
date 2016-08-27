package com.javen.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;

import com.javen.mvpdemo.basemvp.BasePresenter;
import com.javen.mvpdemo.service.OnRequestListener;
import com.javen.mvpdemo.service.RequestBiz;
import com.javen.mvpdemo.service.impl.RequestBizIml;
import com.javen.mvpdemo.view.NewMvpView;

import java.util.List;

public class NewMvpPresenter extends BasePresenter<NewMvpView> {
        private RequestBiz requestBiz;
        private Handler mHandler;

        public NewMvpPresenter() {        
            requestBiz = new RequestBizIml();
            mHandler = new Handler(Looper.getMainLooper());
        }    

        public void onResume(){
            mView.showLoading();
            requestBiz.requestForData(new OnRequestListener() {
                @Override            
                public void onSuccess(final List<String> data) {
                    //由于请求开启了新线程，所以用handler去更新界面
                    mHandler.post(new Runnable() {
                      @Override                    
                       public void run() {                        
                           mView.hideLoading();                        
                           mView.setListItem(data);                    
                        }                
                    });            
                 }            

                 @Override            
                 public void onFailed() {                
                     mView.showMessage("请求失败");            
                 }        
            });    
       }   

        public void onItemClick(int position){        
              mView.showMessage("点击了item"+position);     
        }
}