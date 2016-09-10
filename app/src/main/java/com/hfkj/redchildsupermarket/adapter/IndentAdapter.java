package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.IndentBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 19:29
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 *
 */



public class IndentAdapter extends BaseAdapter {

    private Context mContext;
    private List<IndentBean.OrderListBean> mdatas;

    public IndentAdapter(Context mContext, List<IndentBean.OrderListBean> mdatas) {
        this.mContext = mContext;
        this.mdatas = mdatas;
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public  IndentBean.OrderListBean getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.indent_item, null);
            holder = new ViewHolder();
            holder.tv_intentNum = (TextView) convertView.findViewById(R.id.tv_intentNum);
            holder.tv_intentPrice = (TextView) convertView.findViewById(R.id.tv_intentPrice);
            holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.tv_indentTime = (TextView) convertView.findViewById(R.id.tv_indentTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        IndentBean.OrderListBean orderListBean = getItem(position);

        holder.tv_intentNum.setText("订单编号:"+orderListBean.getOrderid());
        holder.tv_intentPrice.setText("订单总额:"+orderListBean.getPrice());

        int stateValue = orderListBean.getState();//状态值
        //状态
        String state = whatState(stateValue);
        holder.tv_state.setText("状态:"+state);
        long time = orderListBean.getTime();
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Time = sdf.format(d);
        holder.tv_indentTime.setText("时间:"+Time);

        //TODO 设置时间


        return convertView;
    }

    private class ViewHolder{
        TextView tv_intentNum;
        TextView tv_intentPrice;
        TextView tv_state;
        TextView tv_indentTime;



    }
    String state ="";
    private String whatState(int num) {
        switch (num) {
            case 0:
                state = "订单待完成";
                break;
            case 1:
                state = "订单已完成";
                break;
            case 2:
                state = "订单已取消";
                break;

        }
        return  state;

    }
}
