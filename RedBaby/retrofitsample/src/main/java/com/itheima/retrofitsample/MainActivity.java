package com.itheima.retrofitsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itheima.retrofitsample.apiService.TopicService;
import com.itheima.retrofitsample.bean.TopicResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        httpRequest();
    }

    //1.添加联网权限
    //2.把Http Api转化为Java Interface

    private String baseUrl = "http://169.254.175.226:8080/market/";
    //3.初始化Retrofit，调用create(接口的class),生成接口对象
    public Retrofit mRetrofit = new Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public TopicService topicService = mRetrofit.create(TopicService.class);

    //4.调用定义好的方法，Call<返回的数据类型>
    public void httpRequest() {
//        page=1&pageNum=10
        Map<String, String> params = new HashMap<>();
        params.put("page","1");
        params.put("pageNum","10");
        final Call<TopicResponse> call = topicService.getTopicPostRequest(params);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    synRequest(call) ;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        asynRequest(call);
    }

    //5.拿到Call进行同步和异步。
    public void synRequest(Call<TopicResponse> call) throws IOException {
        Response<TopicResponse> response = call.execute();
        if (response.isSuccessful()) {
            TopicResponse data = response.body();
            System.out.println("synRequest:"+data.toString());
        }else{
            System.out.println("synRequest:出错了");
        }
    }

    public void asynRequest(Call<TopicResponse> call) {
        call.enqueue(new Callback<TopicResponse>() {
            @Override
            public void onResponse(Call<TopicResponse> call, Response<TopicResponse> response) {
                if (response.isSuccessful()) {
                    TopicResponse data = response.body();
                    System.out.println("asynRequest:"+data.toString());
                }else{
                    System.out.println("asynRequest:出错了");
                }
            }

            @Override
            public void onFailure(Call<TopicResponse> call, Throwable throwable) {
                System.out.println("synRequest:出错了");
            }
        });
    }



}
