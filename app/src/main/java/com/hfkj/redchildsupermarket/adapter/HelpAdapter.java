package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.bean.HelpCenterBean;

import java.util.List;

/**
 * Created by 栁年 on 2016/9/8.
 */
public class HelpAdapter extends CommonAdapter<HelpCenterBean.HelpListBean> {
    public HelpAdapter(Context context, List<HelpCenterBean.HelpListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = new TextView(mContext);
        }
        HelpCenterBean.HelpListBean helpListBean = getItem(position);

        ((TextView)convertView).setText(helpListBean.getTitle());
        ((TextView)convertView).setTextSize(24);
//        Drawable dwb = get
//        ((TextView)convertView).setCompoundDrawables(null,null, R.drawable.arrow,null);
        return convertView;
    }
}
