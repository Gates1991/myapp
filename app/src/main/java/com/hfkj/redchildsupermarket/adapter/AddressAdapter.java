package com.hfkj.redchildsupermarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.MyAddressBean;

import java.util.List;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/9 9:19
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddressAdapter extends CommonAdapter {
    public AddressAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.address_item, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_ad_name);
            holder.tv_num = (TextView) convertView.findViewById(R.id.tv_ad_num);
            holder.tv_addr = (TextView) convertView.findViewById(R.id.tv_ad_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MyAddressBean.AddressListBean addressListBean = (MyAddressBean.AddressListBean) getItem(position);
        holder.tv_name.setText(addressListBean.getName());
        holder.tv_num.setText(addressListBean.getPhoneNumber());
        holder.tv_addr.setText(addressListBean.getProvince()+","+addressListBean.getCity()+","+addressListBean.getAddressArea());

        return convertView;
    }
    private class ViewHolder{
        TextView tv_name;
        TextView tv_num;
        TextView tv_addr;


    }

}
