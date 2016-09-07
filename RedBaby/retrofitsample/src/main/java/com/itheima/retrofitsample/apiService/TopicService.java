package com.itheima.retrofitsample.apiService;

import com.itheima.retrofitsample.bean.TopicResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface TopicService {
    @GET("topic")
    Call<TopicResponse> getTopic(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("topic")
    Call<TopicResponse> getTopicPostRequest(@FieldMap Map<String, String> params);


}