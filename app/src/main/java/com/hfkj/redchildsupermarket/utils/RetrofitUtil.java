package com.hfkj.redchildsupermarket.utils;

import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.http.HttpApi;

import retrofit2.Retrofit;

/**
 * Created by CC on 2016/7/2.
 * Hello wolrd
 */
public class RetrofitUtil{

    private static HttpApi httpApi;

    public static HttpApi createHttpApiInstance(){
        if(httpApi==null){
            if(httpApi==null){
                httpApi=new Retrofit
                        .Builder()
                        .baseUrl(Constant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(HttpApi.class);
            }
        }
        return httpApi;
    }
}
