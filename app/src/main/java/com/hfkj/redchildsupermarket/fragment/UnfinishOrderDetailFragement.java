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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.UnFinishAdapter;
import com.hfkj.redchildsupermarket.bean.UnfinishOrderDetailBean;
import com.hfkj.redchildsupermarket.http.HttpApi;
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
    Button bt_title_left;
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
    @Bind(R.id.contain_ship)//含运费
    TextView       mContainShip;
    @Bind(R.id.tv_cancel)//取消订单
    TextView       mTvCancel;
    @Bind(R.id.rl_cancel)
    RelativeLayout mRlCancel;
    @Bind(R.id.tv_)//继续支付
    TextView       mTv;
    @Bind(R.id.rl_continue)
    RelativeLayout mRlContinue;
    private List<UnfinishOrderDetailBean.OrderListBean> mOrderList=new ArrayList<>();
    private List<UnfinishOrderDetailBean.OrderListBean.CartsBean> mCarts=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unfinishorderdetail, null);
        tv_title_name = (TextView) view.findViewById(R.id.tv_title_name);
        bt_title_left = (Button) view.findViewById(R.id.bt_title_left);
        bt_title_left.setOnClickListener(this);
        mMainActivity.isMainFrament = 2;
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_name.setText("订单详情");
        initData();
        ButterKnife.bind(this, view);

        return view;
    }

    private void initData() {
        getNetData();
    }

    @Override
    public void onClick(View v) {
        mMainActivity.popBackStack();

    }

    public void getNetData() {

        String login_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        long login_token = SpUtil.getLonginfo(mContext, "login_token", 0);
        HttpApi httpApi = RetrofitUtil.createHttpApiInstance();
        Call<UnfinishOrderDetailBean> call = httpApi.getUnfinishOrderDetailData(login_user_id, login_token, 5, 2, 0);
        call.enqueue(new Callback<UnfinishOrderDetailBean>() {
            @Override
            public void onResponse(Call<UnfinishOrderDetailBean> call, Response<UnfinishOrderDetailBean> response) {
                if (response.isSuccessful()) {
                    UnfinishOrderDetailBean unfinishOrderDetailBean = response.body();
                    parseNetData(unfinishOrderDetailBean);
                }
            }

            @Override
            public void onFailure(Call<UnfinishOrderDetailBean> call, Throwable throwable) {

            }
        });

    }

    private void parseNetData(UnfinishOrderDetailBean unfinishOrderDetailBean) {
        mOrderList = unfinishOrderDetailBean.getOrderList();
        mCarts = mOrderList.get(0).getCarts();
        //加载页面数据
        addData();
    }

    private void addData() {

        mIndentNum.setText("订单编号"+mOrderList.get(0).getOrderid());
        if (mOrderList.get(0).getState() == 0) {
            mIndentCondition.setText("未结算");
            //“state”,         //0 未结算，1：已经结算 2:已经取消
        } else if (mOrderList.get(0).getState() == 1){
            mIndentCondition.setText("已经结算");
        } else if (mOrderList.get(0).getState() == 2) {
            mIndentCondition.setText("已经取消");
        }

        mTvName.setText("8888");
        mTvTelephone.setText("88888888");
       mTvAddressDetial.setText("上海市航头镇8888");
        mTvPayway.setText("支付方式:"+"在线支付");
        mTvDeliverTime.setText("送货时间:"+"任何时间");
        // invoiceType:0,//0：不要发票，1 ：个人 2：单位
        if (mOrderList.get(0).getInvoiceType() == 0) {
            mTvInvoiceType.setText("发票类型:"+"不要发票");
        } else if (mOrderList.get(0).getInvoiceType() == 1) {
            mTvInvoiceType.setText("发票类型:"+"个人");
        } else {
            mTvInvoiceType.setText("发票类型:"+"单位");
        }
     //   invoiceTitle:”发票抬头”,
        mTvInvoiceContent.setText("办公用品:"+mOrderList.get(0).getInvoiceContent());
        mTvInvoiceRise.setText("发票抬头:"+mOrderList.get(0).getInvoiceTitle());

        mLvDeailShopping.setAdapter(new UnFinishAdapter(mContext,mCarts));



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}