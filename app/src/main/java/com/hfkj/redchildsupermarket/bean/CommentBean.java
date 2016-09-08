package com.hfkj.redchildsupermarket.bean;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/8 20:07
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/8$
 * @更新描述	${TODO}
 */

import java.util.List;

public class CommentBean extends BaseResponse {

    /**
     * product : [{"content":"这是一个好裙子","time":1439543897925,"title":"裙子","username":"杨柳"}]
     * response : product
     * total : 1
     */

    //private String response;
    private int total;
    /**
     * content : 这是一个好裙子
     * time : 1439543897925
     * title : 裙子
     * username : 杨柳
     */

    private List<ProductBean> product;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean {
        private String content;
        private long time;
        private String title;
        private String username;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
