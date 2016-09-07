package com.itheima.retrofitsample.bean;

/**
 * Created by CC on 2016/5/3.
 */
public class Topic {
    public Integer id;
    public String name;
    public String pic;

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
