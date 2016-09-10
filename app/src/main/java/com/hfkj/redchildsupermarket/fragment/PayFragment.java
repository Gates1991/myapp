package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.bean.BaseResponse;
import com.hfkj.redchildsupermarket.bean.IndentBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;
import com.hfkj.redchildsupermarket.view.ShowAlipayDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wf on 2016/9/9.
 */
public class PayFragment extends BaseFragment {
    @Bind(R.id.iv_order_all)
    ImageView mIvOrderAll;
    @Bind(R.id.iv_by_alipay)
    ImageView mIvByAlipay;
    private ShowAlipayDialog mDialog;
    private String mUseid;
    private String mToken;
    private int mTotalPay;
    private String orderId ;
    private TextView mOrder_money;

    @Nullable

    public static PayFragment newInstance() {
        return new PayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, null);
        mUseid = SpUtil.getinfo(mContext, "login_user_id", "");
        mToken = String.valueOf(SpUtil.getLonginfo(mContext, "login_token", 0));
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mTotalPay = bundle.getInt("id");
        mOrder_money = (TextView) view.findViewById(R.id.order_money);
        initData();
        return view;
    }

    @Override
    public void initData() {
        mOrder_money.setText(mTotalPay+"元");
        int pageNum = 1;
        int pageSzie = 10;
        int type = 0;
        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(com.hfkj.redchildsupermarket.http.HttpApi.class)
                .orderlist(mUseid,Long.parseLong(mToken),pageNum,pageSzie,type)
                .enqueue(new Callback<IndentBean>() {
                    @Override
                    public void onResponse(Call<IndentBean> call, Response<IndentBean> response) {
                        if (response.isSuccessful()) {
                            IndentBean body = response.body();
                            List<IndentBean.OrderListBean> orderList = body.getOrderList();
                            matchData(orderList);
                        }
                    }
                    @Override
                    public void onFailure(Call<IndentBean> call, Throwable throwable) {
                        Toast.makeText(mContext, "获取数据失败",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void matchData(List<IndentBean.OrderListBean> orderList) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getPrice() == mTotalPay) {
                orderId = orderList.get(i).getOrderid();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_order_all, R.id.iv_by_alipay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_order_all:
                ((MainActivity)mContext).addToBackStack(new MyIndentFragment());
                break;
            case R.id.iv_by_alipay:
                //弹出dialog
                //跳到订单详情界面
                if (mDialog == null) {
                    mDialog = new ShowAlipayDialog(mContext, R.style.dialog, new ShowAlipayDialog.LeaveMyDialogListener() {
                        @Override
                        public void onClick(View view) {
                            switch (view.getId()) {
                                case R.id.iv_pay:
                                    //跳到订单详情界面
                                    payComplete(Long.parseLong(mToken),mUseid,orderId,"itheima@itcast.cn","itheima123456",171);
                                    ((MainActivity)mContext).addToBackStack(new MyIndentFragment());
                                    Toast.makeText(mContext,"支付完成",Toast.LENGTH_SHORT).show();
                                    mDialog.dismiss();
                                    break;
                                case R.id.iv_cancel:
                                    mDialog.dismiss();
                                    break;
                            }
                        }
                    });
                }
                mDialog.show();
                break;
        }
    }

    private void payComplete(Long s1 ,String s2,String s3,String s4,String s5,int s6) {
        new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
               .create(HttpApi.class)
               .postPayOrder(s1,s2,s3,s4,s5,s6)
               .enqueue(new Callback<BaseResponse>() {
                   @Override
                   public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                   }

                   @Override
                   public void onFailure(Call<BaseResponse> call, Throwable throwable) {
                        Toast.makeText(mContext,"网络异常",Toast.LENGTH_SHORT).show();
                   }
               });
    }
    public interface HttpApi {
        @FormUrlEncoded  //进行表单url编码
        @POST(Constant.URL_CHECKOUT)
        Call<BaseResponse> postPayOrder(@Field("token") Long token,
                                        @Field("userid") String userid,
                                        @Field("orderid") String orderid,
                                        @Field("alipaycount") String paymentType,
                                        @Field("alipaypwd") String alipaypwd,
                                        @Field("paymoney") int paymoney );

    }

}
