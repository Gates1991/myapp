package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/10 22:40
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/10$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.IndentBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UnFinishAdapter extends CommonAdapter<IndentBean.OrderListBean.CartsBean> {

    public UnFinishAdapter(Context context, List<IndentBean.OrderListBean.CartsBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_item_unfinish_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        IndentBean.OrderListBean.CartsBean cartsBean = mDatas.get(position);
        viewHolder.mTvTitle.setText(cartsBean.getProductName());
        //viewHolder.mIvGoodsIcon
        Glide.with(mContext).load(Constant.BASE_URL+cartsBean.getProductImageUrl()).into(viewHolder.mIvGoodsIcon);
        viewHolder.mTvNowPrice.setText("商品价格 ¥："+cartsBean.getProductPrice());
        viewHolder.mTvAppraise.setText("商品数量："+cartsBean.getPnum());
        return convertView;
    }
    static class ViewHolder {
        @Bind(R.id.iv_goods_icon)
        ImageView mIvGoodsIcon;
        @Bind(R.id.tv_title)
        TextView  mTvTitle;
        @Bind(R.id.tv_now_price)
        TextView  mTvNowPrice;
        @Bind(R.id.tv_appraise)
        TextView  mTvAppraise;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
