package com.hfkj.redchildsupermarket.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/9 23:30
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SaveAddBean extends BaseResponse {



        /**
         * addressArea : 密云县
         * addressDetail : 23
         * city : 县
         * default : false
         * id : 146
         * name : 23
         * phoneNumber : 322
         * province : 北京市
         * userid : 14731574662277
         * zipCode : 234
         */

        private List<AddressListBean> addressList;

        public List<AddressListBean> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AddressListBean> addressList) {
            this.addressList = addressList;
        }

        public static class AddressListBean {
            private String  addressArea;
            private String  addressDetail;
            private String  city;
            @SerializedName("default")
            private boolean defaultX;
            private int     id;
            private String  name;
            private String  phoneNumber;
            private String  province;
            private String  userid;
            private String  zipCode;

            public String getAddressArea() {
                return addressArea;
            }

            public void setAddressArea(String addressArea) {
                this.addressArea = addressArea;
            }

            public String getAddressDetail() {
                return addressDetail;
            }

            public void setAddressDetail(String addressDetail) {
                this.addressDetail = addressDetail;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public boolean isDefaultX() {
                return defaultX;
            }

            public void setDefaultX(boolean defaultX) {
                this.defaultX = defaultX;
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

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }
        }
    }



