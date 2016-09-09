package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String mEmail;
    private String mAddress;
    private String mCity;
    private String mPhone;
    private String mConsignee;
    private String output;

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
                //TODO 进行判断,不为空则跳转到地址中心,空则提示,并且不跳转
                //先拿到填写的数据
                getEditAdd();
                //联网数据对接
                saveAddress();
                mMainActivity.addToBackStack(new AddressManagerFragment());
               // System.out.println("保存被点击了");
                break;
        }
    }

    private void saveAddress() {
        //TODO 等地址三级联动做完继续


    }

    private void getEditAdd() {
        //收货人
        mConsignee = this.etConsignee.getText().toString().trim();
        String urlmConsignee = getUrlDecode(mConsignee);
        System.out.println(urlmConsignee+"1");
        //手机
        mPhone = this.etPhone.getText().toString().trim();
       // String urlmPhone = getUrlDecode(mPhone);
        //省市区
        mCity = mTv_city.getText().toString().trim();
        String urlmCity = getUrlDecode(mCity);
        System.out.println(urlmCity+"2");
        //详细地址
        mAddress = etAddress.getText().toString().trim();
        String urlmAddress = getUrlDecode(mAddress);
        System.out.println(urlmAddress+"3");
        //邮编
        mEmail = email.getText().toString().trim();
        //String urlmEmail = getUrlDecode(mEmail);


    }

    private String getUrlDecode(String string) {
        try {
            output = java.net.URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return output;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_pcd_address)
    public void onClick() {
        System.out.println("点击事件");
    }
}
