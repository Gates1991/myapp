package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.HomePageViewPageAdapter;
import com.hfkj.redchildsupermarket.bean.HomeViewPageBean;
import com.hfkj.redchildsupermarket.bean.HomeViewPageBean.HomeTopicBean;

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


    @Bind(R.id.search_et)
    EditText  mSearchEt;
    @Bind(R.id.vp)
    ViewPager mVp;
    @Bind(R.id.lv)
    ListView  mLv;
    private List<HomeViewPageBean.HomeTopicBean> mHomeTopic;
   private  List<HomeTopicBean>  homeTopicbean=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initData();
        mVp.setAdapter(new HomePageViewPageAdapter(homeTopicbean,mContext));
       // mLv.setAdapter();
        return view;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpRetrofit httpRetrofit = retrofit.create(HttpRetrofit.class);
        Call<HomeViewPageBean> call = httpRetrofit.getHomeData();
        call.enqueue(new Callback<HomeViewPageBean>() {
            @Override
            public void onResponse(Call<HomeViewPageBean> call, Response<HomeViewPageBean> response) {
                if (response.isSuccessful()) {
                    HomeViewPageBean homeData = response.body();
                    parseNetData(homeData);
                }
            }

            @Override
            public void onFailure(Call<HomeViewPageBean> call, Throwable throwable) {
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void parseNetData(HomeViewPageBean homeData) {
        mHomeTopic = homeData.getHomeTopic();
        homeTopicbean.clear();
        for (int i = 0; i <mHomeTopic.size() ; i++) {
            homeTopicbean.add(mHomeTopic.get(i));
        }


    }

    private  interface  HttpRetrofit{
            @GET("home")
        Call<HomeViewPageBean> getHomeData();
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
