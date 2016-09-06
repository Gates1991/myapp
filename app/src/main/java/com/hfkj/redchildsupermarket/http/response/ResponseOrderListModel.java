package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.OrderInfoModel;

import java.util.List;

/**
 * @author Chance
 * 
 * orderList
 *
 *购物车返回信息
 */
public class ResponseOrderListModel extends BaseResponse {
	public List<OrderInfoModel> orderList;
}
