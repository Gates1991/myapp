package com.hfkj.redchildsupermarket.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/7 19:10
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class InformationBean extends BaseResponse {


    /**
     * bonus : 0
     * couponid : 0
     * favoritesCount : 0
     * id : 50
     * level : 普通会员
     * logState : false
     * orderCount : 0
     * password : 123
     * token : 1473130706688
     * user_id : 14729981933930
     * username : 123@qq.com
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
    @DatabaseTable(tableName = "userinfobean")
    public static class UserInfoBean {
        public UserInfoBean(int bonus, int couponid, int favoritesCount, int id, String level, boolean logState, int orderCount, String password, long token, String user_id, String username) {

            this.bonus = bonus;
            this.couponid = couponid;
            this.favoritesCount = favoritesCount;
            this.id = id;
            this.level = level;
            this.logState = logState;
            this.orderCount = orderCount;
            this.password = password;
            this.token = token;
            this.user_id = user_id;
            this.username = username;
        }

        public UserInfoBean() {
        }

        @DatabaseField(columnName = "bonus")
        private int     bonus;
        @DatabaseField(columnName = "couponid")
        private int     couponid;
        @DatabaseField(columnName = "favoritesCount")
        private int     favoritesCount;
        @DatabaseField(columnName = "id")
        private int     id;
        @DatabaseField(columnName = "level")
        private String  level;
        @DatabaseField(columnName = "logState")
        private boolean logState;
        @DatabaseField(columnName = "orderCount")
        private int     orderCount;
        @DatabaseField(columnName = "password")
        private String  password;
        @DatabaseField(columnName = "token")
        private long    token;
        @DatabaseField(columnName = "user_id")
        private String  user_id;
        @DatabaseField(columnName = "username")
        private String  username;

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

        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "bonus=" + bonus +
                    ", couponid=" + couponid +
                    ", favoritesCount=" + favoritesCount +
                    ", id=" + id +
                    ", level='" + level + '\'' +
                    ", logState=" + logState +
                    ", orderCount=" + orderCount +
                    ", password='" + password + '\'' +
                    ", token=" + token +
                    ", user_id='" + user_id + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
    }




}
