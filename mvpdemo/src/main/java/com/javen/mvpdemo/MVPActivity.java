package com.javen.mvpdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.javen.mvpdemo.presenter.MvpPresenter;
import com.javen.mvpdemo.view.MvpView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MVPActivity extends AppCompatActivity implements MvpView, AdapterView.OnItemClickListener {
    @BindView(R.id.mvp_listview)
    ListView mvpListView;

    MvpPresenter mvpPresenter;
    SweetAlertDialog pd;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext= this;
        ButterKnife.bind(this);
        mvpListView.setOnItemClickListener(this);
        mvpPresenter = new MvpPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        mvpPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mvpPresenter.onItemClick(position);
    }

    @Override
    public void showLoading() {
        if (pd == null) {
            pd = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
            pd.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pd.setTitleText("Loading");
            pd.setCancelable(true);
        }
        pd.show();
    }

    @Override
    public void hideLoading() {
        pd.hide();
    }

    @Override
    public void setListItem(List<String> data) {
        ArrayAdapter adapter = new ArrayAdapter(MVPActivity.this,
                android.R.layout.simple_list_item_1, data);
        mvpListView.setAdapter(adapter);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}