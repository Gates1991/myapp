package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.GoodsListAdapter;
import com.hfkj.redchildsupermarket.bean.SearchGoodsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 栁年 on 2016/9/7.
 */
public class GoodsFragment extends BaseFragment {

    @Bind(R.id.rb_sales)
    RadioButton rbSales;
    @Bind(R.id.rb_price)
    RadioButton rbPrice;
    @Bind(R.id.rb_appraise)
    RadioButton rbAppraise;
    @Bind(R.id.rb_uprack)
    RadioButton rbUprack;
    @Bind(R.id.rg_sort)
    RadioGroup rgSort;
    @Bind(R.id.lv_goods)
    ListView lvGoods;

    @Bind(R.id.tv_title_name)
    TextView mTvTitleName;
    @Bind(R.id.bt_title_left)
    Button mBtTitleLeft;

    List<SearchGoodsResponse.ProductListBean> mDatas = new ArrayList();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(mContext, R.layout.fragment_goods_item, null);

        ButterKnife.bind(this, view);

        initView();
        initData();
        return view;
    }

    private void initView() {
        mBtTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleName.setText("搜索结果");
    }

    @Override
    public void initData() {

        lvGoods.setAdapter(new GoodsListAdapter(mContext,mDatas));
    }
//    private void getPostHttp(int childPosition) {
//        new Retrofit
//                .Builder()
//                .baseUrl(Constant.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(HttpApi.class).search(0, 20, "saleDown",keyword).enqueue(new Callback<SearchGoodsResponse>() {
//            @Override
//            public void onResponse(Call<SearchGoodsResponse> call, Response<SearchGoodsResponse> response) {
//                if (response.isSuccessful()) {
//                    SearchGoodsResponse searchGoodsResponse = response.body();
//                    System.out.println(searchGoodsResponse.toString());
//                    parseRespomse1(searchGoodsResponse);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SearchGoodsResponse> call, Throwable throwable) {
//                Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rb_sales, R.id.rb_price, R.id.rb_appraise, R.id.rb_uprack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_sales:
                break;
            case R.id.rb_price:
                break;
            case R.id.rb_appraise:
                break;
            case R.id.rb_uprack:
                break;
        }
    }

    private interface HttpApi {

        //POST 请求PSOT参数
        @FormUrlEncoded  //进行表单url编码
        @POST("search")
        Call<SearchGoodsResponse> search(@Field("page") int page, @Field("pageNum") int pageNum, @Field("orderby") String orderby, @Field("keyword") String keyword);

    }
}
