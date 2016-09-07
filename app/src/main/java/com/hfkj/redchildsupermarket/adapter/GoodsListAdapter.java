package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.SearchGoodsResponse;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 栁年 on 2016/9/7.
 */
public class GoodsListAdapter extends CommonAdapter {

    public GoodsListAdapter(Context context, List datas) {
        super(context, datas);
    }


    public View getView(int position, View convertView, ViewGroup paren) {
        ViewHolder holder =null;
        if (convertView == null) {
            holder = new ViewHolder(convertView);
            convertView = View.inflate(mContext, R.layout.goods_list_item, null);

            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        SearchGoodsResponse.ProductListBean  bean = (SearchGoodsResponse.ProductListBean) getItem(position);
        Glide.with(mContext.getApplicationContext()).load(Constant.BASE_URL+bean.getPic()).into(holder.ivGoodsIcon);

        holder.tvTitle.setText(bean.getName());
        holder.tvNowPrice.setText(bean.getPrice());
        holder.tvLastPrice.setText(bean.getMarketPrice());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_goods_icon)
        ImageView ivGoodsIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_now_price)
        TextView tvNowPrice;
        @Bind(R.id.tv_last_price)
        TextView tvLastPrice;
        @Bind(R.id.tv_appraise)
        TextView tvAppraise;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
