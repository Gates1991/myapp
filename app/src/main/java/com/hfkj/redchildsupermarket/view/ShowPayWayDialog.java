package com.hfkj.redchildsupermarket.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import java.util.ArrayList;

/**
 * Created by wf on 2016/9/8.
 */
public class ShowPayWayDialog extends Dialog implements AdapterView.OnItemClickListener {

    private  ArrayList<String> list;
    private ListView mLv_dialog;
    private OnItemSelectedItem mOnItemSelectedItem;


    public ShowPayWayDialog(Context context,ArrayList<String> list) {
        super(context, R.style.locationStyle);
        this.list = list;
        //在dialog显示之前修改它的布局参数
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        window.setAttributes(attributes);
    }

    public ShowPayWayDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ShowPayWayDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialag_pay);
        mLv_dialog = (ListView) findViewById(R.id.lv_dialog);
        mLv_dialog.setAdapter(new DialogAdapate());
        mLv_dialog.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //当点击事件发生时,传出position
        mOnItemSelectedItem.onItemSelectItem(position);

    }

    private class DialogAdapate extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getContext(),R.layout.dialog_item_pay,null);
            TextView tv_payway = (TextView) convertView.findViewById(R.id.tv_payway);
            tv_payway.setText(list.get(position));
            tv_payway.setTextColor(Color.BLACK);
            return convertView;
        }
    }
    //接口回调
    public interface OnItemSelectedItem {
        public void onItemSelectItem(int position);
    }
    public void setOnItemSelectItem (OnItemSelectedItem onSelectedItem) {
        this.mOnItemSelectedItem = onSelectedItem;
    }
}
