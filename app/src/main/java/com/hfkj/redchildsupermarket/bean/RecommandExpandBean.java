package com.hfkj.redchildsupermarket.bean;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 21:06
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import java.util.List;

public class RecommandExpandBean extends BaseResponse {

    /**
     * key : 孕妈专区
     * value : [{"id":1203,"name":"防辐射","pic":"/images/brand/2104_1333163839_9.jpg"},{"id":1204,"name":"飞利浦","pic":"/images/brand/288_1339146655_3.jpg"}]
     */

    public List<BrandBean> brand;

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public static class BrandBean {
        private  String          key;
        /**
         * id : 1203
         * name : 防辐射
         * pic : /images/brand/2104_1333163839_9.jpg
         */

        public         List<ValueBean> value;

        public  String getKey() {
            return key;
        }


        public void setKey(String key) {
            this.key = key;
        }

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }

        public static class ValueBean {
            private int    id;
            private String name;
            private String pic;

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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
