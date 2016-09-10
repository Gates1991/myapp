package com.hfkj.redchildsupermarket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.adapter.AddressAdapter;
import com.hfkj.redchildsupermarket.bean.MyAddressBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.http.HttpApi;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 1:58
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddressManagerFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.btn_right)
    ImageButton btnRight;
    private View mView;
    private ListView mLv_address;
    private MyAddressBean myAddressBean;
    private Bundle mBundle;
    public static final int RESPONSE_CODE = 0x010;
    private MyAddressBean mMyAddressBean;
    private MyAddressBean.AddressListBean addressListBean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_addressmanager, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        //联网拿数据
        getAddressData();
        ButterKnife.bind(this, mView);
        return mView;
    }

    private void getDataFromAccountFrment() {
        mBundle = getArguments();
        boolean isOrder = mBundle.getBoolean("isOrder");
        System.out.println(isOrder);

    }


    @Override
    public void onResume() {

        super.onResume();
        getAddressData();
    }

    private void initTitleView() {

        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        mImgbtn_left.setOnClickListener(this);
        ImageButton mImgbtn_right = (ImageButton) mView.findViewById(R.id.btn_right);
        mImgbtn_right.setVisibility(View.VISIBLE);

        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView tv_title_right = (TextView) mView.findViewById(R.id.tv_title_right);
        tv_title_right.setText("新增地址");

        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("我的地址");
        mLv_address = (ListView)mView.findViewById(R.id.lv_address);
        mLv_address.setVerticalScrollBarEnabled(true);
        mLv_address.setOnItemClickListener(this);
    }


    @Override
    public void initData() {



    }

    private void getAddressData() {
        String userid = SpUtil.getinfo(mContext, "login_user_id", "");
        long token = SpUtil.getLonginfo(mContext, "login_token", 0);
//        System.out.println(tokenString);
//        long token = Long.parseLong(tokenString);


        //借口对接
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<MyAddressBean> call = httpApi.getAddressData(userid,token);

        call.enqueue(new Callback<MyAddressBean>() {
            @Override
            public void onResponse(Call<MyAddressBean> call, Response<MyAddressBean> response) {
                if (response.isSuccessful()) {
                    //响应成功
                    mMyAddressBean = response.body();
                    processRegisterBean(mMyAddressBean);
                    myAddressBean = response.body();
                        processRegisterBean(myAddressBean);
                } else {
                    Toast.makeText(mContext,"响应失败",Toast.LENGTH_SHORT).show();
                    //MyAddressBean.AddressListBean addressListBean = myAddressBean.getAddressList().get(0);
                }
            }

            @Override
            public void onFailure(Call<MyAddressBean> call, Throwable throwable) {
                Toast.makeText(mContext,"对接失败",Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void processRegisterBean(MyAddressBean myAddressBean) {
        if (TextUtils.equals("error", myAddressBean.response)) {
            Toast.makeText(mContext, "ERRORCODE:" + myAddressBean.error.code + "MSG:" + myAddressBean.error.msg, Toast.LENGTH_SHORT).show();
            //请重新登录(如果知道超时返回的code值就可以进行判断,在登录无效后,重新登录)
            mMainActivity.addToBackStack(new UserLoginFrament());

        } else {
            //正常对接
            //设置适配器 TODO
            System.out.println("能走过来?");
            System.out.println(myAddressBean);
            mLv_address.setAdapter(new AddressAdapter(mContext, myAddressBean.getAddressList()));
            addressListBean = myAddressBean.getAddressList().get(0);

            System.out.println("abcdf");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_right)
    public void onClick() {
        //暂时跳转,无逻辑
        mMainActivity.addToBackStack(new AddAddressFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_left:
                mMainActivity.popBackStack();
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO 跳转到条目编辑界面 modification
        boolean isOrderPage = SpUtil.getBoolean(mContext, "isOrderPage", false);
        if (isOrderPage) {
            Toast.makeText(mContext,"订单页面跳转",Toast.LENGTH_SHORT).show();
            Collections.reverse(mMyAddressBean.getAddressList());
            MyAddressBean.AddressListBean addressListBean = mMyAddressBean.getAddressList().get(position);
            //携带此对象到订单页面
            SpUtil.saveBoolean(mContext,"isOrderPage",false);
            sendResult(addressListBean);
        }else {

          //  mMainActivity.addToBackStack(new ModificationFragment());
            MyAddressBean.AddressListBean addressListBean = (MyAddressBean.AddressListBean) parent.getItemAtPosition(parent.getCount()-1-position);
            ModificationFragment modificationFragment = new ModificationFragment();

            mMainActivity.addToBackStack(modificationFragment);
            modificationFragment.setitemBean(addressListBean);
        }

//        int addressid = myAddressBean.getAddressList().get(0).getId();
//
//        SpUtil.saveIntinfo(mContext,"addressID",addressid);


    }

    private void sendResult( MyAddressBean.AddressListBean addressListBean) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent i = new Intent();
        i.putExtra("name",addressListBean.getName());
        i.putExtra("phone",addressListBean.getPhoneNumber());
        i.putExtra("address",addressListBean.getAddressDetail());
        getTargetFragment().onActivityResult(AccountFragment.REQUEST_CODE2,RESPONSE_CODE,i);
        ((MainActivity)mContext).popBackStack();
    }


}
