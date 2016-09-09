package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xx on 2016/8/28.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    public   List<T> mDatas;
    public   Context mContext;

    public CommonAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas =datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    //    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
}
