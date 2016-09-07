package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 19:51
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserInformationFragment extends BaseFragment {

    @Bind(R.id.tv_username_user)
    TextView tvUsernameUser;
    @Bind(R.id.tv_userlevel)
    TextView tvUserlevel;
    @Bind(R.id.tv_userintegral)
    TextView tvUserintegral;
    @Bind(R.id.my_indent)
    RelativeLayout myIndent;
    @Bind(R.id.address)
    RelativeLayout address;
    @Bind(R.id.coupon)
    RelativeLayout coupon;
    @Bind(R.id.favorite)
    RelativeLayout favorite;
    @Bind(R.id.ib_cancel)
    ImageButton ibCancel;
    private FragmentManager supportFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        View view = inflater.inflate(R.layout.fragment_userinformation, null);
        TextView mTv_title_layout = (TextView) view.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("用户信息");
        ImageButton mImgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) view.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        supportFragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
        ButterKnife.bind(this, view);
        return view;
    }

    //创建一个集合管理fragment
    List<BaseFragment> mUserInFragments = new ArrayList<>();

    @Override
    public void initData() {
        //TODO:
        // mUserInFragments.add()   //我的订单页
        // mUserInFragments.add()   //地址管理页
        // mUserInFragments.add()   //我的优惠券页
        // mUserInFragments.add()   //我的收藏夹页


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }




    @OnClick({R.id.imgbtn_left, R.id.my_indent, R.id.address, R.id.coupon, R.id.favorite, R.id.ib_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_left://返回
                mMainActivity.clearBackStack();
                break;
            case R.id.my_indent: // 我的订单
                break;
            case R.id.address: // 地址管理
                break;
            case R.id.coupon :    //我的优惠券
                break;
            case R.id.favorite:  //我的收藏夹
                break;
            case R.id.ib_cancel: // 注销按钮
                //点击退栈,到登录界面,清空sp
                SpUtil.clearData(mContext);
                mMainActivity.popBackStack();

                break;
        }
    }




}
