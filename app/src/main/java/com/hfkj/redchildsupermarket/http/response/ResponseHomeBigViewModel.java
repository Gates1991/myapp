package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.HomeBigViewModel;



import java.util.List;

/**
 * @author Chance
 * {
"response": "home"
  "homeTopic":        //轮转大图 320*125
[
    {
      "id": 123,                  //专题id
      "item_title": "活动1",            //专题标题
      "pic": "http://localhost/1.png"  //专题图片URL
    }, 
    {
      "id": 124,
      "item_title": "活动2",
      "pic": "http://localhost/1.png"
	}
  {
      "id": 125,                  //专题id
      "item_title": "活动3",            //专题标题
      "pic": "http://localhost/1.png"  //专题图片URL
    },
  ]
}
 *
 *首页数据实体获取
 */
public class ResponseHomeBigViewModel extends BaseResponse {
	public List<HomeBigViewModel> homeTopic;
}
