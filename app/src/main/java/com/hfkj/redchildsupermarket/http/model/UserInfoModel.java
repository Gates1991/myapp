package com.hfkj.redchildsupermarket.http.model;

/**
 * @author Chance
 *
 *"userInfo":{ 
    "userId": 30505,
	"bonus": 3002,                  //会员积分
	"level": "金卡",                 //会员等级
    "orderCount":"20",                      //所下订单数
    "favoritesCount":"12"                     //收藏总数
    "user_Id": 30505,
}

 *
 *
 */
public class UserInfoModel {
	public String userId;
	public String level;
	public int bonus;
	public int orderCount;
	public int favoritesCount;
	public long token;
	public String user_id;
	@Override
	public String toString() {
		return "UserInfoModel [userId=" + userId + ", level=" + level
				+ ", bonus=" + bonus + ", orderCount=" + orderCount
				+ ", favoritesCount=" + favoritesCount + ", token=" + token
				+ ", user_id=" + user_id + "]";
	}
}
