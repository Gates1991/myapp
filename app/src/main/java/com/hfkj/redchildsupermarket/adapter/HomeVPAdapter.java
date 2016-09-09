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

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.bean.HomeVPBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

;

public class HomeVPAdapter extends PagerAdapter{
    private List<HomeVPBean.HomeTopicBean> pageList;
    private Context                        mContext;
   private  List<ImageView> datas;

    public HomeVPAdapter(List<HomeVPBean.HomeTopicBean> pageList, Context context, List<ImageView> datas ) {
        this.pageList = pageList;
        mContext = context;
        this.datas=datas;
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

      /*  ImageView iv = new ImageView(mContext);
        ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
        lp.height = ViewPager.LayoutParams.MATCH_PARENT;
        lp.width = ViewPager.LayoutParams.MATCH_PARENT;
        iv.setLayoutParams(lp);
        container.addView(iv);*/
        ImageView iv = datas.get(position);
        container.addView(iv);
        Glide.with(mContext).load(Constant.BASE_URL+pageList.get(position).getPic()).into(iv);

        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
