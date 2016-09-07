package com.hfkj.redchildsupermarket.bean;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 19:17
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import java.util.List;

public class SalesBean extends  BaseResponse {

    /**
     * response : topic
     * topic : [{"id":1,"name":"孕妇服装大特惠","pic":"/images/topic/1.jpg"},{"id":2,"name":"儿童玩具聚划算","pic":"/images/topic/2.jpg"},{"id":3,"name":"哈哈哈哈","pic":"/images/topic/1.jpg"}]
     * total : 3
     */

    private int             total;
    /**
     * id : 1
     * name : 孕妇服装大特惠
     * pic : /images/topic/1.jpg
     */

    private List<TopicBean> topic;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TopicBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    public static class TopicBean {
        private int    id;
        private String name;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
