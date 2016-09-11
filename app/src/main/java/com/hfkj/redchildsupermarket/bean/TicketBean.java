package com.hfkj.redchildsupermarket.bean;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/11 16:09
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TicketBean extends BaseResponse {

    /**
     * cutmoney : 10
     * desc : 满100元减10元，仅限首单
     * fullmoney : 100
     * id : 1
     * name : 新用户专享
     */

    private CouponBean coupon;

    public CouponBean getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponBean coupon) {
        this.coupon = coupon;
    }

    public static class CouponBean {
        private int cutmoney;
        private String desc;
        private int fullmoney;
        private int id;
        private String name;

        public int getCutmoney() {
            return cutmoney;
        }

        public void setCutmoney(int cutmoney) {
            this.cutmoney = cutmoney;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getFullmoney() {
            return fullmoney;
        }

        public void setFullmoney(int fullmoney) {
            this.fullmoney = fullmoney;
        }

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
    }
}
