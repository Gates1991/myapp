package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.adapter.ShopCarFinishAdapter;
import com.hfkj.redchildsupermarket.adapter.ShopCarShowAdapter;
import com.hfkj.redchildsupermarket.bean.ShoppingCarBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class CarFragment extends BaseFragment {


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
    private Retrofit             mRetrofit;
    private boolean              isFinish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if (hasShopping) {
            view = inflater.inflate(R.layout.fragment_hasshopping, null);
            ButterKnife.bind(this, view);

        } else {
            view = inflater.inflate(R.layout.fragment_noshopping, null);
            mIb_shop_view = (ImageButton) view.findViewById(R.id.ib_shop_view);
            mIb_shop_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity activity = (MainActivity) mContext;
                    activity.getBrand().setChecked(true);
                }
            });
        }
        ButterKnife.bind(this, view);
        mRlBottom.setVisibility(View.GONE);
        return view;
    }

    public void initData() {

        //从服务器获取数据
        mList.clear();
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofit.create(HttpApi.class)
                .getShopData("1473125231223", "14731252269010")
                .enqueue(new Callback<ShoppingCarBean>() {

                    @Override
                    public void onResponse(Call<ShoppingCarBean> call, Response<ShoppingCarBean> response) {
                        if (response.isSuccessful()) {
                            ShoppingCarBean carBean = response.body();
                            mList = carBean.getCart();
                            fillData(carBean);
                            mShopCarShowAdapter = new ShopCarShowAdapter(mContext, mList);
                            mShopCarFinishAdapter = new ShopCarFinishAdapter(mContext, mList);
                            mLvShopCarShow.setAdapter(mShopCarShowAdapter);
                            mLvShopCarFinish.setAdapter(mShopCarFinishAdapter);
                            mLvShopCarFinish.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onFailure(Call<ShoppingCarBean> call, Throwable throwable) {
                        Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void fillData(ShoppingCarBean carBean) {
        int totalNum = 0;
        int totalMoney = 0;
        for (int i = 0; i < carBean.getCart().size(); i++) {
            totalNum += carBean.getCart().get(i).getPnum();
            totalMoney += Integer.valueOf(carBean.getCart().get(i).getProductPrice()) * carBean.getCart().get(i).getPnum();
        }
        mShopTotalNum.setText(totalNum + "");
        mShopTotalMoney.setText(totalMoney + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.imgbtn_left, R.id.btn_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_left:
                Toast.makeText(mContext, "编辑被点击了", Toast.LENGTH_SHORT).show();
                //跳转到编辑页面,其实就是将下面的listview替换掉
                if (!isFinish) {
                    mTvTitleLeft.setText("完成");
                    isFinish = true;
                    mLvShopCarShow.setVisibility(View.GONE);
                } else {
                    mTvTitleLeft.setText("编辑");
                    isFinish = false;
                    mLvShopCarShow.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_right:
                Toast.makeText(mContext, "结算被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public interface HttpApi {
        @FormUrlEncoded  //进行表单url编码
        @POST(Constant.URL_CART)
        Call<ShoppingCarBean> getShopData(@Field("token") String token, @Field("userid") String userid);
    }
}
