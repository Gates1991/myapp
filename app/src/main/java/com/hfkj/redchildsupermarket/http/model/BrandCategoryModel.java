package com.hfkj.redchildsupermarket.http.model;

import java.util.List;


/**
 * @author Chance 
	促销海报
 * 
 		"key":"孕妈专区",           //分区名称
	 	"value":
	  [
		{
		  "id": "1201"           //品牌编号
		  "name": "ain",         //品牌名称
		  "pic": ""              //品牌图片URL
		},
 * 
 */

public class BrandCategoryModel {
	public String key;
	public List<BrandModel> value;
}
