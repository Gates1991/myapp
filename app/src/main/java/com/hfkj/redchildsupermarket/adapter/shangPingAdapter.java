package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/10 9:16
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/10$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

public class shangPingAdapter extends PagerAdapter {
    private final Context mContext;
    private final List<String> mPics;

    public shangPingAdapter(Context context, List<String> pics) {

        this.mContext = context;
        this.mPics = pics;
    }

    @Override
    public int getCount() {
        return mPics.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = new ImageView(mContext);
        String url = mPics.get(position);

        Glide.with(mContext).load(Constant.BASE_URL + url).into(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
