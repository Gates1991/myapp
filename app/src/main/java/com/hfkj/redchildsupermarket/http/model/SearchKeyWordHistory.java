package com.hfkj.redchildsupermarket.http.model;

import android.support.annotation.NonNull;


public class SearchKeyWordHistory {

	public int id;

	@NonNull
	public String keyworkd;
	
	
	public SearchKeyWordHistory(){}
	public SearchKeyWordHistory(String key){this.keyworkd=key;}
}
