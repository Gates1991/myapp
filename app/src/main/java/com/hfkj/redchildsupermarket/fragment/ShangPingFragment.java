package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 20:03
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.DetailsBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ShangPingFragment extends BaseFragment {

    @Bind(R.id.bt_title_left)//这是头布局的左斌按钮
            Button mBtTitleLeft;
    @Bind(R.id.tv_title_name)//这是头布局名字
            TextView mTvTitleName;
    @Bind(R.id.iv_title_icon)//这是头布局背景
            ImageView mIvTitleIcon;
    @Bind(R.id.bt_title_right)//这是头布局右边按钮
            Button mBtTitleRight;
    @Bind(R.id.iv_icon)//这是大图
            ImageView mIvIcon;
    @Bind(R.id.tv_name)//这是商品名
            TextView mTvName;
    @Bind(R.id.tv_marketPrice)//这是市场价
            TextView mTvMarketPrice;
    @Bind(R.id.tv_vipPrice)//这是会员价
            TextView mTvVipPrice;
    @Bind(R.id.tv_inventory)//这是库存数量
            TextView mTvInventory;
    @Bind(R.id.bt_addCar)//这是加入购物车
            Button mBtAddCar;
    @Bind(R.id.bt_collect)//这是收藏
            Button mBtCollect;
    @Bind(R.id.tv_location)//这是库存地址
            TextView mTvLocation;
    @Bind(R.id.tv_comment)//这是用户评论
            TextView mTvComment;
    private int mPid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shangping, null);

        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mMainActivity.isMainFrament = false;
        initView();
         mPid =  bundle.getInt("id");
        initData();
        return view;
    }

    private void initView() {
        mBtTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleName.setText("商品详情");
    }

    @Override
    public void initData() {
        getNetData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getNetData() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).getDetailsData(mPid).enqueue(new Callback<DetailsBean>() {
            @Override
            public void onResponse(Call<DetailsBean> call, Response<DetailsBean> response) {
                DetailsBean detailsBean = response.body();
                parseRespomse(detailsBean);
            }

            @Override
            public void onFailure(Call<DetailsBean> call, Throwable throwable) {

            }
        });
    }

    private void parseRespomse(DetailsBean detailsBean) {
        DetailsBean.ProductBean product = detailsBean.getProduct();
        //大图
        List<String> bigPic = product.getBigPic();
        Glide.with(mContext).load(Constant.BASE_URL+bigPic.get(0)).into(mIvIcon);
        //商品名
        String name = product.getName();
        mTvName.setText(name);
        //市场价
        int marketPrice = product.getMarketPrice();
        mTvMarketPrice.setText("¥"+marketPrice);
        mTvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //会员价
        int limitPrice = product.getLimitPrice();
        mTvVipPrice.setText("¥"+limitPrice);
        //数量
        int buyLimit = product.getBuyLimit();
        mTvInventory.setText(""+buyLimit);
        //地址
        String inventoryArea = product.getInventoryArea();
        mTvLocation.setText(inventoryArea);
        //评论数量
        int commentCount = product.getCommentCount();
        mTvComment.setText("共有"+commentCount+"条评论");


    }

    private interface HttpApi {

        @GET("product")
        Call<DetailsBean> getDetailsData(@Query("pId") int cId);
    }
}
