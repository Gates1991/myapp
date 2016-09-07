package com.hfkj.redchildsupermarket.http;

import com.hfkj.redchildsupermarket.http.response.ResponseHomeBigViewModel;
import com.hfkj.redchildsupermarket.http.response.ResponseProductListModel;
import com.hfkj.redchildsupermarket.http.response.ResponseSearchHotKeywordModel;
import com.hfkj.redchildsupermarket.http.response.ResponseUserInfoModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by CC on 2016/7/2.
 * Hello wolrd
 */
public interface HttpApi {
    /**
     * 首页轮播图的接口
     * @return
     */
    @GET("home")
    Call<ResponseHomeBigViewModel> getHomeTopic();

    /**
     *获取热门搜索关键字
     * @return
     */
    @GET("search/recommend")
    Call<ResponseSearchHotKeywordModel> getHotKeyWorld();


    /**
     * 关键字搜索商品列表
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<ResponseProductListModel> searchProdut(@FieldMap Map<String, String> params);

    /**
     * 这是用户登录的接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Call<ResponseUserInfoModel> login(@FieldMap Map<String, String> params);



}
