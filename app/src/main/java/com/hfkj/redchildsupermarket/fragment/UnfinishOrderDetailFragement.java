package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/10 18:49
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/10$
 * @更新描述	${TODO}
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.UnFinishAdapter;
import com.hfkj.redchildsupermarket.bean.BaseResponse;
import com.hfkj.redchildsupermarket.bean.IndentBean;
import com.hfkj.redchildsupermarket.utils.RetrofitUtil;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnfinishOrderDetailFragement extends BaseFragment implements View.OnClickListener {

    TextView tv_title_name;
    Button   bt_title_left;
    @Bind(R.id.indent_num)//订单编号
            TextView       mIndentNum;
    @Bind(R.id.indent_condition)//已完成
            TextView       mIndentCondition;
    @Bind(R.id.tv_name) //姓名:
            TextView       mTvName;
    @Bind(R.id.tv_telephone)//电话:
            TextView       mTvTelephone;
    @Bind(R.id.tv_address_detial)//详细地址:
            TextView       mTvAddressDetial;
    @Bind(R.id.rl_address)
            RelativeLayout mRlAddress;
    @Bind(R.id.tv_payway)//支付方式:
            TextView       mTvPayway;
    @Bind(R.id.rl_pay_way)
            RelativeLayout mRlPayWay;
    @Bind(R.id.tv_deliver_time)//送货时间
            TextView       mTvDeliverTime;
    @Bind(R.id.rl_deliver)//送货方式
            RelativeLayout mRlDeliver;
    @Bind(R.id.rl_invoice)
            RelativeLayout mRlInvoice;
    @Bind(R.id.tv_invoice_type)//发票类型:
            TextView       mTvInvoiceType;
    @Bind(R.id.tv_invoice_rise)//发票抬头:
            TextView       mTvInvoiceRise;
    @Bind(R.id.tv_invoice_content)//发票内容:
            TextView       mTvInvoiceContent;
    @Bind(R.id.lv_deail_shopping)//商品详情listview
            ListView       mLvDeailShopping;
    @Bind(R.id.tv_pay_total)//订单金额
            TextView       mTvPayTotal;
    //tv_pay_total
    @Bind(R.id.rl_cancel)
            RelativeLayout mRlCancel;
    Button mTvCancel;
    Button bt_goon;
    @Bind(R.id.rl_continue)
    RelativeLayout mRlContinue;
    private List<IndentBean.OrderListBean.CartsBean> mCarts = new ArrayList<>();
    private ScrollView               mScrollView;
    private String                   mLogin_user_id;
    private long                     mLogin_token;
    private int                      mProductPrice;
    private IndentBean.OrderListBean mOrderListBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unfinishorderdetail, null);
        tv_title_name = (TextView) view.findViewById(R.id.tv_title_name);
        bt_title_left = (Button) view.findViewById(R.id.bt_title_left);
        mScrollView = (ScrollView) view.findViewById(R.id.sv);
        mLogin_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        mLogin_token = SpUtil.getLonginfo(mContext, "login_token", 0);
        mTvCancel = (Button) view.findViewById(R.id.tv_cancel);
        bt_goon = (Button) view.findViewById(R.id.tv_goon);
        bt_title_left.setOnClickListener(this);
        mMainActivity.isMainFrament = 2;
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_name.setText("订单详情");
        ButterKnife.bind(this, view);
        mTvCancel.setOnClickListener(this);
        bt_goon.setOnClickListener(this);
        initData();
        return view;
    }

    private void initData() {
        mCarts = mOrderListBean.getCarts();

        System.out.println("订单的商品数量"+mCarts.size());

        addData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_title_left:
                mMainActivity.popBackStack();
                break;
            case R.id.tv_cancel:
                cancelNet();
                mMainActivity.popBackStack();
                break;
            case R.id.tv_goon:
                PayFragment payFragment = new PayFragment();
                mMainActivity.addToBackStack(payFragment, mProductPrice);
                break;
        }
    }


    private void cancelNet() {
        String login_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        long login_token = SpUtil.getLonginfo(mContext, "login_token", 0);
        String orderid = mOrderListBean.getOrderid();
        com.hfkj.redchildsupermarket.http.HttpApi httpApi = RetrofitUtil.createHttpApiInstance();

        Call<BaseResponse> call = httpApi.cancelList(orderid, login_user_id, login_token);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable throwable) {
            }
        });

    }

    private void addData() {
        mIndentNum.setText("订单编号" + mOrderListBean.getOrderid());
        if (mOrderListBean.getState() == 0) {
            mIndentCondition.setText("未结算");
            //“state”,         //0 未结算，1：已经结算 2:已经取消
        } else if (mOrderListBean.getState() == 1) {
        mIndentCondition.setText("已经结算");
    } else if (mOrderListBean.getState() == 2) {
        mIndentCondition.setText("已经取消");
    }

        mProductPrice = 0;
        for (int i = 0; i < mCarts.size(); i++) {
            mProductPrice += Integer.valueOf(mCarts.get(i).getProductPrice());
        }

        mTvName.setText("8888");
        mTvTelephone.setText("88888888");
        mTvAddressDetial.setText("上海市航头镇8888");
        mTvPayway.setText("支付方式:" + "在线支付");
        mTvDeliverTime.setText("送货时间:" + "任何时间");
        mTvPayTotal.setText("订单金额: ¥" + mProductPrice);//订单金额
        // invoiceType:0,//0：不要发票，1 ：个人 2：单位
        if (mOrderListBean.getInvoiceType() == 0) {
            mTvInvoiceType.setText("发票类型:" + "不要发票");
        } else if (mOrderListBean.getInvoiceType() == 1) {
            mTvInvoiceType.setText("发票类型:" + "个人");
        } else {
            mTvInvoiceType.setText("发票类型:" + "单位");
        }
        //   invoiceTitle:”发票抬头”,
        mTvInvoiceContent.setText("办公用品:" + mOrderListBean.getInvoiceContent());
        mTvInvoiceRise.setText("发票抬头:" + mOrderListBean.getInvoiceTitle());
        mLvDeailShopping.setAdapter(new UnFinishAdapter(mContext, mCarts));
        setListViewHeightBasedOnChildren(mLvDeailShopping);

    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null)
            return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        mScrollView.scrollTo(0, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setitemBean(IndentBean.OrderListBean orderListBean) {
        this.mOrderListBean = orderListBean;
    }
}