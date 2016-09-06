package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.ProductLimitShopModel;

import java.util.List;

/**
 * @author Chance 
 * 
 * 限时抢购
 */
public class ResponseLimitProductModel extends BaseResponse {
	public List<ProductLimitShopModel> productList;
}
