package com.hfkj.redchildsupermarket.allpage;

import android.content.Context;
import android.view.View;

import com.hfkj.redchildsupermarket.R;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/5 9:21
 * @描述着 ${TODO}  更多页面
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MorePage extends BasePage {

    public MorePage(Context context) {
        super(context);
    }

    @Override
    protected View initView() {

        View view = View.inflate(mContext, R.layout.more_frame, null);
        initTitleBar();
        return view;
    }

    private void initTitleBar() {



    }

    @Override
    public void initData() {

    }
}
