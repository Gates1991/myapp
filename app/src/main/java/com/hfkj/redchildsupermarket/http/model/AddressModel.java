package com.hfkj.redchildsupermarket.http.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Chance
 *
 *"id": "1001",                     //地址簿ID
      "name": "肖文",                //收货人姓名
      "phoneNumber": "15801477821",   //手机号码
      "province": "福建省",             //省ID
      "city": "厦门市",               //市ID
      "addressArea": "吉祥区",             //地区ID
      "addressDetail": "闵庄路3号",       //订单地址
      "zipCode": "100195",             //邮编
       “isDefault”：true

 */
public class AddressModel implements Parcelable {
	public String id;
	public String name;
	public String phoneNumber;
	public String province;
	public String city;
	public String addressArea;
	public String addressDetail;
	public String zipCode;
	public boolean isDefault;
	
	
	
	
	
	
	public AddressModel() {
		super();
	}
	static public Creator<AddressModel> CREATOR=new Creator<AddressModel>() {
		
		@Override
		public AddressModel[] newArray(int size) {
			return new AddressModel[size];
		}
		
		@Override
		public AddressModel createFromParcel(Parcel source) {
			AddressModel address=new AddressModel(source);
			return address;
		}
	};
	

	public AddressModel(Parcel parcel) {
		Bundle bundle = parcel.readBundle();
		
		this.id = bundle.getString("id");
		this.name = bundle.getString("name");
		this.phoneNumber = bundle.getString("phoneNumber");
		this.province = bundle.getString("province");
		this.city = bundle.getString("city");
		this.addressArea = bundle.getString("addressArea");
		this.addressDetail = bundle.getString("addressDetail");
		this.zipCode = bundle.getString("zipCode");
		this.isDefault = bundle.getBoolean("isDefault");
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
//		可以用map集合,扩展性更好
//		Map<String,Object> inVal=new HashMap<String,Object>();
//		inVal.put("id", id);
//		inVal.put("name", name);
//		inVal.put("phoneNumber", phoneNumber);
//		inVal.put("province", province);
//		inVal.put("city", city);
//		inVal.put("addressArea", addressArea);
//		inVal.put("addressDetail", addressDetail);
//		inVal.put("zipCode", zipCode);
//		inVal.put("isDefault", isDefault);
//		
//		dest.writeMap(inVal);
//		
		//可以单独把字段写入
//		dest.writeString(val);
		//可以数组的形式写入
//		dest.writeStringArray(val);
		
		Bundle val=new Bundle();
		val.putString("id", id);
		val.putString("name", name);
		val.putString("phoneNumber", phoneNumber);
		val.putString("province", province);
		val.putString("city", city);
		val.putString("addressArea", addressArea);
		val.putString("addressDetail", addressDetail);
		val.putString("zipCode", zipCode);
		val.putBoolean("isDefault", isDefault);
		
		dest.writeBundle(val);
		
	}
}
