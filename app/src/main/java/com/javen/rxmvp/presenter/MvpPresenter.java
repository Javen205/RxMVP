package com.javen.rxmvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.javen.rxmvp.api.HttpJuHeMethods;
import com.javen.rxmvp.basemvp.BasePresenter;
import com.javen.rxmvp.bean.JuHeDream;
import com.javen.rxmvp.view.MvpView;
import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;

public class MvpPresenter extends BasePresenter<MvpView> {
    private Context mContext;
    private Subscriber subscriber;
    private List<JuHeDream> mDatas;

    public MvpPresenter(Context context) {
        this.mContext = context;
    }

    //获取数据
    public void getData(String q) throws UnsupportedEncodingException {
        if (q.isEmpty()) {
            mView.showMessage("请输入解梦内容");
            return;
        }
        mView.showLoading();
        getDream(q);
    }

    public void onItemClick(int position) {

        List<String> stringList = mDatas.get(position).getList();

        StringBuffer sbf = new StringBuffer();
        for (String s : stringList) {
            sbf.append(s).append("\n\n\n");
        }
        new SweetAlertDialog(mContext)
                .setTitleText(mDatas.get(position).getTitle())
                .setContentText(sbf.toString())
                .show();
    }

    private void getDream(String q) throws UnsupportedEncodingException {
        String content = URLDecoder.decode(q, "utf-8");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("key", "f86ed9f21931cd311deffada92b58ac7");
        options.put("full", "1");
        options.put("q", content);

        subscriber = new Subscriber<List<JuHeDream>>() {

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
                mView.showMessage(e.toString());
            }

            @Override
            public void onNext(List<JuHeDream> data) {
                for (JuHeDream  juheDream:data) {
                    Logger.e(juheDream.toString());
                }
                mDatas = data;
                mView.setListItem(mDatas);
            }
        };
        HttpJuHeMethods.getInstance().getJokesByHttpResultMap(subscriber,options);
    }

    public void destory(){
        subscriber.unsubscribe();
    }
}