package com.hfkj.redchildsupermarket.http.model;

import java.util.List;

/**
 * @author Chance 商品列表中的数据模型
 * 
 * 
 * "id": "1102539",           //商品ID
	"name": "雅培金装",       //商品名称
	"marketPrice": "79",        //市场价
      "price": "78",             //会员价
      "limitPrice": "68",          //限时抢购价
      "leftTime": "3600"          //剩余时间，单位为秒
      "pics":[                   //商品图片
		"url",
		"ur2"
			],
	"score": "4.5",            //评分
	"available": true,      //是否可售
	"buyLimit":"购买上限"     //单品购买上限
	"commentCount":"23",   //商品评论数
	"inventoryArea":"可发送至",  //配货说明
	"bigPic":[                //商品大图
   		url1,
   		url2
	], 
 * 
 */

public class ProductModel {
	public String id;
	public String name;
	public String marketPrice;
	public String price;
	public String limitPrice;
	public long leftTime;
	public List<String> pics;
	public float score;
	public boolean available;
	public String buyLimit;
	public String commentCount;
	public String inventoryArea;
	public List<String> bigPic;
}
