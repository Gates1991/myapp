package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.SearchGoodsBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

/**
 * Created by 栁年 on 2016/9/7.
 */
public class GoodsListAdapter extends CommonAdapter {

    public GoodsListAdapter(Context context, List datas) {
        super(context, datas);
    }


    public View getView(int position, View convertView, ViewGroup paren) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.goods_list_item, null);
            holder.ivGoodsIcon = (ImageView) convertView.findViewById(R.id.iv_goods_icon);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvNowPrice = (TextView) convertView.findViewById(R.id.tv_now_price);
            holder.tvLastPrice = (TextView) convertView.findViewById(R.id.tv_last_price);
            holder.tvAppraise = (TextView) convertView.findViewById(R.id.tv_appraise);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SearchGoodsBean.ProductListBean bean = (SearchGoodsBean.ProductListBean) getItem(position);
        Glide.with(mContext.getApplicationContext()).load(Constant.BASE_URL + bean.getPic()).into(holder.ivGoodsIcon);

        holder.tvTitle.setText(bean.getName());
        holder.tvNowPrice.setText("现价:¥" + bean.getPrice());
        holder.tvLastPrice.setText("原价:¥" + bean.getMarketPrice());
        holder.tvLastPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvAppraise.setText("已有" + bean.getCommentCount() + "条评论");

        return convertView;
    }

    public class ViewHolder {

        ImageView ivGoodsIcon;

        TextView tvTitle;

        TextView tvNowPrice;

        TextView tvLastPrice;

        TextView tvAppraise;


    }
}
