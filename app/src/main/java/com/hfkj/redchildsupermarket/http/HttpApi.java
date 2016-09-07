package com.hfkj.redchildsupermarket.http;

import com.hfkj.redchildsupermarket.bean.CancelBean;
import com.hfkj.redchildsupermarket.bean.InformationBean;
import com.hfkj.redchildsupermarket.bean.LoginBean;
import com.hfkj.redchildsupermarket.bean.RecommandExpandBean;
import com.hfkj.redchildsupermarket.bean.RegisterBean;
import com.hfkj.redchildsupermarket.bean.SalesBean;
import com.hfkj.redchildsupermarket.http.response.ResponseHomeBigViewModel;
import com.hfkj.redchildsupermarket.http.response.ResponseProductListModel;
import com.hfkj.redchildsupermarket.http.response.ResponseSearchHotKeywordModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Call<LoginBean> login(@Field("username") String username, @Field("password") String password);


    /**
     *  用户注册的借口
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded //进行url表单编码
    @POST("register")
    Call<RegisterBean> register(@Field("username") String username, @Field("password") String password);


    /**
     * 用户信息借口
     * @param
     * @return
     */

    @GET("userinfo")
    Call<InformationBean> getInfoMsg(@Query("token") Long b,@Query("userid") String a );

    /**
     * 注销接口
     * @param userid
     * @return
     */

    @FormUrlEncoded
    @POST("logout")
    Call<CancelBean> logout(@Field("userid") String userid);


    /**
     * 获得促销页面的数据
     * @param value1
     * @param value2
     * @return
     */
    @GET("topic")
    Call<SalesBean> getSalesData(@Query("page") String value1, @Query("pageNum") String value2);

    /**
     * 推荐品牌的数据
     * @return
     */
    @GET("home")
    Call<RecommandExpandBean> getRecommandExpand();



}
