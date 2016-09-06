package com.hfkj.redchildsupermarket.http.model;

/**
 * @author Chance 商品列表中的数据模型
 * 
 * 
 *{
            "id": "1102539",        //商品ID
			"name": "雅培金装",    //商品名称
			"pic": "",              //商品图片URL
			"price": "79",           //会员价
            "limitPrice": "78",       //限时特价
            "leftTime": "3600"       //剩余时间，单位为秒
		}


 * 
 */

public class ProductLimitShopModel {
	public String id;
	public String name;
	public String pic;
	public String price;
	public String limitPrice;
	public int leftTime;
}
