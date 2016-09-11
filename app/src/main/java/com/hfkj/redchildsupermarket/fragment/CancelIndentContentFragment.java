package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.UnFinishAdapter;
import com.hfkj.redchildsupermarket.bean.BaseResponse;
import com.hfkj.redchildsupermarket.bean.IndentBean;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 栁年 on 2016/9/10.
 */
public class CancelIndentContentFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private IndentBean bean;
    private String userid;
    private long token1;
    private TextView mIndentNum;
    private int position;
    private TextView mtv_name;
    private TextView mTvPayway;
    private TextView mTvDeliverTime;
    private TextView mTv_invoice;
    private TextView mTvInvoiceType;
    private TextView mTvInvoiceRise;
    private TextView mTvInvoiceContent;
    private TextView mTvPreferential;
    private ListView mlvdeailShopping;
    private TextView mTvPayTotal;
    private TextView mContainShip;
    private RelativeLayout mRlContinue;
    private IndentBean.OrderListBean orderListBean;
    private TextView mIndentCondition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_indent, null);
        Bundle bundle = getArguments();
        bean = (IndentBean) bundle.getSerializable("indent");
        position = bundle.getInt("position");

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

        mIndentNum = (TextView) view.findViewById(R.id.indent_num);
        mIndentCondition = (TextView) view.findViewById(R.id.indent_condition);
        mIndentCondition.setText("已取消");

        mtv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView mtv_telephone = (TextView) view.findViewById(R.id.tv_telephone);
        mtv_telephone.setText("电话：13813813813");
        TextView mTvAddressDetial = (TextView) view.findViewById(R.id.tv_address_detial);
        mTvAddressDetial.setText("天朝人民共和国 皇城 凌云殿");

        mTvPayway = (TextView) view.findViewById(R.id.tv_payway);
        mTvDeliverTime = (TextView) view.findViewById(R.id.tv_deliver_time);
        mTv_invoice = (TextView) view.findViewById(R.id.tv_invoice);//不用写

        mTvInvoiceType = (TextView) view.findViewById(R.id.tv_invoice_type);
        mTvInvoiceRise = (TextView) view.findViewById(R.id.tv_invoice_rise);
        mTvInvoiceContent = (TextView) view.findViewById(R.id.tv_invoice_content);

        mTvPreferential = (TextView) view.findViewById(R.id.tv_preferential);

        mlvdeailShopping = (ListView) view.findViewById(R.id.lv_deail_shopping);

        mTvPayTotal = (TextView) view.findViewById(R.id.tv_pay_total);
        mContainShip = (TextView) view.findViewById(R.id.contain_ship);
        mRlContinue = (RelativeLayout) view.findViewById(R.id.rl_continue);


    }

    public void initData() {
        //userid 值
        userid = SpUtil.getinfo(mContext, "login_user_id", "");
        //token  值
        token1 = SpUtil.getLonginfo(mContext, "login_token", 0);

        List<IndentBean.OrderListBean> orderList = bean.getOrderList();
        orderListBean = orderList.get(position);

        mIndentNum.setText("订单编号:" + orderListBean.getOrderid());

        mtv_name.setText("姓名:编号" + orderListBean.getAddressid());

        if (orderListBean.getPaymentType() == 0) {
            mTvPayway.setText("支付方式：支付宝在线支付");
        } else if (orderListBean.getPaymentType() == 1) {
            mTvPayway.setText("支付方式：货到付款-现金");
        } else {
            mTvPayway.setText("支付方式：货到付款-刷卡");
        }

        if (orderListBean.getDeliveryType() == 0) {
            mTvDeliverTime.setText("送货时间：任何时间");

        } else if (orderListBean.getDeliveryType() == 1) {
            mTvDeliverTime.setText("送货时间：限工作日");

        } else {
            mTvDeliverTime.setText("送货时间：限休息日");

        }


        if (orderListBean.getPaymentType() == 1) {
            mTvInvoiceType.setText("发票类型：普通发票");
        } else {
            mTvInvoiceType.setText("发票类型：电子发票");
        }

        mTvInvoiceRise.setText("发票抬头：" + orderListBean.getInvoiceTitle());
        mTvInvoiceContent.setText("发票内容：" + orderListBean.getInvoiceContent());

        mTvPayTotal.setText("应付金额：" + orderListBean.getPrice() + ".0元");
        mContainShip.setText("含运费:￥" + (0.0) + "元");

        mlvdeailShopping.setAdapter(new UnFinishAdapter(mContext, orderListBean.getCarts()));
        setListViewHeightBasedOnChildren(mlvdeailShopping);

        mRlContinue.setOnClickListener(this);

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
    }

    @Override
    public void onClick(View v) {
        getdelete();
        mMainActivity.popBackStack();
    }

    private void getdelete() {
        new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .getdeleteInfo(token1, userid, orderListBean.getOrderid())
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable throwable) {
                        Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public interface HttpApi {
        @FormUrlEncoded  //进行表单url编码
        @POST(Constant.URL_ORDER_DELETE)
        Call<BaseResponse> getdeleteInfo(
                @Field("token") Long token,
                @Field("userid") String userid,
                @Field("orderid") String orderid
        );
    }

}
