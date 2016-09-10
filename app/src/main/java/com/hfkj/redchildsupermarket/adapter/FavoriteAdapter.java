package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/10 20:03
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/10$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.FavoriteBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FavoriteAdapter extends CommonAdapter<FavoriteBean.ProductListBean> {


    public FavoriteAdapter(Context context, List<FavoriteBean.ProductListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_fragment, null);
            holder = new ViewHolder();
            holder.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.mTvLastPrice = (TextView) convertView.findViewById(R.id.tv_last_price);
            holder.mTvNowPrice = (TextView) convertView.findViewById(R.id.tv_now_price);
            holder.mIvGoodsIcon = (ImageView) convertView.findViewById(R.id.iv_goods_icon);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        FavoriteBean.ProductListBean bean = getItem(position);

        Glide.with(mContext).load(Constant.BASE_URL+bean.getPic()).into(holder.mIvGoodsIcon);
        holder.mTvLastPrice.setText("市场价:"+bean.getMarketPrice());
        holder.mTvLastPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mTvNowPrice.setText("会员价:"+bean.getLimitPrice());
        holder.mTvTitle.setText(bean.getName());
        holder.mTvTime.setText("上架时间:"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(bean.getShelves()))));

        return convertView;
    }


    static class ViewHolder {
        ImageView mIvGoodsIcon;
        TextView mTvTitle;
        TextView mTvNowPrice;
        TextView mTvLastPrice;
        TextView mTvTime;

    }
}
