package com.javen.mvpdemo.view;

import com.javen.mvpdemo.basemvp.BaseView;

import java.util.List;

public interface NewMvpView extends BaseView {
        void setListItem(List<String> data);
        void showMessage(String message);
}