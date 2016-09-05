package com.hfkj.redchildsupermarket.allpage;/*
 * @创建者  	bubble
 * @创建时间 	2016/8/27 22:32
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/8/27$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;

public abstract class BasePage {

    public Context mContext;
    private View view;

    public BasePage(Context context) {
        this.mContext = context;
        this.view = initView();

    }

    protected abstract View initView();

    public View getRootView(){
        return view;
    }

    public abstract void initData();


    public void onResume() {
    }
}
