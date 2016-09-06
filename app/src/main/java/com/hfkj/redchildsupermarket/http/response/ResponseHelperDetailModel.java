package com.hfkj.redchildsupermarket.http.response;

import com.hfkj.redchildsupermarket.http.model.HelperDetailModel;

import java.util.List;

/**
 * @author Chance 
 * 
 * 
 * 
 *{
"response": "helpDetail",  
"helpDetailList":[
		{
		 "item_title":"怎么购买？",          //item_title
		 "content":" content "		 //帮助内容
		}
]}

 * 
 */
public class ResponseHelperDetailModel extends BaseResponse {
	public List<HelperDetailModel> helpDetailList;
}
