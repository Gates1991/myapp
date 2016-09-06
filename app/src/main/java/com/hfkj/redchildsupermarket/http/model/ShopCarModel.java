package com.hfkj.redchildsupermarket.http.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author Chance
 * 
 *         { "id": 23, "pid": 1, "pnum": 2, "ppid": 0, "productImageUrl":
 *         "/images/topic/product/1.jpg", "productName": "灰色孕妇服",
 *         "productPrice": "50", "productPropertyName": "null:null", "state": 1,
 *         "user_id": "14599337416420" },
 */
public class ShopCarModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id;
	public String pid;
	public String pnum;
	public String ppid;
	public String productImageUrl;
	public String productName;
	public String productPrice;
	public String productPropertyName;
	public String state;
	public String user_id;
	public boolean isSelect;

	@Override
	public String toString() {
		return "ShopCarModel [id=" + id + ", pid=" + pid + ", pnum=" + pnum
				+ ", ppid=" + ppid + ", productImageUrl=" + productImageUrl
				+ ", productName=" + productName + ", productPrice="
				+ productPrice + ", productPropertyName=" + productPropertyName
				+ ", state=" + state + ", user_id=" + user_id + "]";
	}

	static public Parcelable.Creator<ShopCarModel> CREATOR = new Parcelable.Creator<ShopCarModel>() {

		@Override
		public ShopCarModel createFromParcel(Parcel source) {
			ShopCarModel carModel = new ShopCarModel();
			Bundle bundle = source.readBundle();
			carModel.id = bundle.getString("id");
			carModel.pid = bundle.getString("pid");
			carModel.pnum = bundle.getString("pnum");
			carModel.ppid = bundle.getString("ppid");
			carModel.productImageUrl = bundle.getString("productImageUrl");
			carModel.productName = bundle.getString("productName");
			carModel.productPrice = bundle.getString("productPrice");
			carModel.productPropertyName = bundle
					.getString("productPropertyName");
			carModel.state = bundle.getString("state");
			carModel.user_id = bundle.getString("user_id");

			return carModel;
		}

		@Override
		public ShopCarModel[] newArray(int size) {

			return new ShopCarModel[size];
		}
	};

}
