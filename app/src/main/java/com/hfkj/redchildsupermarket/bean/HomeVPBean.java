package com.hfkj.redchildsupermarket.bean;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/5 22:55
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/5$
 * @更新描述	${TODO}
 */

import java.util.List;

public class HomeVPBean {

    /**
     * id : 123
     * pic : /images/home/image1.jpg
     * title : 活动1
     */


    public List<HomeTopicBean> homeTopic;

    public List<HomeTopicBean> getHomeTopic() {
        return homeTopic;
    }

    public void setHomeTopic(List<HomeTopicBean> homeTopic) {
        this.homeTopic = homeTopic;
    }

    public static class HomeTopicBean {
        private int    id;
        private String pic;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
