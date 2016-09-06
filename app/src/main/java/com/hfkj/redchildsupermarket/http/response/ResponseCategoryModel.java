package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.CategoryModel;

import java.util.List;

/**
 * @author Chance
 * 
 *         首页数据实体获取
 */
public class ResponseCategoryModel extends BaseResponse {
	public String version;
	public List<CategoryModel> category;
}
