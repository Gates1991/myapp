package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/6 9:30
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/6$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.BrandBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

public class BrandAdapter extends CommonAdapter<BrandBean.CategoryBean> {
    public BrandAdapter(Context context, List<BrandBean.CategoryBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_brand, null);
            holder.mItemIcon = (ImageView) convertView.findViewById(R.id.item_icon);
            holder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mTvTag = (TextView) convertView.findViewById(R.id.tv_tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String uri = getItem(position).getPic();

        Glide.with(mContext).load(Constant.BaseUrl + uri).into(holder.mItemIcon);
        holder.mTvName.setText(getItem(position).getName());
        holder.mTvTag.setText(getItem(position).getTag());
        return convertView;
    }

    private class ViewHolder {
        ImageView mItemIcon;
        TextView mTvName;
        TextView mTvTag;
    }
}

