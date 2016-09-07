package com.itheima.retrofitsample.bean;

import java.util.List;

/**
 * Created by CC on 2016/5/3.
 */
public class TopicResponse extends BaseResponse {
public List<Topic> topic;
    public Integer total;

    @Override
    public String toString() {
        return "TopicResponse{" +
                "topic=" + topic +
                ", total=" + total +
                '}';
    }
}
