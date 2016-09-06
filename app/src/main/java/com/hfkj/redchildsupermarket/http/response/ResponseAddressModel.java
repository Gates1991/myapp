package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.AddressModel;

import java.util.List;

/**
 * @author Chance 上传地址响应
 */
public class ResponseAddressModel extends BaseResponse {
	public List<AddressModel> addressList;
	public AddressModel address;
}
