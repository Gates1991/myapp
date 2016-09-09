package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.adapter.ShopCarFinishAdapter;
import com.hfkj.redchildsupermarket.adapter.ShopCarShowAdapter;
import com.hfkj.redchildsupermarket.bean.BaseResponse;
import com.hfkj.redchildsupermarket.bean.ShoppingCarBean;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class CarFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener {


    @Bind(R.id.tv_title_left)
    TextView       mTvTitleLeft;
    @Bind(R.id.tv_title_layout)
    TextView       mTvTitleLayout;
    @Bind(R.id.shop_total_num)
    TextView       mShopTotalNum;
    @Bind(R.id.shop_total_money)
    TextView       mShopTotalMoney;
    @Bind(R.id.lv_shop_car_finish)
    ListView       mLvShopCarFinish;
    @Bind(R.id.lv_shop_car_show)
    ListView       mLvShopCarShow;
    @Bind(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    private TextView    tvTitle;
    private ImageButton mIb_shop_view;
    private boolean                        hasShopping = true;
    private List<ShoppingCarBean.CartBean> mList       = new ArrayList<>();
    private ShopCarShowAdapter   mShopCarShowAdapter;
    private ShopCarFinishAdapter mShopCarFinishAdapter;

    private boolean              isFinish;
    private ImageButton mImgbtn_left;
    private ImageButton mBtn_right;
    private Retrofit mRetrofit;
    private List<ShoppingCarBean.CartBean> mShowList = new ArrayList<>();
    private CheckBox mChecked_all;
    private View mIv_allremove;
    private  AccountFragment accountFragment;
    private int mTotalMoney;
    private String mUserid;
    private String mTokenString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUserid = SpUtil.getinfo(mContext, "login_user_id", "");
        mTokenString = SpUtil.getinfo(mContext, "login_token", "");
      //  long token = Long.parseLong(tokenString);
       /* if (!TextUtils.isEmpty(userid) && !TextUtils.isEmpty(tokenString)) {
            //获取网络数据显示
        }*/

        View view = null;
        if (hasShopping) {
            view = inflater.inflate(R.layout.fragment_hasshopping, null);
            ButterKnife.bind(this, view);
            mImgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
            mBtn_right = (ImageButton) view.findViewById(R.id.btn_right);
            mImgbtn_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到编辑页面
                    if (!isFinish) {
                        mTvTitleLeft.setText("完成");
                        mLvShopCarFinish.setVisibility(View.VISIBLE);
                        isFinish = true;
                        mRlBottom.setVisibility(View.VISIBLE);
                        mLvShopCarShow.setVisibility(View.GONE);
                    }else {
                        mTvTitleLeft.setText("编辑");
                        isFinish = false;
                        mRlBottom.setVisibility(View.GONE);
                        mLvShopCarFinish.setVisibility(View.GONE);
                        showData();
                        mLvShopCarShow.setVisibility(View.VISIBLE);
                        mChecked_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (mChecked_all.isChecked()) {
                                    for (int i = 0; i < mList.size() ; i++) {
                                        mList.get(i).setChecked(true);
                                    }
                                }else {
                                    for (int i = 0; i < mList.size() ; i++) {
                                        mList.get(i).setChecked(false);
                                    }
                                }
                                mLvShopCarFinish.setAdapter(new ShopCarFinishAdapter(mContext,mList));
                            }
                        }
                        );
                        mIv_allremove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //获取选中项的cid,然后post到服务器删除
                                //遍历集合,获取每个cid
                                if (mChecked_all.isChecked()) {
                                    //跳转到空购物车页面
                                    hasShopping = false;
                                    Toast.makeText(mContext,"确定全部删除吗",Toast.LENGTH_SHORT).show();
                                }else {

                                }
                            }
                        });
                    }

                }
            });
            mBtn_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到结算页面
                    if (accountFragment == null) {
                        accountFragment = new AccountFragment();
                    }
                    ((MainActivity)mContext).addToBackStack(accountFragment, (ArrayList) mList,mTotalMoney);
                }
            });
        } else {
            view = goToNoShopping(inflater);
        }
        mChecked_all = (CheckBox) view.findViewById(R.id.checked_all);
        mIv_allremove = view.findViewById(R.id.iv_allremove);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @NonNull
    private View goToNoShopping(LayoutInflater inflater) {
        View view;
        view = inflater.inflate(R.layout.fragment_noshopping, null);
        mIb_shop_view = (ImageButton) view.findViewById(R.id.ib_shop_view);
        mIb_shop_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) mContext;
                activity.getBrand().setChecked(true);
            }
        });
        return view;
    }

    public void initData() {


        //从服务器获取数据
        mList.clear();
        getRetrfit();
        mRetrofit.create(HttpApi.class)
                .getShopData(mTokenString, mUserid)
                .enqueue(new Callback<ShoppingCarBean>() {
                    @Override
                    public void onResponse(Call<ShoppingCarBean> call, Response<ShoppingCarBean> response) {
                        if (response.isSuccessful()) {
                            ShoppingCarBean carBean = response.body();
                            mList = carBean.getCart();
                            fillData(mList);
                            mShopCarShowAdapter = new ShopCarShowAdapter(mContext, mList);
                            mShopCarShowAdapter.notifyDataSetChanged();
                            mShopCarFinishAdapter = new ShopCarFinishAdapter(mContext, mList);
                            mShopCarFinishAdapter.notifyDataSetChanged();
                            mLvShopCarShow.setAdapter(mShopCarShowAdapter);
                            mLvShopCarFinish.setAdapter(mShopCarFinishAdapter);
                            mLvShopCarFinish.setVisibility(View.GONE);
                            mRlBottom.setVisibility(View.GONE);
                            mShopCarFinishAdapter.setOnAddNum(CarFragment.this);
                            mShopCarFinishAdapter.setOnSubNum(CarFragment.this);
                        }
                    }

                    @Override
                    public void onFailure(Call<ShoppingCarBean> call, Throwable throwable) {
                        Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private Retrofit getRetrfit() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

    private void fillData(List<ShoppingCarBean.CartBean> list) {
        int totalNum = 0;
        mTotalMoney = 0;
        for (int i = 0; i < list.size(); i++) {
            totalNum += list.get(i).getPnum();
            mTotalMoney += Integer.valueOf(list.get(i).getProductPrice()) * list.get(i).getPnum();
        }
        mShopTotalNum.setText(totalNum + "");
        mShopTotalMoney.setText(mTotalMoney + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {

        Object tag = v.getTag();
        switch (v.getId()) {

            case R.id.iv_button_add:

                if (tag != null && tag instanceof Integer) {
                    int position = (int) tag;
                    ShoppingCarBean.CartBean cartBean = mList.get(position);
                    int num = cartBean.getPnum();
                    if (num < 10) {
                        num++;
                        //将增加后的数据post到服务器
                        postData2Server(mTokenString, mUserid, num,
                                cartBean.getPid(),
                                cartBean.getId());
                        mList.get(position).setPnum(num);
                        fillData(mList);
                        mLvShopCarFinish.setAdapter(new ShopCarFinishAdapter(mContext, mList));
                        /*SystemClock.sleep(1500);
                        showData();*/
                        Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mContext,"超过该商品的最大购买数量",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.iv_button_cut:
                if (tag != null && tag instanceof  Integer) {
                    int position = (int) tag;
                    ShoppingCarBean.CartBean cartBean = mList.get(position);
                    int num = cartBean.getPnum();
                    if (num > 1) {
                        num --;
                        postData2Server(mTokenString, mUserid, num,
                                cartBean.getPid(),
                                cartBean.getId());
                        mList.get(position).setPnum(num);
                        fillData(mList);
                        mLvShopCarFinish.setAdapter(new ShopCarFinishAdapter(mContext,mList));
                    }
                    Toast.makeText(mContext,"删减成功",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void postData2Server(String s, String s1, int num, int pid, int id) {
        mRetrofit.create(EditApi.class)
                .editShoppingNum(s,s1,num,pid,id).enqueue(new Callback<BaseResponse>() {
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
        @POST(Constant.URL_CART)
        Call<ShoppingCarBean> getShopData(@Field("token") String token, @Field("userid") String userid);
    }
    public interface EditApi {
        @FormUrlEncoded  //进行表单url编码
        @POST(Constant.URL_CART_EDIT)
        Call<BaseResponse> editShoppingNum(@Field("token") String token,
                                           @Field("userid") String userid,
                                           @Field("pnum") int pnum,
                                           @Field("pid") int pid,
                                           @Field("cid") int cid);
    }
    public void showData() {
        //从服务器获取数据
        mShowList.clear();
        mRetrofit.create(HttpApi.class)
                .getShopData("1473125231223", "14731252269010")
                .enqueue(new Callback<ShoppingCarBean>() {
                    @Override
                    public void onResponse(Call<ShoppingCarBean> call, Response<ShoppingCarBean> response) {
                        if (response.isSuccessful()) {
                            ShoppingCarBean carBean = response.body();
                            mShowList = carBean.getCart();
                            mLvShopCarShow.setAdapter(new ShopCarShowAdapter(mContext,mShowList));
                        }
                    }

                    @Override
                    public void onFailure(Call<ShoppingCarBean> call, Throwable throwable) {
                        Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
