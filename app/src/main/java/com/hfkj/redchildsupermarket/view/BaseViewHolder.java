package com.hfkj.redchildsupermarket.view;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/9 20:25
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/9$
 * @更新描述	${TODO}
 */

import android.util.SparseArray;
import android.view.View;

public class BaseViewHolder {
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
