package com.javen.rxmvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.javen.rxmvp.R;
import com.javen.rxmvp.api.HttpJuHeMethods;
import com.javen.rxmvp.api.subscribers.ProgressSubscriber;
import com.javen.rxmvp.bean.JuHeDream;
import com.javen.rxmvp.view.Adapter.MyAdapter;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AutoLayoutActivity {

    @BindView(R.id.id_dream_query)
    EditText dreamQuery;
    @BindView(R.id.id_dream_btn)
    Button dreamBtn;
    @BindView(R.id.id_dream_result)
    ListView listView;

    private Context mContext;
    MyAdapter myAdapter;

    List<JuHeDream> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        myAdapter = new MyAdapter(this, mDatas);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, "点击了:" + position, Toast.LENGTH_SHORT).show();
                List<String> stringList = mDatas.get(position).getList();

                StringBuffer sbf= new StringBuffer();
                for (String s : stringList) {
                    sbf.append(s).append("\n\n\n");
                }
                new SweetAlertDialog(mContext)
                        .setTitleText(mDatas.get(position).getTitle())
                        .setContentText(sbf.toString())
                        .show();
            }
        });
    }

    @OnClick(R.id.id_dream_btn)
    public void onClick() {
        try {
            String q = dreamQuery.getText().toString();
            if (q.isEmpty()){
                Toast.makeText(this, "请输入解梦内容", Toast.LENGTH_SHORT).show();
                return;
            }
            mDatas.clear();
            getDream(q);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private void getDream(String q) throws UnsupportedEncodingException {
        String content = URLDecoder.decode(q, "utf-8");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("key", "f86ed9f21931cd311deffada92b58ac7");
        options.put("full", "1");
        options.put("q", content);

        HttpJuHeMethods.getInstance().getJokesByHttpResultMap(new ProgressSubscriber<List<JuHeDream>>(this) {
            @Override
            public void onDoNext(List<JuHeDream> data) {
                for (JuHeDream  juheDream:data) {
                    Logger.e(juheDream.toString());
                }
                mDatas = data;
                myAdapter.refresh(mDatas);
            }

            @Override
            protected void onCustomError(Throwable e) {

                super.onCustomError(e);
            }
        }, options);
    }

}
