package com.hfkj.redchildsupermarket.http;

import com.hfkj.redchildsupermarket.bean.CancelBean;
import com.hfkj.redchildsupermarket.bean.GetAddBean;
import com.hfkj.redchildsupermarket.bean.IndentBean;
import com.hfkj.redchildsupermarket.bean.InformationBean;
import com.hfkj.redchildsupermarket.bean.LoginBean;
import com.hfkj.redchildsupermarket.bean.MyAddressBean;
import com.hfkj.redchildsupermarket.bean.RecommandExpandBean;
import com.hfkj.redchildsupermarket.bean.RegisterBean;
import com.hfkj.redchildsupermarket.bean.SalesBean;
import com.hfkj.redchildsupermarket.bean.SaveAddBean;
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
    @GET("brand")
    Call<RecommandExpandBean> getRecommandExpand();


    /**
     * 订单管理接口
     *
     * @param type
     * @param token
     * @param userid
     * @param pagenum
     * @param pagesize
     * @return
     */

    //  @FormUrlEncoded
    //  @POST("orderlist")
    //  Call<IndentBean> orderlist(@Field("type") Integer type, @Field("token") Long token,@Query("userid") String userid,@Query("pageNum") Integer pagenum,@Query("pageSize") Integer pagesize);
    @FormUrlEncoded
    @POST("orderlist")
    Call<IndentBean> orderlist(@Field("userid") String userid, @Field("token") Long token, @Field("page") Integer pagenum, @Field("pageNum") Integer pagesize, @Field("type") Integer type);


    /**
     * 地址接口
     * @param userid
     * @param token
     * @return
     */
    @GET("addresslist")
    Call<MyAddressBean> getAddressData(@Query("userid") String userid, @Query("token") Long token );

    /**
     *
     * @param token
     * @param userid
     * @param name
     * @param phoneNumber
     * @param city
     * @param province
     * @param addressArea
     * @param addressDeatil
     * @param zipCode
     * @return
     */

    @FormUrlEncoded
    @POST("addresssave")
    Call<SaveAddBean> addresssave(@Field(value = "id",encoded = true) Integer id,@Field(value = "token",encoded = true) String token ,
                                  @Field(value = "userid",encoded = true) String userid,@Field(value = "name",encoded = true) String name,
                                  @Field(value = "phoneNumber",encoded = true) String phoneNumber,@Field(value = "city",encoded = true) String city,
                                  @Field(value = "province",encoded = true) String province,@Field(value = "addressArea",encoded = true) String addressArea,
                                  @Field(value = "addressDetail",encoded = true) String addressDeatil,@Field(value = "zipCode",encoded = true) String zipCode);


    /**
     * 获取地址接口
     * @param userid
     * @param token
     * @param id
     * @return
     */


    @GET("addressDetail")
    Call<GetAddBean> getAddData(@Query("userid") String userid, @Query("token") Long token,@Query("addressid") Integer id );

}
