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

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.LimitShopintBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class LimitShoping extends BaseFragment {
    @Bind(R.id.lv_limitshoping)
    ListView mLvLimitshoping;
    @Bind(R.id.bt_title_left)
    Button bt_title_left;
    @Bind(R.id.tv_title_name)
    TextView tv_title_name;


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


    }
    private interface HttpLimitShoping{
        @GET("limitbuy")
        Call<LimitShopintBean> getHomeData(@Query("page") String value1, @Query("pageNum") String value2);

    }

}
