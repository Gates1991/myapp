package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.HelperModel;

import java.util.List;

/**
 * @author Chance 
 * 
 * 
 * 
 * {
  "response": "help",
  "version":"12",
  "helpList": [
		{
		 "id":"1",
		 "item_title":"购物指南",          //item_title
		},
		
		{
		 "id":"2",
		 "item_title":"配送方式",  //item_title
		}
	]
}

 * 
 */
public class ResponseHelperModel extends BaseResponse {
	public String version;
	public List<HelperModel> helpList;
}
