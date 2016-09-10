package com.hfkj.redchildsupermarket.bean;

import java.util.List;

/**
 * Created by wf on 2016/9/10.
 */
public class SubmitOrder extends BaseResponse {

    /**
     * addressid : 1001
     * carts : [{"id":432,"pid":12,"pnum":1,"ppid":0,"productPrice":"57","state":1,"user_id":"14734336456577"}]
     * couponid : 1
     * deliveryType : 1
     * id : 0
     * invoiceContent : ä½ å¥½
     * invoiceTitle : å¾è¿
     * invoiceType : 0
     * orderid : 201609101906523
     * paymentType : 0
     * price : 57
     * state : 0
     * time : 1473505612474
     * user_id : 14734336456577
     */

    private OrderInfoBean orderInfo;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean {
        private String addressid;
        private String couponid;
        private int    deliveryType;
        private int    id;
        private String invoiceContent;
        private String invoiceTitle;
        private int    invoiceType;
        private String orderid;
        private int    paymentType;
        private int    price;
        private int    state;
        private long   time;
        private String user_id;
        /**
         * id : 432
         * pid : 12
         * pnum : 1
         * ppid : 0
         * productPrice : 57
         * state : 1
         * user_id : 14734336456577
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
            private int    id;
            private int    pid;
            private int    pnum;
            private int    ppid;
            private String productPrice;
            private int    state;
            private String user_id;

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
