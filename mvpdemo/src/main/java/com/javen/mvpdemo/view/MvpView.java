package com.javen.mvpdemo.view;

import java.util.List;

public interface MvpView {
     //显示loading progress
     void showLoading();
     //隐藏loading progress
     void hideLoading();
     //ListView的初始化
     void setListItem(List<String> data);
     //Toast 消息
     void showMessage(String messgae);
}