package com.hfkj.redchildsupermarket.adapter;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/8 20:24
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/8$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.CommentBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends CommonAdapter<CommentBean.ProductBean> {
    public CommentAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.layout_comment, null);
            holder.mTextname = (TextView) convertView.findViewById(R.id.textname);
            holder.mTextcomment = (TextView) convertView.findViewById(R.id.textcomment);
            holder.mTexttime = (TextView) convertView.findViewById(R.id.texttime);
            holder.mTextuser = (TextView) convertView.findViewById(R.id.textuser);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CommentBean.ProductBean productBean = getItem(position);
        holder.mTextcomment.setText(productBean.getContent());
        holder.mTextname.setText(productBean.getTitle());
        holder.mTextuser.setText(productBean.getUsername());

            /*long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1=new Date(time);
            String t1=format.format(d1);
            Log.e("msg", t1);
        }*/

        holder.mTexttime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(productBean.getTime())));

        return convertView;
    }

    static class ViewHolder {

        TextView mTextname;

        TextView mTextcomment;

        TextView mTextuser;

        TextView mTexttime;


    }


}
