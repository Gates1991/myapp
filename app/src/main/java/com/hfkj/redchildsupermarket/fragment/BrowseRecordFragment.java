package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 18:23
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class BrowseRecordFragment extends BaseFragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_browserecord, null);
        mMainActivity.isMainFrament = 2;
        inintTitleView();
        return mView;

        View view = View.inflate(mContext, R.layout.fragment_browserecord, null);

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();//获取携带的数据
        keyword = bundle.getString("keyword");

        mMainActivity.isMainFrament = false;

        sort = "saleDown";
        initView();
        initData();
        return view;
    }

    private void initView() {
        mBtTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleName.setText("浏览记录");

        //默认进来rbSales设置为true状态
        rbSales.setChecked(true);
        previousChecked = rbSales;
        setCheckedTextColor(previousChecked);

        mBtTitleLeft.setOnClickListener(this);


    }

    public void initData() {
        getPostHttp();

        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mMainActivity.addToBackStack(new ShangPingFragment(), mDatas.get(i).getId());
            }
        });

    }

    private void getPostHttp() {
        new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class).search(0, 20, sort, keyword).enqueue(new Callback<SearchGoodsBean>() {
            @Override
            public void onResponse(Call<SearchGoodsBean> call, Response<SearchGoodsBean> response) {
                if (response.isSuccessful()) {

                    SearchGoodsBean searchGoodsResponse = response.body();

                    parseRespomse1(searchGoodsResponse);
                }
            }

            @Override
            public void onFailure(Call<SearchGoodsBean> call, Throwable throwable) {
                Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseRespomse1(SearchGoodsBean searchGoodsResponse) {
        mDatas.clear();
        mDatas = searchGoodsResponse.getProductList();

        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(mContext, mDatas);
        lvGoods.setAdapter(goodsListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private int i=1;
    @OnClick({R.id.rb_sales, R.id.rb_price, R.id.rb_appraise, R.id.rb_uprack})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_title_left:
                mMainActivity.popBackStack();
                break;

            case R.id.rb_sales:
                i++;
                if (i%2==0) {
                    sort = "saleDown";

                }else {
                    sort = "saleDown";
                }

                getPostHttp();
                previousChecked = rbSales;
                setCheckedTextColor(previousChecked);

                break;
            case R.id.rb_price:
                i++;
                if (i%2==0) {
                    sort = "priceDown";

                }else {
                    sort = "priceUp";
                }
                getPostHttp();

                previousChecked = rbPrice;
                setCheckedTextColor(previousChecked);
                break;
            case R.id.rb_appraise:
                //                sort="commentDown";// 评价排序接口错误
                sort = "priceDown";//使用价格排序
                getPostHttp();


                previousChecked = rbAppraise;
                setCheckedTextColor(previousChecked);
                break;
            case R.id.rb_uprack:
                sort = "shelvesDown";
                getPostHttp();

                previousChecked = rbUprack;
                setCheckedTextColor(previousChecked);
                break;

        }


    }

    private void setCheckedTextColor(RadioButton previousChecked) {

        rbSales.setTextColor(Color.parseColor("#88000000"));
        rbPrice.setTextColor(Color.parseColor("#88000000"));
        rbAppraise.setTextColor(Color.parseColor("#88000000"));
        rbUprack.setTextColor(Color.parseColor("#88000000"));
        if (previousChecked.isChecked()) {
            previousChecked.setTextColor(Color.WHITE);
        }

    }

    private interface HttpApi {

        //POST 请求PSOT参数
        @FormUrlEncoded  //进行表单url编码
        @POST("search")
        Call<SearchGoodsBean> search(@Field("page") int page, @Field("pageNum") int pageNum, @Field("orderby") String orderby, @Field("keyword") String keyword);

    }
}
