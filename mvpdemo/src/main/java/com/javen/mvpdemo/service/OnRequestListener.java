package com.javen.mvpdemo.service;

import java.util.List;

public interface OnRequestListener {

    void onSuccess(List<String> data);
    void onFailed();
}