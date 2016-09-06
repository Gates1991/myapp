package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    public ShopCarFinishAdapter(Context context, List<ShoppingCarBean.CartBean> list) {
        this.mContext = context;
        this.mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.shopcar_finish_item, null);
            viewHolder = new ViewHolder(convertView);
            ShoppingCarBean.CartBean bean = mData.get(position);
            Glide.with(mContext.getApplicationContext()).load(Constant.BASE_URL + bean.getProductImageUrl())
                    .into(viewHolder.mIvShop);
            viewHolder.mTvProductName.setText(bean.getProductName());
            viewHolder.mProductPrice.setText("价格:" + bean.getProductPrice());
        /*    viewHolder.mTvProductNum.setText("数量:" + bean.getPnum());*/
            viewHolder.mProductTotalMoney.setText("小计:" + Integer.valueOf(bean.getProductPrice()) * bean.getPnum());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_shop)
        ImageView mIvShop;
        @Bind(R.id.tv_product_name)
        TextView  mTvProductName;
        @Bind(R.id.product_price)
        TextView  mProductPrice;
        @Bind(R.id.tv_product_num)
        TextView  mTvProductNum;
        @Bind(R.id.product_total_money)
        TextView  mProductTotalMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

   /* static class ViewHolder {
        @Bind(R.id.iv_shop)
        ImageView mIvShop;
        @Bind(R.id.tv_product_name)
        TextView  mTvProductName;
        @Bind(R.id.product_price)
        TextView  mProductPrice;
        @Bind(R.id.tv_product_num)
        TextView  mTvProductNum;
        @Bind(R.id.product_total_money)
        TextView  mProductTotalMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }*/
}
