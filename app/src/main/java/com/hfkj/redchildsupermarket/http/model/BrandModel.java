package com.hfkj.redchildsupermarket.http.model;


/**
 * @author Chance 
	
 * 
		{
		  "id": "1201"           //品牌编号
		  "name": "ain",         //品牌名称
		  "pic": ""              //品牌图片URL
		},

 * 
 */

public class BrandModel{
	public String id;
	public String name;
	public String pic;
	@Override
	public String toString() {
		return "BrandModel [id=" + id + ", name=" + name + ", pic=" + pic + "]";
	}
	
	
}
