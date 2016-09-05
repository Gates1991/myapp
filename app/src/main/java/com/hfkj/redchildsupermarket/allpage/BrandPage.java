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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class BrandPage extends BasePage {
    @Bind(R.id.button_back)
    Button mButtonBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.listview)
    ListView mListview;
    private List mListViewData = new ArrayList();
    public BrandPage(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.brandpage_frame, null);

        return view;
    }

    @Override
    public void initData() {

        //mListview.setAdapter();
    }
}
