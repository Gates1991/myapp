package com.hfkj.redchildsupermarket.http.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Chance
 * 
 *         { "orderInfo":{ "id": "1112111111", "state":"", "price":"230",
 *         "paymentType":"1", "time”:1400000, “addressid”:1, “deliveryType”:2,
 *         “invoiceTitle”:"发票抬头" “invoiceType”:0, “invoiceContent”:”明细”,
 *         “couponid”：1, } }
 * 
 * 
 */
public class OrderInfoModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	public String orderid;
	public String state;
	public String price;
	public String paymentType;
	public String time;
	public String addressid;
	public String deliveryType;
	public String invoiceTitle;
	public String invoiceType;
	public String invoiceContent;
	public String couponid;

	public ArrayList<ShopCarModel> carts;

	@Override
	public String toString() {
		return "OrderInfoModel [id=" + orderid + ", state=" + state
				+ ", price=" + price + ", paymentType=" + paymentType
				+ ", time=" + time + ", addressid=" + addressid
				+ ", deliveryType=" + deliveryType + ", invoiceTitle="
				+ invoiceTitle + ", invoiceType=" + invoiceType
				+ ", invoiceContent=" + invoiceContent + ", couponid="
				+ couponid + ", carts=" + carts + "]";
	}

}
