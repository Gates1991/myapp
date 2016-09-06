package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/5 22:16
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/5$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.fragment.HomeFragment;

import java.util.List;

public class HomeLVAdapter extends CommonAdapter<HomeFragment.ItemBean> {
    public HomeLVAdapter(Context context, List<HomeFragment.ItemBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
           convertView = View.inflate(mContext, R.layout.item_home, null);
        }
        ImageView item_icon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
        TextView item_title = (TextView) convertView.findViewById(R.id.tv_item_title);
        HomeFragment.ItemBean itemBean = mDatas.get(position);
        item_icon.setImageResource(itemBean.icon);
        item_title.setText(itemBean.itemTitle);
        return convertView;
    }
}
