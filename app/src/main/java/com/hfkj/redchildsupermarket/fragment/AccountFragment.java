package com.hfkj.redchildsupermarket.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.adapter.ShopCarShowAdapter;
import com.hfkj.redchildsupermarket.bean.InvoiceInfoBean;
import com.hfkj.redchildsupermarket.view.ShowPayWayDialog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wf on 2016/9/7.
 */
public class AccountFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.tv_name)
    TextView       mTvName;
    @Bind(R.id.tv_telephone)
    TextView       mTvTelephone;
    @Bind(R.id.tv_address_detial)
    TextView       mTvAddressDetial;
    @Bind(R.id.rl_address)
    RelativeLayout mRlAddress;
    @Bind(R.id.rl_pay_way)
    RelativeLayout mRlPayWay;
    @Bind(R.id.rl_deliver)
    RelativeLayout mRlDeliver;
    @Bind(R.id.rl_invoice)
    RelativeLayout mRlInvoice;
    /*@Bind(R.id.tv_invoice_type)
    TextView       mTvInvoiceType;
    @Bind(R.id.tv_invoice_rise)
    TextView       mTvInvoiceRise;
    @Bind(R.id.tv_invoice_content)
    TextView       mTvInvoiceContent;*/
    @Bind(R.id.lv_deail_shopping)
    ListView       mLvDeailShopping;
    @Bind(R.id.tv_pay_total)
    TextView       mTvPayTotal;
    @Bind(R.id.contain_ship)
    TextView       mContainShip;
    private View mIv_submit;
    private TextView mTv_title_name;
    private Bundle mBundle;
    private ArrayList mList;
    private int mTotalPay;
    private ArrayList<String> payWayData = new ArrayList();
    private ArrayList<String> deliverData  = new ArrayList();
    private ShowPayWayDialog showPayWayDialog;
    private ShowPayWayDialog showDeliverDialog;
    private TextView mTv_deliver_time;
    private TextView mTv_payway;
    private InvoiceInfo invoiceInfo;
    public static final int REQUEST_CODE = 0x001;
    public static final int RESULT_CODE = 0x101;
    private TextView mTv_invoice_type;
    private TextView mTv_invoice_rise;
    private TextView mTv_invoice_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, null);

        ButterKnife.bind(this, view);
        mTv_title_name = (TextView) view.findViewById(R.id.tv_title_name);
        mTv_deliver_time = (TextView) view.findViewById(R.id.tv_deliver_time);
        mTv_payway = (TextView) view.findViewById(R.id.tv_payway);
        mIv_submit = view.findViewById(R.id.iv_submit);
        mTv_invoice_type = (TextView) view.findViewById(R.id.tv_invoice_type);
        mTv_invoice_rise = (TextView) view.findViewById(R.id.tv_invoice_rise);
        mTv_invoice_content = (TextView) view.findViewById(R.id.tv_invoice_content);
        initData();
        return view;
    }

    @Override
    public void initData() {
        payWayData.clear();
        deliverData.clear();
        payWayData.add("支付宝在线支付");
        payWayData.add("货到付款--现金");
        payWayData.add("货到付款--刷卡");
        deliverData.add("任何时间");
        deliverData.add("限工作日");
        deliverData.add("限休息日");
        mTv_title_name.setText("订单");
        mRlAddress.setOnClickListener(this);
        mRlPayWay.setOnClickListener(this);
        mIv_submit.setOnClickListener(this);
        mRlDeliver.setOnClickListener(this);
        mRlInvoice.setOnClickListener(this);
        mBundle = getArguments();
        mList = (ArrayList) mBundle.getSerializable("list");
        mTotalPay = mBundle.getInt("data");
        mLvDeailShopping.setAdapter(new ShopCarShowAdapter(mContext,mList));
        setListViewHeightBasedOnChildren(mLvDeailShopping);
        fillBottom(mTotalPay);
    }


    private void fillBottom(int mTotalPay) {
        mTvPayTotal.setText("应付金额:￥"+mTotalPay+".0");
        mTvPayTotal.setTextColor(Color.RED);
        mContainShip.setText("含运费:￥"+(0.0));
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_address:
                ((MainActivity)mContext).addToBackStack(new AddressManagerFragment());
                break;
            case R.id.rl_pay_way:
                if (showPayWayDialog == null) {
                    showPayWayDialog = new ShowPayWayDialog(mContext, payWayData);
                }
                showPayWayDialog.show();
                showPayWayDialog.setOnItemSelectItem(new ShowPayWayDialog.OnItemSelectedItem() {
                    @Override
                    public void onItemSelectItem(int position) {
                        mTv_payway.setText("配送方式 :  "+payWayData.get(position));
                        showPayWayDialog.dismiss();
                    }
                });
                break;
            case R.id.rl_deliver:
                if (showDeliverDialog == null) {
                    showDeliverDialog =  new ShowPayWayDialog(mContext,deliverData);
                }
                showDeliverDialog.show();
                showDeliverDialog.setOnItemSelectItem(new ShowPayWayDialog.OnItemSelectedItem() {
                    @Override
                    public void onItemSelectItem(int position) {
                        mTv_deliver_time.setText("送货时间 :  "+deliverData.get(position));
                        showDeliverDialog.dismiss();
                    }
                });
                break;
            case R.id.rl_invoice:
                if (invoiceInfo == null) {
                   // invoiceInfo = new InvoiceInfo();
                     invoiceInfo = InvoiceInfo.newInstance();
                }
                invoiceInfo.setTargetFragment(AccountFragment.this,REQUEST_CODE);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(
                        R.anim.fragment_slide_right_in, R.anim.fragment_slide_left_out,
                        R.anim.fragment_slide_left_in, R.anim.fragment_slide_right_out
                );
                transaction.hide(AccountFragment.this)
                        .add(R.id.fl_content, invoiceInfo)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.iv_submit:
                ((MainActivity)mContext).addToBackStack(PayFragment.newInstance());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) {
            return;
        }
        InvoiceInfoBean bean = (InvoiceInfoBean) data.getSerializableExtra("bean");
        mTv_invoice_type.setText("发票类型:\t"+ bean.invoiceType);
        mTv_invoice_rise.setText("发票抬头:\t"+ bean.invoiceTop);
        mTv_invoice_content.setText("发票内容:\t"+bean.invoiceContent);
    }

}
