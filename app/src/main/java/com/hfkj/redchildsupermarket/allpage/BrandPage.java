package com.hfkj.redchildsupermarket.allpage;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/5 9:19
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/5$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;

import com.hfkj.redchildsupermarket.R;

public class BrandPage extends BasePage {
    public BrandPage(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext,R.layout.brandpage_frame,null);

        return view;
    }

    @Override
    public void initData() {

    }
}
