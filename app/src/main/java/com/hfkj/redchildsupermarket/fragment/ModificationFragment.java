package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.GetAddBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.http.HttpApi;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/10 2:59
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ModificationFragment extends BaseFragment {
    @Bind(R.id.imgbtn_left)
    ImageButton imgbtnLeft;
    @Bind(R.id.tv_title_left)
    TextView tvTitleLeft;
    @Bind(R.id.tv_title_layout)
    TextView tvTitleLayout;
    @Bind(R.id.btn_right)
    ImageButton btnRight;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_consignee)
    EditText etConsignee;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.ll_pcd_address)
    LinearLayout llPcdAddress;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.email)
    EditText email;
    @Bind(R.id.bt_deleteAdd)
    Button btDeleteAdd;
    @Bind(R.id.bt_setDefu)
    Button btSetDefu;
    private long token_get;
    private String login_user_id;
    private GetAddBean getAddBean;
    private String login_token;
    private String mConsignee;
    private String mPhone;
    private String mCity;
    private String mAddress;
    private String mEmail;
    private int id_get;

    @Override
    public void initData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        View view = inflater.inflate(R.layout.fragment_addaddress, null);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAddDatas();
    }

    private void getAddDatas() {
        login_user_id = SpUtil.getinfo(mContext, "login_user_id", null);
        //token
        String login_token = SpUtil.getinfo(mContext, "login_token", null);
        //   id_get = saveAddBean.getAddressList().get(0).getId();
        String id_address = SpUtil.getinfo(mContext, "id_address", null);

        System.out.println(id_address);
        if (!TextUtils.isEmpty(id_address)) {

            id_get = Integer.parseInt(id_address);
        }
        token_get = Long.parseLong(login_token);
        new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .getAddData(login_user_id, token_get, id_get)
                .enqueue(new Callback<GetAddBean>() {
                    @Override
                    public void onResponse(Call<GetAddBean> call, Response<GetAddBean> response) {
                        if (response.isSuccessful()) {
                            getAddBean = response.body();
                            if (TextUtils.equals("error", getAddBean.response)) {
                                Toast.makeText(mContext, "ERRORCODE:" + getAddBean.error + "MSG:" + getAddBean.error.msg, Toast.LENGTH_SHORT).show();
                            } else {
                                System.out.println("获取地址成功");
                                //TODO
                                System.out.println(getAddBean.getAddress());

                                setData();
                            }

                        } else {
                            Toast.makeText(mContext, "响应失败", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<GetAddBean> call, Throwable throwable) {

                    }
                });

    }

    private void setData() {
        etConsignee.setText(getAddBean.getAddress().getName());
        etPhone.setText(getAddBean.getAddress().getPhoneNumber());
        tvCity.setText(getAddBean.getAddress().getProvince() + getAddBean.getAddress().getCity() + getAddBean.getAddress().getAddressArea());
        etAddress.setText(getAddBean.getAddress().getAddressDetail());
        email.setText(getAddBean.getAddress().getZipCode());

    }


    private void initView() {
        imgbtnLeft.setVisibility(View.VISIBLE);
        tvTitleLeft.setText("返回");
        tvTitleLayout.setText("修改地址");
        btnRight.setVisibility(View.VISIBLE);
        tvTitleRight.setText("保存");
        btDeleteAdd.setVisibility(View.VISIBLE);
        btSetDefu.setVisibility(View.VISIBLE);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.imgbtn_left, R.id.btn_right, R.id.bt_deleteAdd, R.id.bt_setDefu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_left:
                mMainActivity.popBackStack();
                break;
            case R.id.btn_right:
                mMainActivity.addToBackStack(new AddressManagerFragment());

//                id = 0;
//                login_user_id = SpUtil.getinfo(mContext, "login_user_id", null);
//                //token
//                login_token = SpUtil.getinfo(mContext, "login_token", null);
//                //姓名
//                mConsignee = etConsignee.getText().toString().trim();
//
//                mPhone = etPhone.getText().toString().trim();
//                //收货人
//                mConsignee = this.etConsignee.getText().toString().trim();
//                //手机
//                mPhone = this.etPhone.getText().toString().trim();
//                //省市区
//                mCity = tvCity.getText().toString().trim();
//                //详细地址
//                mAddress = etAddress.getText().toString().trim();
//                //邮编
//                mEmail = email.getText().toString().trim();
//
//                if (mConsignee.isEmpty() || mPhone.isEmpty() || mCity.isEmpty() || mAddress.isEmpty() || mEmail.isEmpty()) {
//                    Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
//                } else {
//                   saveAddress();
//                    System.out.println("能不能保存");
//                }
                break;
            case R.id.bt_deleteAdd:


                break;
            case R.id.bt_setDefu:
                break;
        }
    }


//    private void saveAddress() {
//        new Retrofit.Builder()
//                .baseUrl(Constant.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(HttpApi.class)
//                .addresssave(id, login_token, login_user_id, mConsignee, mPhone, cit1, pro1, con1, mAddress, mEmail)
//                .enqueue(new Callback<SaveAddBean>() {
//                    @Override
//                    public void onResponse(Call<SaveAddBean> call, Response<SaveAddBean> response) {
//                        if (response.isSuccessful()) {
//                            SaveAddBean saveAddBean = response.body();
//                            processLoginBean(saveAddBean);
//                        } else {
//                            Toast.makeText(mContext, "获取数据失败~", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<SaveAddBean> call, Throwable throwable) {
//                        Toast.makeText(mContext, "响应失败~", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//
//    private void processLoginBean(SaveAddBean saveAddBean) {
//
//        if (TextUtils.equals("error", saveAddBean.response)) {
//            Toast.makeText(mContext, "ERRORCODE:" + saveAddBean.error + "MSG:" + saveAddBean.error.msg, Toast.LENGTH_SHORT).show();
//
//        } else {
//            //获取地址
//            id_get = saveAddBean.getAddressList().get(0).getId();
//            String id_Str = String.valueOf(id_get);
//            SpUtil.saveinfo(mContext, "id_address", id_Str);
//            token_get = Long.parseLong(login_token);
//            //String id_String = String.valueOf(id);
//            // SpUtil.saveinfo(mContext,"addressid",id_String);
//            // System.out.println("保存能走到?");
//
//            //获取地址接口
//            //  getAddDatas();
//            mMainActivity.addToBackStack(new AddressManagerFragment());
//
//
//        }
//
//
//    }

    @OnClick(R.id.ll_pcd_address)
    public void onClick() {


    }
}
