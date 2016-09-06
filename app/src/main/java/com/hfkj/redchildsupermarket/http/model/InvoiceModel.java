package com.hfkj.redchildsupermarket.http.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Chance
 *
 */

/**
 * @author Chance
 *
 */
public class InvoiceModel implements Parcelable{
	public String type;
	public String title;
	public String content;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "InvoiceModel [type=" + type + ", item_title=" + title + ", content="
				+ content + "]";
	}
	static public Creator<InvoiceModel> CREATOR=new Creator<InvoiceModel>() {

		@Override
		public InvoiceModel createFromParcel(Parcel source) {
			Bundle bundle = source.readBundle();
			InvoiceModel invoiceModel=new InvoiceModel();
			invoiceModel.type=bundle.getString("type");
			invoiceModel.title=bundle.getString("item_title");
			invoiceModel.content=bundle.getString("content");
			
			return invoiceModel;
		}

		@Override
		public InvoiceModel[] newArray(int size) {
			return new InvoiceModel[size];
		}
	};
	
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		Bundle bundle=new Bundle();
		bundle.putString("type", type);
		bundle.putString("item_title", title);
		bundle.putString("content", content);
		
		dest.writeBundle(bundle);
		
	}
	
	
}
