package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.ShopCarModel;

import java.util.List;

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
public class ResponseShopCarModel extends BaseResponse {
	public List<ShopCarModel> cart;

	@Override
	public String toString() {
		return "ResponseShopCarModel{" +
				"cart=" + cart +
				'}';
	}
}
