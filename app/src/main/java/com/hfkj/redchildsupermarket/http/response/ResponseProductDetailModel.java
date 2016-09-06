package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.ProductColorModel;
import com.hfkj.redchildsupermarket.http.model.ProductModel;

import java.util.List;

/**
 * @author Chance
 * 
 * 首页数据实体获取
 */
public class ResponseProductDetailModel extends BaseResponse {
	public ProductModel product;
	public List<ProductColorModel> productProperty;
}
