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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.LimitShopintBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LimitShopAdapter extends CommonAdapter<LimitShopintBean.ProductListBean> {

    @Bind(R.id.bt_limitshop)
    Button    mBtLimitshop;
    @Bind(R.id.iv_limitshop)
    ImageView mIvLimitshop;
    @Bind(R.id.tv_item_type)
    TextView  mTvItemType;
    @Bind(R.id.tv_item_price)
    TextView  mTvItemPrice;
    @Bind(R.id.tv_item_limitPrice)
    TextView  mTvItemLimitPrice;
    @Bind(R.id.tv_limittime)
    TextView  mTvLimittime;


    public LimitShopAdapter(Context context, List<LimitShopintBean.ProductListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_limitshop, null);
            ButterKnife.bind(this, convertView);
        }
        LimitShopintBean.ProductListBean productListBean = mDatas.get(position);
        Glide.with(mContext).load(Constant.BASE_URL+productListBean.getPic()).into(mIvLimitshop);
        mTvItemType.setText(productListBean.getName());
        mTvItemLimitPrice.setText(productListBean.getLimitPrice()+"");
        mTvLimittime.setText("限时"+productListBean.getLeftTime());
        mTvItemPrice.setText(productListBean.getPrice()+"价格");
        return convertView;
    }



    @OnClick(R.id.bt_limitshop)
    public void onClick() {

    }
}
