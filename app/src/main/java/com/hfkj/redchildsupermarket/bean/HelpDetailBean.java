package com.hfkj.redchildsupermarket.bean;

import java.util.List;

/**
 * Created by 栁年 on 2016/9/9.
 */
public class HelpDetailBean extends BaseResponse {

    /**
     * content : 这是内容1
     * help_id : 1
     * id : 1
     * title : 这是标题1
     */

    private List<HelpDetailListBean> helpDetailList;

    public void setHelpDetailList(List<HelpDetailListBean> helpDetailList) {
        this.helpDetailList = helpDetailList;
    }

    public List<HelpDetailListBean> getHelpDetailList() {
        return helpDetailList;
    }

    public static class HelpDetailListBean {
        private String content;
        private int help_id;
        private int id;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getHelp_id() {
            return help_id;
        }

        public void setHelp_id(int help_id) {
            this.help_id = help_id;
        }

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

        @Override
        public String toString() {
            return "HelpDetailListBean{" +
                    "content='" + content + '\'' +
                    ", help_id=" + help_id +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HelpDetailBean{" +
                "helpDetailList=" + helpDetailList +
                '}';
    }
}

