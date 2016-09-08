package com.hfkj.redchildsupermarket.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.GoodsListAdapter;
import com.hfkj.redchildsupermarket.bean.SearchGoodsBean;
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
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 栁年 on 2016/9/7.
 */
public class GoodsFragment extends BaseFragment implements View.OnClickListener {

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

    private RadioButton previousChecked;
    private String keyword;

    private List<SearchGoodsBean.ProductListBean> mDatas = new ArrayList();
    private String sort;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(mContext, R.layout.fragment_goods_item, null);

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
        mTvTitleName.setText("搜索结果");

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
        // TODO: 2016/9/7 json 数据解析处理
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

    @OnClick({R.id.rb_sales, R.id.rb_price, R.id.rb_appraise, R.id.rb_uprack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_title_left:
                mMainActivity.popBackStack();
                break;

            case R.id.rb_sales:

                sort = "saleDown";
                getPostHttp();
                //                rbSales.setTextColor(Color.WHITE);
                //                rbPrice.setTextColor(Color.parseColor("#88000000"));
                //                rbAppraise.setTextColor(Color.parseColor("#88000000"));
                //                rbUprack.setTextColor(Color.parseColor("#88000000"));
                previousChecked = rbSales;
                setCheckedTextColor(previousChecked);

                break;
            case R.id.rb_price:
                if (rbSales.isChecked()) {
                    sort = "priceDown";

                } else {

                    sort = "priceUp";
                }
                getPostHttp();
                //                rbSales.setTextColor(Color.parseColor("#88000000"));
                //                rbPrice.setTextColor(Color.WHITE);
                //                rbAppraise.setTextColor(Color.parseColor("#88000000"));
                //                rbUprack.setTextColor(Color.parseColor("#88000000"));
                previousChecked = rbPrice;
                setCheckedTextColor(previousChecked);
                break;
            case R.id.rb_appraise:
                //                sort="commentDown";// 评价排序接口错误
                sort = "priceDown";//使用价格排序
                getPostHttp();
//                rbPrice.setTextColor(Color.parseColor("#88000000"));
//                rbSales.setTextColor(Color.parseColor("#88000000"));
//                rbAppraise.setTextColor(Color.WHITE);
//                rbUprack.setTextColor(Color.parseColor("#88000000"));

                previousChecked = rbAppraise;
                setCheckedTextColor(previousChecked);
                break;
            case R.id.rb_uprack:
                sort = "shelvesDown";
                getPostHttp();
//                rbAppraise.setTextColor(Color.parseColor("#88000000"));
//                rbPrice.setTextColor(Color.parseColor("#88000000"));
//                rbSales.setTextColor(Color.parseColor("#88000000"));
//                rbUprack.setTextColor(Color.WHITE);
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
