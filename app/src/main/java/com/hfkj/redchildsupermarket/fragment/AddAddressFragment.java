package com.hfkj.redchildsupermarket.fragment;

import android.app.Dialog;
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
import com.hfkj.redchildsupermarket.bean.SaveAddBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.http.HttpApi;
import com.hfkj.redchildsupermarket.utils.CityPicker;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.ScrollerNumberPicker;
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
 * @创建时间 2016/9/8 2:37
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddAddressFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.et_consignee)
    EditText etConsignee;
    @Bind(R.id.et_phone)
    EditText etPhone;


    @Bind(R.id.ll_pcd_address)
    LinearLayout llPcdAddress;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.email)
    EditText email;

    private View mView;
    private TextView mTv_city;


    private String mCity;


    private String output;
    private Button bt_dialog;
    private Dialog dialog;
    private CityPicker cityPicker;
    private String pro;
    private String cit;
    private String pcc;
    private String login_user_id;
    private long login_token;
    private String mname;
    private String mPhone;
    private String mcit;
    private String mpro;
    private String mcon;
    private String urlmAddress;
    private String mEmail;
    private String mConsignee;
    private String mAddress;
    private String con;
    private String cit1;
    private String pro1;
    private String con1;
    private int id;
    private int id_get;
    private long token_get;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_addaddress, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        ButterKnife.bind(this, mView);

        return mView;
    }

    private void initTitleView() {

        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        mImgbtn_left.setOnClickListener(this);
        ImageButton mImgbtn_right = (ImageButton) mView.findViewById(R.id.btn_right);
        mImgbtn_right.setVisibility(View.VISIBLE);
        mImgbtn_right.setOnClickListener(this);

        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView tv_title_right = (TextView) mView.findViewById(R.id.tv_title_right);
        tv_title_right.setText("保存");

        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("新增地址");
        mTv_city = (TextView) mView.findViewById(R.id.tv_city);
        //dialog的button


    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_left:
                mMainActivity.popBackStack();
                break;
            case R.id.btn_right:
                id = 0;
                login_user_id = SpUtil.getinfo(mContext, "login_user_id", null);
                //token
                login_token = SpUtil.getLonginfo(mContext, "login_token", 0);
                //姓名
                mConsignee = etConsignee.getText().toString().trim();

                mPhone = etPhone.getText().toString().trim();
                //收货人
                mConsignee = this.etConsignee.getText().toString().trim();
                //手机
                mPhone = this.etPhone.getText().toString().trim();
                //省市区
                mCity = mTv_city.getText().toString().trim();
                //详细地址
                mAddress = etAddress.getText().toString().trim();
                //邮编
                mEmail = email.getText().toString().trim();

                if (mConsignee.isEmpty() || mPhone.isEmpty() || mCity.isEmpty() || mAddress.isEmpty() || mEmail.isEmpty()) {
                    Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
                } else {
                    saveAddress();
                    System.out.println("能不能保存");
                }

                break;
            case R.id.bt_dialog:
                cit1 = cit;
                System.out.println(cit1);
                pro1 = pro;
                con1 = con;
                dialog.dismiss();

                break;
        }
    }


//    private void getEditAdd() {
////        //收货人
////        mConsignee = this.etConsignee.getText().toString().trim();
////        String urlmConsignee = getUrlDecode(mConsignee);
////        System.out.println(urlmConsignee + "1");
////        //手机
////        mPhone = this.etPhone.getText().toString().trim();
////        // String urlmPhone = getUrlDecode(mPhone);
////        //省市区
////        mCity = mTv_city.getText().toString().trim();
////        String urlmCity = getUrlDecode(mCity);
////        System.out.println(urlmCity + "2");
////        //详细地址
////        mAddress = etAddress.getText().toString().trim();
////        String urlmAddress = getUrlDecode(mAddress);
////        System.out.println(urlmAddress + "3");
////        //邮编
////        mEmail = email.getText().toString().trim();
////        //String urlmEmail = getUrlDecode(mEmail);
//
//
//        //userid
//        login_user_id = SpUtil.getinfo(mContext, "login_user_id", null);
//        //token
//        login_token = SpUtil.getinfo(mContext, "login_token", null);
//        mConsignee = etConsignee.getText().toString().trim();
//        // 姓名
////        mname = getUrlDecode(mConsignee);
////        System.out.println(mname);
//
//        //手机
//        mPhone = etPhone.getText().toString().trim();
//
//        //城市
////        mcit = getUrlDecode(cit);
////        System.out.println(mcit);
//
//        //省份
////        mpro = getUrlDecode(pro);
////        System.out.println(mpro);
//
//        //地区
////        mcon = getUrlDecode(con);
////        System.out.println(mcon);
//
////        mAddress = etAddress.getText().toString().trim();
////        //详细地址
////        urlmAddress = getUrlDecode(mAddress);
////        System.out.println(urlmAddress);
////
////        //邮编
////        mEmail = email.getText().toString().trim();
////
//
//
////        //联网数据对接
//        saveAddress();
//
//
//    }


    private void saveAddress() {
        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .addresssave(id,login_token, login_user_id, mConsignee, mPhone, cit1, pro1, con1, mAddress, mEmail)
                .enqueue(new Callback<SaveAddBean>() {
                    @Override
                    public void onResponse(Call<SaveAddBean> call, Response<SaveAddBean> response) {
                        if (response.isSuccessful()) {
                            SaveAddBean saveAddBean = response.body();
                            processLoginBean(saveAddBean);
                        } else {
                            Toast.makeText(mContext, "获取数据失败~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<SaveAddBean> call, Throwable throwable) {
                        Toast.makeText(mContext, "响应失败~", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void processLoginBean(SaveAddBean saveAddBean) {

        if (TextUtils.equals("error", saveAddBean.response)) {
            Toast.makeText(mContext, "ERRORCODE:" + saveAddBean.error + "MSG:" + saveAddBean.error.msg, Toast.LENGTH_SHORT).show();

        } else {
            //获取地址
            id_get = saveAddBean.getAddressList().get(0).getId();
//            List<Integer>  id=new ArrayList<>();
//            id.add(id_get);
//            String id_Str = String.valueOf(id_get);
//            System.out.println(id_Str);
//            SpUtil.saveinfo(mContext,"id_address",id_Str);
//

          //  token_get = Long.parseLong(login_token);
            //String id_String = String.valueOf(id);
           // SpUtil.saveinfo(mContext,"addressid",id_String);
            // System.out.println("保存能走到?");

            //获取地址接口
          //  getAddDatas();
            mMainActivity.addToBackStack(new AddressManagerFragment());


        }


    }

    private void getAddDatas() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .getAddData(login_user_id,token_get,id_get)
                .enqueue(new Callback<GetAddBean>() {
                    @Override
                    public void onResponse(Call<GetAddBean> call, Response<GetAddBean> response) {
                        if (response.isSuccessful()) {
                            GetAddBean getAddBean = response.body();
                            if (TextUtils.equals("error", getAddBean.response)) {
                                Toast.makeText(mContext, "ERRORCODE:" + getAddBean.error + "MSG:" + getAddBean.error.msg, Toast.LENGTH_SHORT).show();
                            } else {
                                System.out.println("获取地址成功");
                                //TODO
                                System.out.println(getAddBean.getAddress());
                            }

                        } else {
                            Toast.makeText(mContext,"响应失败",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<GetAddBean> call, Throwable throwable) {

                    }
                });

    }

//    private String getUrlDecode(String string) {
//        try {
//            output = URLDecoder.decode(string, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return output;
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_pcd_address)
    public void onClick() {
        //彈出三級聯動的對話框
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.address_layout_dialog);
        Button bt_dialog = (Button) dialog.findViewById(R.id.bt_dialog);
        bt_dialog.setOnClickListener(this);


        cityPicker = (CityPicker) dialog.findViewById(R.id.citypicker);
        final ScrollerNumberPicker mprovincePicker = (ScrollerNumberPicker) dialog.findViewById(R.id.province);
        final ScrollerNumberPicker mcityPicker = (ScrollerNumberPicker) dialog.findViewById(R.id.city);
        final ScrollerNumberPicker mcounyPicker = (ScrollerNumberPicker) dialog.findViewById(R.id.couny);
        cityPicker.setOnSelectingListener(new CityPicker.OnSelectingListener() {


            @Override
            public void selected(boolean selected) {
                System.out.println("selected: " + selected);
                System.out.println("selected_code: " + cityPicker.getCity_code_string());
                System.out.println("selected_str: " + cityPicker.getCity_string());
                System.out.println(mprovincePicker.getSelectedText());
                System.out.println(mcityPicker.getSelectedText());
                System.out.println(mcounyPicker.getSelectedText());
                pro = mprovincePicker.getSelectedText();
                cit = mcityPicker.getSelectedText();
                con = mcounyPicker.getSelectedText();
                pcc = cityPicker.getCity_string();
                //数据回填到表中
                mTv_city.setText(cityPicker.getCity_string());
            }

        });
        dialog.show();


    }
}
