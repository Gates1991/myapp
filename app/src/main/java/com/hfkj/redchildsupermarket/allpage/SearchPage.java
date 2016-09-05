package com.hfkj.redchildsupermarket.allpage;

import android.content.Context;
import android.view.View;

import com.hfkj.redchildsupermarket.R;

/**
 * Created by 栁年 on 2016/9/5.
 */
public class SearchPage extends BasePage{
    public SearchPage(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.searchpage_frame, null);
        return view;
    }

    @Override
    public void initData() {

    }
}
