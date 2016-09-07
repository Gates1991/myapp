package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 9:43
 * @描述	$ 商品详情界面
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.GoodsListAdapter;
import com.hfkj.redchildsupermarket.bean.SearchGoodsBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DetailsFragment extends BaseFragment {

    @Bind(R.id.tv_title_name)
    TextView mTvTitleName;
    @Bind(R.id.rb_sales)
    RadioButton mRbSales;
    @Bind(R.id.rb_price)
    RadioButton mRbPrice;
    @Bind(R.id.rb_appraise)
    RadioButton mRbAppraise;
    @Bind(R.id.rb_uprack)
    RadioButton mRbUprack;
    @Bind(R.id.rg_sort)
    RadioGroup mRgSort;
    @Bind(R.id.lv_goods)
    ListView mLvGoods;
    private int mCid;
    private List<SearchGoodsBean.ProductListBean> mProductListBeen = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_item, null);

        Bundle bundle = getArguments();
        mCid = bundle.getInt("id");
        ButterKnife.bind(this, view);
        mTvTitleName.setText("商品列表");
        initData();
        return view;
    }

    @Override
    public void initData() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).getDetailsData("1","10","saleDown",mCid).enqueue(new Callback<SearchGoodsBean>() {


            @Override
            public void onResponse(Call<SearchGoodsBean> call, Response<SearchGoodsBean> response) {
                SearchGoodsBean searchGoodsBean = response.body();
                parseRespomse(searchGoodsBean);
            }

            @Override
            public void onFailure(Call<SearchGoodsBean> call, Throwable throwable) {

            }
        });
    }

    private void parseRespomse(SearchGoodsBean searchGoodsBean) {
        mProductListBeen.addAll(searchGoodsBean.getProductList());
        mLvGoods.setAdapter(new GoodsListAdapter(mContext,mProductListBeen));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private interface HttpApi {

        @GET("productlist")
        Call<SearchGoodsBean> getDetailsData(@Query("page") String page, @Query("pageNum") String pageNum, @Query("orderby") String orderby, @Query("cId")
        int cId);
    }
}
