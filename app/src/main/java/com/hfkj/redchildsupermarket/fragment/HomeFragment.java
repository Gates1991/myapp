package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.HomeVPBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class HomeFragment extends BaseFragment {

    @Bind(R.id.rl)
    public RelativeLayout rl;
    private HomeViewPage   mViewPage;

    public List<HomeVPBean.HomeTopicBean> mHomeTopicBeen = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mViewPage == null) {
                mViewPage = new HomeViewPage(mHomeTopicBeen, mContext);
            }
            mViewPage.start();
            rl.removeAllViews();
            rl.addView(mViewPage);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpRetrofit httpRetrofit = retrofit.create(HttpRetrofit.class);
        Call<HomeVPBean> call = httpRetrofit.getHomeData();
        call.enqueue(new Callback<HomeVPBean>() {
            @Override
            public void onResponse(Call<HomeVPBean> call, Response<HomeVPBean> response) {
                if (response.isSuccessful()) {
                    HomeVPBean homeData = response.body();
                    parseNetData(homeData);
                }
            }

            @Override
            public void onFailure(Call<HomeVPBean> call, Throwable throwable) {
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseNetData(HomeVPBean homeData) {
        mHomeTopicBeen.clear();
        mHomeTopicBeen = homeData.getHomeTopic();
        mHandler.sendEmptyMessageDelayed(0, 1000);

    }

    private interface HttpRetrofit {
        @GET("home")
        Call<HomeVPBean> getHomeData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
