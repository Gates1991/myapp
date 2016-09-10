package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/9 20:26
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/9$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.view.BaseViewHolder;

public class MyGridAdapter extends BaseAdapter{
    private int[]          imgs    = {R.mipmap.home_classify_01, R.mipmap.home_classify_02, R.mipmap.home_classify_03,
            R.mipmap.home_classify_04, R.mipmap.home_classify_05,R.drawable.index2};
    private String[]       img_text = {"限时抢购", "促销快报", "新品上市", "热门单品", "推荐品牌","登录"};
    private Context mContext;
    public MyGridAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.grid_item, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(imgs[position]);

        tv.setText(img_text[position]);
        return convertView;
    }

}
