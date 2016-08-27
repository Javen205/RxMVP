package com.javen.rxmvp.view;

import com.javen.rxmvp.basemvp.BaseView;
import com.javen.rxmvp.bean.JuHeDream;

import java.util.List;

public interface MvpView extends BaseView {
     //ListView的初始化
     void setListItem(List<JuHeDream> data);
     //Toast 消息
     void showMessage(String messgae);
}