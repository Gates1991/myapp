package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.ShoppingCarBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wf on 2016/9/6.
 */
public class ShopCarFinishAdapter extends BaseAdapter {


    private Context                        mContext;
    private List<ShoppingCarBean.CartBean> mData;
    private ImageView                    previousItem ;
    private View.OnClickListener onAddNum;    //加商品数量接口
    private View.OnClickListener onSubNum;    //减商品数量接口

    public ShopCarFinishAdapter(Context context, List<ShoppingCarBean.CartBean> list) {
        this.mContext = context;
        this.mData = list;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return  0;

    }

    @Override
    public ShoppingCarBean.CartBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         final ViewHolder mViewHolder ;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.shopcar_finish_item, null);
            mViewHolder = new ViewHolder(convertView);
            ShoppingCarBean.CartBean bean = mData.get(position);
            Glide.with(mContext.getApplicationContext()).load(Constant.BASE_URL + bean.getProductImageUrl())
                    .into(mViewHolder.mIvShop);
            mViewHolder.mTvProductName.setText(bean.getProductName());
            mViewHolder.mProductPrice.setText("价格:" + bean.getProductPrice());
            mViewHolder.mTvShopNumTotal.setText(bean.getPnum()+"");
            mViewHolder.mProductTotalMoney.setText("小计:" + Integer.valueOf(bean.getProductPrice()) * bean.getPnum());
            mViewHolder.mIvButtonAdd.setOnClickListener(onAddNum);
            mViewHolder.mIvButtonCut.setOnClickListener(onSubNum);
            mViewHolder.mCbCheck.setChecked(bean.isChecked());
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        //设置Tag,用于判断当前用户点击的哪一个条目的按钮
        mViewHolder.mIvButtonAdd.setTag(position);
        mViewHolder.mIvButtonCut.setTag(position);
        mViewHolder.mCbCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  int id = mData.get(position).getId();
                if (mViewHolder.mCbCheck.isChecked()) {
                    mData.get(position).setChecked(true);
                }else {
                    mData.get(position).setChecked(false);
                }
            }
        });
        mViewHolder.mCbCheck.setChecked(mData.get(position).isChecked());
        return convertView;
    }
    public void setOnAddNum(View.OnClickListener onAddNum) {
        this.onAddNum = onAddNum;
    }
    public void setOnSubNum(View.OnClickListener onSubNum) {
        this.onSubNum = onSubNum;
    }



    static class ViewHolder {
        @Bind(R.id.cb_check)
        CheckBox  mCbCheck;
        @Bind(R.id.iv_shop)
        ImageView mIvShop;
        @Bind(R.id.tv_product_name)
        TextView  mTvProductName;
        @Bind(R.id.product_price)
        TextView  mProductPrice;
        @Bind(R.id.tv_product_num)
        TextView  mTvProductNum;
        @Bind(R.id.iv_button_add)
        ImageView mIvButtonAdd;
        @Bind(R.id.iv_button_cut)
        ImageView mIvButtonCut;
        @Bind(R.id.tv_shop_num_total)
        TextView  mTvShopNumTotal;
        @Bind(R.id.product_total_money)
        TextView  mProductTotalMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
