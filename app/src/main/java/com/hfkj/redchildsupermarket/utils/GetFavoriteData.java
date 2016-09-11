package com.hfkj.redchildsupermarket.utils;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/10 21:58
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/10$
 * @更新描述	${TODO}
 */

import com.hfkj.redchildsupermarket.bean.FavoriteBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class GetFavoriteData {




    private  String mLogin_user_id;
    private  String mLogin_token;
    private  List<FavoriteBean.ProductListBean> productList = new ArrayList<>();
    private OnComplateListener mComplateListener;

    public GetFavoriteData(String mLogin_user_id,String mLogin_token) {
        this.mLogin_token = mLogin_token;
        this.mLogin_user_id = mLogin_user_id;
    }


    public  void getNetData() {

        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).getFavoriteData("1","10",mLogin_token,mLogin_user_id).enqueue(new Callback<FavoriteBean>() {

            @Override
            public void onResponse(retrofit2.Call<FavoriteBean> call, Response<FavoriteBean> response) {
                FavoriteBean favoriteBean = response.body();
                List<FavoriteBean.ProductListBean> ctList = favoriteBean.getProductList();
                productList.addAll(ctList);
                    mComplateListener.OnComplated(productList);
            }

            @Override
            public void onFailure(retrofit2.Call<FavoriteBean> call, Throwable throwable) {
                mComplateListener.OnFailed();
            }
        });

    }
    private interface HttpApi {

        @GET("favorites")
        Call<FavoriteBean> getFavoriteData(@Query("page") String page, @Query("pageNum") String pageNum, @Query
                ("token") String
                token, @Query("userid") String userid);

    }

    public void setOnComplateListener(OnComplateListener listener){
        this.mComplateListener = listener;
    }


    public interface OnComplateListener{
        void OnComplated(List<FavoriteBean.ProductListBean> productList);
        void OnFailed();
    }
}
