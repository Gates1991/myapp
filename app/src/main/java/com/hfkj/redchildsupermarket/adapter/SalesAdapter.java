package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 19:29
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.SalesBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

public class SalesAdapter extends CommonAdapter<SalesBean.TopicBean> {
    public SalesAdapter(Context context, List<SalesBean.TopicBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView= View.inflate(mContext, R.layout.item_sales, null);
        }
        TextView tv_item_sales = (TextView) convertView.findViewById(R.id.tv_item_sales);
        ImageView iv_item_sales = (ImageView) convertView.findViewById(R.id.iv_item_sales);
        SalesBean.TopicBean topicBean = mDatas.get(position);
        Glide.with(mContext).load(Constant.BASE_URL+topicBean.getPic()).into(iv_item_sales);
        tv_item_sales.setText(topicBean.getName());
        return convertView;
    }
}
