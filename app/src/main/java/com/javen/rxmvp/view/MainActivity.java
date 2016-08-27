package com.javen.rxmvp.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.javen.rxmvp.R;
import com.javen.rxmvp.basemvp.BaseMvpActivity;
import com.javen.rxmvp.bean.JuHeDream;
import com.javen.rxmvp.presenter.MvpPresenter;
import com.javen.rxmvp.view.Adapter.MyAdapter;

import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends BaseMvpActivity<MvpView, MvpPresenter> implements MvpView, AdapterView.OnItemClickListener {

    @BindView(R.id.id_dream_query)
    EditText dreamQuery;
    @BindView(R.id.id_dream_btn)
    Button dreamBtn;
    @BindView(R.id.id_dream_result)
    ListView listView;

    private Context mContext;
    MyAdapter myAdapter;

    SweetAlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.bind(this);
        initEvent();
    }



    private void initEvent() {
        listView.setOnItemClickListener(this);
    }

    @OnClick(R.id.id_dream_btn)
    public void onClick() {
        try {
            String q = dreamQuery.getText().toString();
            presenter.getData(q);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MvpPresenter initPresenter() {
        return new MvpPresenter(this);
    }

    @Override
    public void setListItem(List<JuHeDream> data) {
        if (myAdapter == null){
            myAdapter = new MyAdapter(mContext, data);
        }
        if (listView.getAdapter() == null){
            listView.setAdapter(myAdapter);
        }
        myAdapter.refresh(data);
    }

    @Override
    public void showMessage(String messgae) {
        Toast.makeText(mContext, messgae, Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        presenter.onItemClick(position);
    }

    @Override
    protected void onDestroy() {
        presenter.destory();
        super.onDestroy();
    }
}
