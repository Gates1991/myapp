package com.hfkj.redchildsupermarket.allpage;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by charry on 2016/9/5.
 */
public class HomePage extends BasePage {
    public HomePage(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("");
        return textView;
    }

    @Override
    public void initData() {

    }
}
