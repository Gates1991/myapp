package com.hfkj.redchildsupermarket.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hfkj.redchildsupermarket.R;

/**
 * Created by wf on 2016/9/9.
 */
public class ShowAlipayDialog extends Dialog implements View.OnClickListener {

    private  LeaveMyDialogListener listener;
    private Context context;
    private Button mIv_pay;
    private Button mIv_cancel;

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public interface LeaveMyDialogListener{
        public void onClick(View view);
    }

    public ShowAlipayDialog(Context context) {
        super(context);
        this.context = context;
    }

    public ShowAlipayDialog(Context context, int themeResId,LeaveMyDialogListener listener) {
        super(context, themeResId);
        this.listener = listener;
        this.context = context;
    }

    /*protected ShowAlipayDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_pay_way);
        mIv_pay = (Button) findViewById(R.id.iv_pay);
        mIv_cancel = (Button) findViewById(R.id.iv_cancel);
        mIv_pay.setOnClickListener(this);
        mIv_cancel.setOnClickListener(this);
    }
}
