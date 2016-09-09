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
import android.widget.AdapterView;
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
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DetailsFragment extends BaseFragment implements AdapterView.OnItemClickListener {

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
    private GoodsListAdapter mAdapter;
    private View mBtTitleLeft;
    private String mOrderby="saleDown";
    private ShangPingFragment mShangPingFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__libiao, null);
        mBtTitleLeft = view.findViewById(R.id.bt_title_left);
        Bundle bundle = getArguments();

        ButterKnife.bind(this, view);

        mCid = bundle.getInt("id");
       mBtTitleLeft.setVisibility(View.VISIBLE);
        if (mCid == 100) {
            mTvTitleName.setText("新品上市");
            mCid = 212;
        } else if (mCid == 200) {
            mTvTitleName.setText("热门单品");
            mCid = 121;
        } else if (mCid == 300){
            mTvTitleName.setText("促销商品");
            mCid = 121;
        }else {
            mTvTitleName.setText("商品列表");
        }

        initData();
        mMainActivity.isMainFrament = 2;
        mLvGoods.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void initData() {
        getNetData();
    }

    private void getNetData() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).getDetailsData("1", "10", mOrderby, mCid).enqueue(new Callback<SearchGoodsBean>() {


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
        mProductListBeen.clear();
        mProductListBeen.addAll(searchGoodsBean.getProductList());
        if (mAdapter == null) {
            mAdapter = new GoodsListAdapter(mContext, mProductListBeen);
        }
        mLvGoods.setAdapter(mAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SearchGoodsBean.ProductListBean bean = (SearchGoodsBean.ProductListBean) parent.getItemAtPosition(position);
        int beanId = bean.getId();
        if (mShangPingFragment == null) {
            mShangPingFragment = new ShangPingFragment();
        }
        mMainActivity.addToBackStack(mShangPingFragment, beanId);
    }

    @OnClick(R.id.bt_title_left)
    public void onClick() {
        mMainActivity.popBackStack();
    }


    private interface HttpApi {

        @GET("productlist")
        Call<SearchGoodsBean> getDetailsData(@Query("page") String page, @Query("pageNum") String pageNum, @Query
                ("orderby") String orderby, @Query("cId")
                                             int cId);
    }


}
