package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.UserInfoModel;

/**
 * @author Chance
 * 
 * 
 *         首页数据实体获取
 * 
 * 
 */

/**
 * @author Chance
 *
 */
public class ResponseUserInfoModel extends BaseResponse {
	public UserInfoModel userInfo;

	@Override
	public String toString() {
		return "ResponseUserInfoModel [userInfo=" + userInfo + "]";
	}
	
	
}
