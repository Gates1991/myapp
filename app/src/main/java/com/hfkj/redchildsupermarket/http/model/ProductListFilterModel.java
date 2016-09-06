package com.hfkj.redchildsupermarket.http.model;

import java.util.List;

/**
 * 
 * @author Chance
 * 
 *  {
	"key":"品牌",     //属性名称
	"valueList":[      //属性值列表
	{ "id":"s1",     //标识
	  "name":"雅培" //属性名称
	},
	{ "id":"s2",
	  "name":"雅士利"
	}
	]
		},

 * 
 */

public class ProductListFilterModel {
	public String key;
	public List<FilterValue> valueList;
}
