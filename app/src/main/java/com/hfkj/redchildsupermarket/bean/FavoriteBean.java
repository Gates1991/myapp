package com.hfkj.redchildsupermarket.bean;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/10 19:12
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/10$
 * @更新描述	${TODO}
 */

import java.util.List;

public class FavoriteBean extends BaseResponse {

    /**
     * productList : [{"available":true,"buyLimit":10,"commentCount":12,"id":12,"inventoryArea":"全国","isHot":12,
     * "isNew":12,"leftTime":1448005533,"limitPrice":36,"marketPrice":46,"name":"玩具积木g",
     * "pic":"/images/topic/product/6.jpg","price":57,"productDesc":"好玩具","product_property_id":"0","saleNum":36,
     * "score":12,"shelves":1439543847925},{"available":true,"buyLimit":10,"commentCount":9,"id":9,
     * "inventoryArea":"全国","isHot":9,"isNew":9,"leftTime":1448005533,"limitPrice":36,"marketPrice":43,
     * "name":"玩具积木d","pic":"/images/topic/product/6.jpg","price":55,"productDesc":"好玩具","product_property_id":"0",
     * "saleNum":36,"score":9,"shelves":1439543847925}]
     * total : 2
     */

    private int total;
    /**
     * available : true
     * buyLimit : 10
     * commentCount : 12
     * id : 12
     * inventoryArea : 全国
     * isHot : 12
     * isNew : 12
     * leftTime : 1448005533
     * limitPrice : 36
     * marketPrice : 46
     * name : 玩具积木g
     * pic : /images/topic/product/6.jpg
     * price : 57
     * productDesc : 好玩具
     * product_property_id : 0
     * saleNum : 36
     * score : 12
     * shelves : 1439543847925
     */

    private List<ProductListBean> productList;

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
        private boolean available;
        private int buyLimit;
        private int commentCount;
        private int id;
        private String inventoryArea;
        private int isHot;
        private int isNew;
        private int leftTime;
        private int limitPrice;
        private int marketPrice;
        private String name;
        private String pic;
        private int price;
        private String productDesc;
        private String product_property_id;
        private int saleNum;
        private int score;
        private long shelves;

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public int getBuyLimit() {
            return buyLimit;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInventoryArea() {
            return inventoryArea;
        }

        public void setInventoryArea(String inventoryArea) {
            this.inventoryArea = inventoryArea;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
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

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
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

        public String getProductDesc() {
            return productDesc;
        }

        public void setProductDesc(String productDesc) {
            this.productDesc = productDesc;
        }

        public String getProduct_property_id() {
            return product_property_id;
        }

        public void setProduct_property_id(String product_property_id) {
            this.product_property_id = product_property_id;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public long getShelves() {
            return shelves;
        }

        public void setShelves(long shelves) {
            this.shelves = shelves;
        }
    }
}
