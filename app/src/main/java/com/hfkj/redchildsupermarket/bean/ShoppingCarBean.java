package com.hfkj.redchildsupermarket.bean;

import java.util.List;

/**
 * Created by wf on 2016/9/6.
 */
public class ShoppingCarBean extends BaseResponse {

    /**
     * id : 59
     * pid : 6
     * pnum : 1
     * ppid : 0
     * productImageUrl : /images/topic/product/6.jpg
     * productName : 玩具积木a
     * productPrice : 52
     * productPropertyName :
     * state : 1
     * user_id : 14731252269010
     */

    private List<CartBean> cart;

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean {
        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        private boolean isChecked;
        private int     id;
        private int     pid;
        private int     pnum;
        private int     ppid;
        private String  productImageUrl;
        private String  productName;
        private String  productPrice;
        private String  productPropertyName;
        private int     state;
        private String  user_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public String getProductImageUrl() {
            return productImageUrl;
        }

        public void setProductImageUrl(String productImageUrl) {
            this.productImageUrl = productImageUrl;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductPropertyName() {
            return productPropertyName;
        }

        public void setProductPropertyName(String productPropertyName) {
            this.productPropertyName = productPropertyName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
