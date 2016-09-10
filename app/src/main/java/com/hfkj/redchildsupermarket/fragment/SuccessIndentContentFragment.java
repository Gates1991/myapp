package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

/**
 * Created by 栁年 on 2016/9/10.
 */
public class SuccessIndentContentFragment extends BaseFragment {

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_indent, null);
        initView();
        initData();
        return view;
    }

    private void initView() {
        TextView mTitle = (TextView) view.findViewById(R.id.tv_title_name);
        mTitle.setText("订单详情");
        Button mTv_back = (Button) view.findViewById(R.id.bt_title_left);
        mTv_back.setVisibility(View.VISIBLE);
        mTv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMainActivity.popBackStack2();

            }
        });

        TextView mIndentNum =  (TextView) view.findViewById(R.id.indent_num);
        TextView mtv_name =  (TextView) view.findViewById(R.id.tv_name);
        TextView mtv_telephone =  (TextView) view.findViewById(R.id.tv_telephone);
        TextView mTvAddressDetial =  (TextView) view.findViewById(R.id.tv_address_detial);
        TextView mTvPayway =  (TextView) view.findViewById(R.id.tv_payway);
        TextView mTvDeliverTime =  (TextView) view.findViewById(R.id.tv_deliver_time);
        TextView mTvInvoiceType =  (TextView) view.findViewById(R.id.tv_invoice_type);
        TextView mTvInvoiceRise =  (TextView) view.findViewById(R.id.tv_invoice_rise);
        TextView mTvInvoiceContent =  (TextView) view.findViewById(R.id.tv_invoice_content);
        TextView mTvPreferential =  (TextView) view.findViewById(R.id.tv_preferential);
        ListView mlvdeailShopping = (ListView) view.findViewById(R.id.lv_deail_shopping);
        TextView mTvPayTotal =  (TextView) view.findViewById(R.id.tv_pay_total);
        TextView mContainShip =  (TextView) view.findViewById(R.id.contain_ship);
        RelativeLayout mRlContinue = (RelativeLayout) view.findViewById(R.id.rl_continue);
    }

    public void initData() {

    }


}
