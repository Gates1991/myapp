package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.OrderInfoModel;

/**
 * @author Chance
 * 
 * "response": "cart",
    "cart":{
		"cartItemList": [ ]
		
		}

 *
 *购物车返回信息
 */
public class ResponseOrderModel extends BaseResponse {
	public OrderInfoModel orderInfo;
}
