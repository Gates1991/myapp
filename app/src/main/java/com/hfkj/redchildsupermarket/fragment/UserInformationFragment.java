package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.bean.CancelBean;
import com.hfkj.redchildsupermarket.bean.InformationBean;
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
 * @创建时间 2016/9/6 19:51
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserInformationFragment extends BaseFragment {




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
    private View mView;
    private String username;
    private String level;
    private int bonus;
    private int orderCount;
    private int favoritesCount;
    private int couponid;
    private String user_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        mView = inflater.inflate(R.layout.fragment_userinformation, null);
        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("用户信息");
        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        supportFragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
        ButterKnife.bind(this, mView);

        /**
         * 这个放哪?????
         */
        getdata();

        return mView;

    }
    //拿到当前gsonbean中的数据,
    private void getdata() {

        String userid = SpUtil.getinfo(mContext, "login_user_id", "");
        long token = SpUtil.getLonginfo(mContext, "login_token", 0);


        //借口对接
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<InformationBean> call = httpApi.getInfoMsg(token,userid);

        call.enqueue(new Callback<InformationBean>() {
            @Override
            public void onResponse(Call<InformationBean> call, Response<InformationBean> response) {
                if (response.isSuccessful()) {
                    //响应成功
                    InformationBean informationBean = response.body();
                    processRegisterBean(informationBean);
                } else {
                    Toast.makeText(mContext,"响应失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InformationBean> call, Throwable throwable) {
                Toast.makeText(mContext,"对接失败",Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void processRegisterBean(InformationBean informationBean) {
        if (TextUtils.equals("error", informationBean.response)) {
            Toast.makeText(mContext, "ERRORCODE:" + informationBean.error.code + "MSG:" + informationBean.error.msg, Toast.LENGTH_SHORT).show();
            //请重新登录(如果知道超时返回的code值就可以进行判断,在登录无效后,重新登录)
            mMainActivity.addToBackStack(new UserLoginFrament());

        } else {
            //正常对接
            InformationBean.UserInfoBean userInfo = informationBean.getUserInfo();
            user_id = userInfo.getUser_id();
            //会员积分
            bonus = userInfo.getBonus();
            //会员等级
            level = userInfo.getLevel();
            //会员名
            username = userInfo.getUsername();
            //订单数
            orderCount = userInfo.getOrderCount();
            //收藏总数
            favoritesCount = userInfo.getFavoritesCount();
            //礼品券
            couponid = userInfo.getCouponid();
            //数据展示
            ininViewShow();
        }

    }

    private void ininViewShow() {
        TextView tvUsernameUser = (TextView) mView.findViewById(R.id.tv_username_user);
        tvUsernameUser.setText("用户名:"+username);

        TextView tv_userlevel = (TextView) mView.findViewById(R.id.tv_userlevel);
        tv_userlevel.setText("会员等级:"+level);

        TextView tv_userintegral = (TextView) mView.findViewById(R.id.tv_userintegral);
        tv_userintegral.setText("总积分:"+bonus);

        TextView tv_my_indent = (TextView) mView.findViewById(R.id.tv_my_indent);
        tv_my_indent.setText("我的订单("+orderCount+")");

        TextView tv_my_coupon = (TextView) mView.findViewById(R.id.tv_my_coupon);
        tv_my_coupon.setText("我的优惠券/礼品卡("+couponid+")");

        TextView tv_my_favorite = (TextView) mView.findViewById(R.id.tv_my_favorite);
        tv_my_favorite.setText("我的收藏夹("+favoritesCount+")");

    }

    @Override
    public void onResume() {

        super.onResume();
    }



    public void initData() {


    }


    @OnClick({R.id.imgbtn_left, R.id.my_indent, R.id.address, R.id.coupon, R.id.favorite, R.id.ib_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_left://返回
                mMainActivity.clearBackStack();
                //mMainActivity.popBackStack2();
                break;
            case R.id.my_indent: // 我的订单
                mMainActivity.addToBackStack(new MyIndentFragment());
                break;
            case R.id.address: // 地址管理
                mMainActivity.addToBackStack(new AddressManagerFragment());
                break;
            case R.id.coupon :    //我的优惠券
                mMainActivity.addToBackStack(new MyTicketFragment());
                break;
            case R.id.favorite:  //我的收藏夹

                mMainActivity.addToBackStack(new MyFavoriteFragment());
                break;
            case R.id.ib_cancel: // 注销按钮
                //点击退栈,到登录界面,接口对接,清空sp
                Cancel();
                //退栈清空

         //       mMainActivity.popBackStack();
                break;
        }
    }

    private void Cancel() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .logout(user_id)
                .enqueue(new Callback<CancelBean>() {
                    @Override
                    public void onResponse(Call<CancelBean> call, Response<CancelBean> response) {
                        if (response.isSuccessful()) {
                            CancelBean cancelBean = response.body();
                            if (TextUtils.equals("error", cancelBean.response)) {

                            } else {
                                //清空sp中的数据
                         //       SpUtil.clearData(mContext);
                                SpUtil.saveinfo(mContext,"login_user_id",null);
                                SpUtil.saveLonginfo(mContext,"login_token",0);

                                //退栈
                                mMainActivity.popBackStack();

                            }

                        } else {
                            Toast.makeText(mContext, "响应失败", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<CancelBean> call, Throwable throwable) {

                    }
                });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
