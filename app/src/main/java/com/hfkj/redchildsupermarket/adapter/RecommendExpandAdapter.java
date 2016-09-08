package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 20:45
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.RecommandExpandBean.BrandBean;
import com.hfkj.redchildsupermarket.bean.RecommandExpandBean.BrandBean.ValueBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

public class RecommendExpandAdapter extends BaseExpandableListAdapter {

    private  Context mContext;
    private  List<BrandBean> mData;
    private  List<ValueBean> mChildData;
    public RecommendExpandAdapter(Context context, List<BrandBean> data, List<ValueBean> childData){
        this.mContext=context;
        this.mData=data;
        this.mChildData=childData;
    }
    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildData.size();
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView=View.inflate(mContext, R.layout.item_elv_recommend, null);
        }
        TextView tv_elv_recommend = (TextView) convertView.findViewById(R.id.tv_elv_recommend);
        BrandBean brandBean = mData.get(groupPosition);
          tv_elv_recommend.setText(brandBean.getKey());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView=View.inflate(mContext, R.layout.item_lv_recommend, null);
        }
        TextView tv_item_recommandbrand = (TextView) convertView.findViewById(R.id.tv_item_recommandbrand);
        ImageView iv_item_recommandbrand = (ImageView) convertView.findViewById(R.id.iv_item_recommandbrand);
        ValueBean valueBean = mChildData.get(childPosition);
        tv_item_recommandbrand.setText(valueBean.getName());
        Glide.with(mContext).load(Constant.BASE_URL+valueBean.getPic()).into(iv_item_recommandbrand);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
