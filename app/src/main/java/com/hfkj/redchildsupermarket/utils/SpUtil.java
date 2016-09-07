package com.hfkj.redchildsupermarket.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @Description: sp 工具类
 * @author teacher
 * @date 2016-9-2 上午9:10:58 
 */
public class SpUtil {
	
	private static final String SP_NAME = "config";
	private static SharedPreferences sp;

	//保存boolean值
	public static void putBoolean(Context context,String key,boolean flag){
		if (sp==null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}

		Editor edit = sp.edit();
		edit.putBoolean(key, flag);
		edit.commit();
	}

	//获取布尔值
	public static boolean getBoolean(Context context,String key,boolean defValue){

		if (sp==null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		boolean value = sp.getBoolean(key,defValue);
		return value;
	}


	//保存String
	public static void putString(Context context,String key,String value){

		if (sp==null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}

	//获取String
	public static String getString(Context context,String key){

		if (sp==null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}

		return sp.getString(key, null);
	}


	//保存int值
	public static void putInt(Context context,String key,int value){

		if (sp==null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	//获取int值
	public static int getInt(Context context,String key, int defvalue){
		if (sp==null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}

		return sp.getInt(key, defvalue);
	}
}
