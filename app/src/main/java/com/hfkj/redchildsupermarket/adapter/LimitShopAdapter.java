package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/6 23:09
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/6$
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
import com.hfkj.redchildsupermarket.bean.LimitShopintBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

import butterknife.OnClick;

public class LimitShopAdapter extends CommonAdapter<LimitShopintBean.ProductListBean> {

    private LimitShopintBean.ProductListBean mProductListBean;


    public LimitShopAdapter(Context context, List<LimitShopintBean.ProductListBean> datas) {
        super(context, datas);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_limitshop, null);
            viewHolder.mIvLimitshop = (ImageView) convertView.findViewById(R.id.iv_limitshop);
            viewHolder.mTvItemPrice = (TextView) convertView.findViewById(R.id.tv_item_price);
            viewHolder.mTvItemType = (TextView) convertView.findViewById(R.id.tv_item_type);
            viewHolder.mTvItemLimitPrice = (TextView) convertView.findViewById(R.id.tv_item_limitPrice);
            viewHolder.mTvLimittime = (TextView) convertView.findViewById(R.id.tv_limittime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
       mProductListBean = mDatas.get(position);
        Glide.with(mContext).load(Constant.BASE_URL+ mProductListBean.getPic()).into( viewHolder.mIvLimitshop);
        viewHolder.mTvItemType .setText(mProductListBean.getName());
        viewHolder.mTvItemLimitPrice.setText(mProductListBean.getLimitPrice()+"¥");
        String time = getTimeFromInt(mProductListBean.getLeftTime());
        System.out.println(time+position);
        viewHolder.mTvLimittime.setText(time);
        viewHolder.mTvItemPrice.setText("原价:"+mProductListBean.getPrice()+"¥");
        viewHolder.mTvItemPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        System.out.println("11111");

        return convertView;
    }

    private class ViewHolder{
        ImageView mIvLimitshop;
        TextView  mTvItemType;
        TextView  mTvItemPrice;
        TextView  mTvItemLimitPrice;
        TextView  mTvLimittime;
    }

    public String getTimeFromInt(long time) {
     //  System.out.println(time);
        time=time/1000;
        if (time <= 0) { return "已结束"; }
        long day = time / (1 * 60 * 60 * 24);
        long hour = time / (1 * 60 * 60) % 24;
        long minute = time / (1 * 60) % 60;
        long second = time / (1) % 60;
        return "剩余"+ hour + "小时" + minute + "分" + second + "秒";
    }





    @OnClick(R.id.bt_limitshop)
    public void onClick() {

    }
}
