package com.hfkj.redchildsupermarket.utils;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wf on 2016/9/7.
 */
public class NetData {

    private static Object bean;

    public static Object getDataFromNet(final Context context) {
        Retrofit mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofit.create(HttpApi.class).getData("","")
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()) {
                            bean = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable throwable) {
                        Toast.makeText(context,"网络异常",Toast.LENGTH_SHORT).show();
                    }
                });
        return  bean;
    }
    public interface HttpApi {
        @FormUrlEncoded  //进行表单url编码
        @POST(Constant.URL_CART)
        Call<Object> getData(@Field("token") String token, @Field("userid") String userid);
    }

}
