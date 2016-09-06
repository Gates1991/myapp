package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.ProductListFilterModel;
import com.hfkj.redchildsupermarket.http.model.ProductListModel;

import java.util.List;

/**
 * @author Chance
 * 
 * 首页数据实体获取
 */
public class ResponseProductListModel extends BaseResponse {
	public String version;
	public List<ProductListModel> productList;
	public List<ProductListFilterModel> listFilter;
}
