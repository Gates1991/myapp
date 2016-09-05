package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/5 22:22
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/5$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hfkj.redchildsupermarket.bean.HomeViewPageBean;

import java.util.List;

public class HomePageViewPageAdapter  extends PagerAdapter{
    private List<HomeViewPageBean.HomeTopicBean> pageList;
    private Context                              mContext;

    public HomePageViewPageAdapter(List<HomeViewPageBean.HomeTopicBean> pageList, Context context) {
        this.pageList = pageList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return pageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
