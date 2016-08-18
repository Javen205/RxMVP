package com.javen.rxmvp.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.javen.rxmvp.R;
import com.javen.rxmvp.bean.JuHeDream;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends BaseAdapter {
    List<JuHeDream> mList;
    Context mContext;

    public MyAdapter(Context context, List<JuHeDream> list) {
        mList = list;
        mContext = context;
    }

    public void refresh(List<JuHeDream> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.dreamTitle.setText(mList.get(position).getTitle());
        holder.dreamDesc.setText(mList.get(position).getDes());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.dream_title)
        TextView dreamTitle;
        @BindView(R.id.dream_desc)
        TextView dreamDesc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}