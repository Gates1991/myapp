package com.itheima.retrofitsample.bean;

/**
 * Created by CC on 2016/5/3.
 *
 * "error": {
 "code": "111",
 "msg": "ssss"
 }

 */

public class MyError {
    public String code;
    public String msg;

    @Override
    public String toString() {
        return "MyError{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
