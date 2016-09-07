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

        return view;
    }

    @Override
    public void initData() {
        mBtTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleName.setText("搜索结果");
        lvGoods.setAdapter(new GoodsListAdapter(mContext,mDatas));
    }

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
}
