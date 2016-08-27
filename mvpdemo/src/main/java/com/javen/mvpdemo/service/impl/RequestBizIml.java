package com.javen.mvpdemo.service.impl;

import com.javen.mvpdemo.service.OnRequestListener;
import com.javen.mvpdemo.service.RequestBiz;

import java.util.ArrayList;

public class RequestBizIml implements RequestBiz {

    @Override
    public void requestForData(final OnRequestListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*10);
                    ArrayList<String> data = new ArrayList<String>();
                    for(int i = 1 ; i< 10 ; i++){
                        data.add("item"+i);
                    }
                    if(null != listener){
                        listener.onSuccess(data);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}