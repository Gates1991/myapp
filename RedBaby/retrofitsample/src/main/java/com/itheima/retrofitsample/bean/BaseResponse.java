package com.itheima.retrofitsample.bean;

/**
 * Created by CC on 2016/5/3.
 *
 * {
 "response": "error",
 "error": {
 "code": "111",
 "msg": "ssss"
 }

 }

 */
public class BaseResponse {
    public String response;
    public MyError error;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "response='" + response + '\'' +
                ", error=" + error +
                '}';
    }
}
