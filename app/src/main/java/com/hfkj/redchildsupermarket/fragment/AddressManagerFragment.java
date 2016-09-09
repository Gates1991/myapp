package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 1:58
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddressManagerFragment extends BaseFragment {

    @Bind(R.id.btn_right)
    ImageButton btnRight;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_addressmanager, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        ButterKnife.bind(this, mView);
        return mView;
    }

    private void initTitleView() {

        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        ImageButton mImgbtn_right = (ImageButton) mView.findViewById(R.id.btn_right);
        mImgbtn_right.setVisibility(View.VISIBLE);

        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView tv_title_right = (TextView) mView.findViewById(R.id.tv_title_right);
        tv_title_right.setText("新增地址");

        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("地址管理");
    }


    @Override
    public void initData() {

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
}
