package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.ErrorModel;

/**
 * @author Chance
 * 
 * 响应数据基类，把公共部分抽取出来
 *
 */
public class BaseResponse {
	public String response;
	public ErrorModel error;
}
