package com.hfkj.redchildsupermarket.bean;

import java.util.List;

/**
 * Created by 栁年 on 2016/9/8.
 */
public class HelpCenterBean extends BaseResponse {

    /**
     * helpList : [{"id":1,"title":"标题1"},{"id":2,"title":"标题2"},{"id":3,"title":"标题3"}]
     * version : 1
     */

    private int version;
    /**
     * id : 1
     * title : 标题1
     */

    private List<HelpListBean> helpList;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<HelpListBean> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpListBean> helpList) {
        this.helpList = helpList;
    }

    public static class HelpListBean {
        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
