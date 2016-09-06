package com.hfkj.redchildsupermarket.bean;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/6 20:06
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/6$
 * @更新描述	${TODO}
 */

import java.util.List;

public class LimitShopintBean extends  BaseResponse{

    /**
     * listCount : 13
     * productList : [{"id":1,"leftTime":1447004517,"limitPrice":30,"name":"灰色孕妇服","pic":"/images/topic/product/1.jpg","price":50},{"id":2,"leftTime":1448004566,"limitPrice":1,"name":"孕妇裙","pic":"/images/topic/product/2.jpg","price":1},{"id":3,"leftTime":1447084515,"limitPrice":90,"name":"男士孕妇服","pic":"/images/topic/product/3.jpg","price":300},{"id":4,"leftTime":1447004517,"limitPrice":98,"name":"玩具小鸭子c","pic":"/images/topic/product/4.jpg","price":168},{"id":5,"leftTime":1448005514,"limitPrice":68,"name":"玩具汽车b","pic":"/images/topic/product/5.jpg","price":108},{"id":6,"leftTime":1448004519,"limitPrice":36,"name":"玩具积木a","pic":"/images/topic/product/6.jpg","price":52},{"id":7,"leftTime":1448005533,"limitPrice":36,"name":"玩具积木t","pic":"/images/topic/product/6.jpg","price":59},{"id":8,"leftTime":1448005533,"limitPrice":36,"name":"玩具积木c","pic":"/images/topic/product/5.jpg","price":66},{"id":9,"leftTime":1448005533,"limitPrice":36,"name":"玩具积木d","pic":"/images/topic/product/6.jpg","price":55},{"id":10,"leftTime":1448005533,"limitPrice":36,"name":"玩具积木e","pic":"/images/topic/product/6.jpg","price":65},{"id":11,"leftTime":1448005533,"limitPrice":36,"name":"玩具积木f","pic":"/images/topic/product/6.jpg","price":58},{"id":12,"leftTime":1448005533,"limitPrice":36,"name":"玩具积木g","pic":"/images/topic/product/6.jpg","price":57},{"id":13,"leftTime":1448005533,"limitPrice":36,"name":"玩具积木h","pic":"/images/topic/product/6.jpg","price":63}]
     * response : limitbuy
     * total : 13
     */

    private int listCount;
    private int    total;
    /**
     * id : 1
     * leftTime : 1447004517
     * limitPrice : 30
     * name : 灰色孕妇服
     * pic : /images/topic/product/1.jpg
     * price : 50
     */

    private List<ProductListBean> productList;

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        private int    id;
        private int    leftTime;
        private int    limitPrice;
        private String name;
        private String pic;
        private int    price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
