package com.hfkj.redchildsupermarket.bean;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/7 15:47
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class LoginBean extends BaseResponse {

    /**
     * bonus : 0
     * couponid : 0
     * favoritesCount : 0
     * id : 51
     * level : 普通会员
     * logState : false
     * orderCount : 0
     * password : 123456
     * token : 1473234533771
     * user_id : 14732099198240
     * username : xiaowen@redbaby.com.cn
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        private int bonus;
        private int couponid;
        private int favoritesCount;
        private int id;
        private String level;
        private boolean logState;
        private int orderCount;
        private String password;
        private long token;
        private String user_id;
        private String username;

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public int getCouponid() {
            return couponid;
        }

        public void setCouponid(int couponid) {
            this.couponid = couponid;
        }

        public int getFavoritesCount() {
            return favoritesCount;
        }

        public void setFavoritesCount(int favoritesCount) {
            this.favoritesCount = favoritesCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public boolean isLogState() {
            return logState;
        }

        public void setLogState(boolean logState) {
            this.logState = logState;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getToken() {
            return token;
        }

        public void setToken(long token) {
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
