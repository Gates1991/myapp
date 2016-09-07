package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/6 19:31
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/6$
 * @更新描述	${TODO}
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.LimitShopAdapter;
import com.hfkj.redchildsupermarket.bean.LimitShopintBean;
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

public class LimitShoping extends BaseFragment {

    @Bind(R.id.lv_limitshoping)
    ListView mLvLimitshoping;
    @Bind(R.id.tv_title_name)
    TextView tv_title_name;
    @Bind(R.id.bt_title_left)
    Button   bt_title_left;
    private List<LimitShopintBean.ProductListBean> mProductList = new ArrayList<>();
    private LimitShopAdapter mLimitShopAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_limitshoping, null);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    public void initData() {
        getNetData();

    }

    private void initView() {
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_name.setText("限时抢购");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getNetData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpLimitShoping httpLimitShoping = retrofit.create(HttpLimitShoping.class);

        Call<LimitShopintBean> call = httpLimitShoping.getHomeData("1", "13");
        call.enqueue(new Callback<LimitShopintBean>() {
            @Override
            public void onResponse(Call<LimitShopintBean> call, Response<LimitShopintBean> response) {
                LimitShopintBean limitShopintBean = response.body();
                parseNetData(limitShopintBean);
            }

            @Override
            public void onFailure(Call<LimitShopintBean> call, Throwable throwable) {
                Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void parseNetData(LimitShopintBean limitShopintBean) {
        mProductList.clear();
        mProductList = limitShopintBean.getProductList();
        //   System.out.println(mProductList.size());
        mLimitShopAdapter = new LimitShopAdapter(mContext, mProductList);
        mLvLimitshoping.setAdapter(mLimitShopAdapter);

    }

    private interface HttpLimitShoping {
        @GET("limitbuy")
        Call<LimitShopintBean> getHomeData(@Query("page") String value1, @Query("pageNum") String value2);

    }

    @OnClick(R.id.bt_title_left)
    public void limitshop(View view){
//       mMainActivity.popBackStack();

        }

}
