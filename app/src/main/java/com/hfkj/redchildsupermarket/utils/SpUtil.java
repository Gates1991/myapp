package com.hfkj.redchildsupermarket.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/7 3:18
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SpUtil {

    private static final String SP_NAME = "config";
    private static SharedPreferences mSp;

    //保存布尔值
    public static void saveBoolean(Context context, String key, boolean value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putBoolean(key, value).commit();
    }


    //获取布尔值
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        boolean result = mSp.getBoolean(key, defValue);
        return result;
    }


    //保存信息数据
    public static void saveinfo(Context context, String key, String value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putString(key, value).commit();
    }


    //获取信息数据
    public static String getinfo(Context context, String key, String defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        String result = mSp.getString(key, defValue);
        return result;
    }

    public static void clearData(Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = mSp.edit();
        edit.clear();
        edit.commit();
    }

    //保存int值
    public static void putInt(Context context,String key,int value){

        if (mSp==null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = mSp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    //获取int值
    public static int getInt(Context context,String key, int defvalue){
        if (mSp==null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }

        return mSp.getInt(key, defvalue);
    }




    //保存信息数据
    public static void saveIntinfo(Context context, String key, int value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putInt(key, value).commit();
    }


    //获取信息数据
    public static int getIntinfo(Context context, String key, int defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        int result = mSp.getInt(key, defValue);
        return result;
    }



    //保存信息数据
    public static void saveLonginfo(Context context, String key, long value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putLong(key, value).commit();
    }


    //获取信息数据
    public static long getLonginfo(Context context, String key, long defValue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        long result = mSp.getLong(key, defValue);
        return result;
    }

}
