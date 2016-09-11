package com.hfkj.redchildsupermarket.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 16:04
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class IndentBean extends BaseResponse implements Serializable{

    /**
     * orderList : [{"addressid":"145","carts":[{"id":53,"orderid":"201609080154241","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","state":2,"user_id":"147323808575613"}],"couponid":"1","deliveryType":1,"id":37,"invoiceContent":"办公用品","invoiceTitle":"悟空","invoiceType":0,"orderid":"201609080154241","paymentType":0,"price":50,"state":1,"time":1473270864610,"user_id":"147323808575613"}]
     * total : 1
     */

    private int total;
    /**
     * addressid : 145
     * carts : [{"id":53,"orderid":"201609080154241","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg",
     * "productName":"灰色孕妇服","productPrice":"50","state":2,"user_id":"147323808575613"}]
     * couponid : 1
     * deliveryType : 1
     * id : 37
     * invoiceContent : 办公用品
     * invoiceTitle : 悟空
     * invoiceType : 0
     * orderid : 201609080154241
     * paymentType : 0
     * price : 50
     * state : 1
     * time : 1473270864610
     * user_id : 147323808575613
     */

    private List<OrderListBean> orderList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        private String addressid;
        private String couponid;
        private int deliveryType;
        private int id;
        private String invoiceContent;
        private String invoiceTitle;
        private int invoiceType;
        private String orderid;
        private int paymentType;
        private int price;
        private int state;
        private long time;
        private String user_id;
        /**
         * id : 53
         * orderid : 201609080154241
         * pid : 1
         * pnum : 2
         * ppid : 0
         * productImageUrl : /images/topic/product/1.jpg
         * productName : 灰色孕妇服
         * productPrice : 50
         * state : 2
         * user_id : 147323808575613
         */

        private List<CartsBean> carts;

        public String getAddressid() {
            return addressid;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
        }

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public int getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(int deliveryType) {
            this.deliveryType = deliveryType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<CartsBean> getCarts() {
            return carts;
        }

        public void setCarts(List<CartsBean> carts) {
            this.carts = carts;
        }

        public static class CartsBean {
            private int id;
            private String orderid;
            private int pid;
            private int pnum;
            private int ppid;
            private String productImageUrl;
            private String productName;
            private String productPrice;
            private int state;
            private String user_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
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
}
