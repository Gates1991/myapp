package com.hfkj.redchildsupermarket.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.bean.InvoiceInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wf on 2016/9/8.
 */
public class InvoiceInfo extends BaseFragment  {
    @Bind(R.id.rg_invoice)
    RadioGroup mRgInvoice;
    @Bind(R.id.et_invoice)
    EditText   mEtInvoice;
    @Bind(R.id.spinner_invoice)
    Spinner    mSpinnerInvoice;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private String mType;
    private TextView mTv_title_name;
    private ImageView mIv_invoiceinfo_finish;
   //定义一个javabean存放发票信息

    private RadioButton mElectron;
    private RadioButton mNormal;
    private EditText mEt_invoice;
    public static final int RESPONSE_CODE = 0x010;
    public static InvoiceInfo newInstance(){
        return new InvoiceInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoiceinfo, null);
        mTv_title_name = (TextView) view.findViewById(R.id.tv_title_name);
        mIv_invoiceinfo_finish = (ImageView) view.findViewById(R.id.iv_invoiceinfo_finish);
        mElectron = (RadioButton) view.findViewById(R.id.electron);
        mNormal = (RadioButton) view.findViewById(R.id.normal);
        mEt_invoice = (EditText) view.findViewById(R.id.et_invoice);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void initData() {
        list.clear();
        final InvoiceInfoBean invoiceInfoBean = new InvoiceInfoBean();
        mTv_title_name.setText("发票信息");
        list.add("文具");
        list.add("生活用品");
        list.add("体育用品");
        list.add("电子产品");
        if (adapter == null) {
            adapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, list);
        }else {
            adapter.notifyDataSetChanged();
        }
        //设置下拉样式
        adapter.setDropDownViewResource(R.layout.dropdown_stytle);
        mSpinnerInvoice.setAdapter(adapter);
        mSpinnerInvoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mType = adapter.getItem(position);
                TextView v1 = (TextView)view;
                v1.setTextColor(Color.RED);
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setVisibility(View.VISIBLE);
            }
        });

        mSpinnerInvoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mSpinnerInvoice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        mRgInvoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mElectron.getId() == checkedId) {
                    invoiceInfoBean.invoiceType = "电子发票";
                    Toast.makeText(mContext,"电子发票",Toast.LENGTH_SHORT).show();
                }else if (mNormal.getId() == checkedId) {
                    invoiceInfoBean.invoiceType = "普通发票";
                }
            }
        });
            invoiceInfoBean.invoiceContent = mType;
        mIv_invoiceinfo_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //携带对象跳转到"订单"页面
                if (!TextUtils.isEmpty(mEt_invoice.getText().toString().trim())) {
                    invoiceInfoBean.invoiceTop = mEt_invoice.getText().toString().trim();
                    invoiceInfoBean.invoiceContent = mType;
                    sendResult(invoiceInfoBean);
                    ((MainActivity)mContext).popBackStack();
                }else {
                    Toast.makeText(mContext,"发票抬头信息不能为空!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

   private void sendResult(InvoiceInfoBean invoiceInfoBean) {
        if (getTargetFragment() == null) {
            return;
        }
            Intent i = new Intent();
            i.putExtra("bean",invoiceInfoBean);
            getTargetFragment().onActivityResult(AccountFragment.REQUEST_CODE,RESPONSE_CODE,i);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
