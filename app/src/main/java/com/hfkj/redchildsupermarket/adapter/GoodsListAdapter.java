package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hfkj.redchildsupermarket.R;

import java.util.List;

/**
 * Created by 栁年 on 2016/9/7.
 */
public class GoodsListAdapter extends CommonAdapter {

    public GoodsListAdapter(Context context, List datas) {
        super(context, datas);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup paren) {
        if (convertView==null) {
            convertView=View.inflate(mContext, R.layout.goods_list_item,null);


        }


        return null;
    }
}
