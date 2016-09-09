package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.view.ShowAlipayDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wf on 2016/9/9.
 */
public class PayFragment extends BaseFragment {
    @Bind(R.id.iv_order_all)
    ImageView mIvOrderAll;
    @Bind(R.id.iv_by_alipay)
    ImageView mIvByAlipay;
    private ShowAlipayDialog mDialog;

    @Nullable

    public static PayFragment newInstance() {
        return new PayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, null);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_order_all, R.id.iv_by_alipay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_order_all:
                break;
            case R.id.iv_by_alipay:
                //弹出dialog
                //跳到订单详情界面
                if (mDialog == null) {
                    mDialog = new ShowAlipayDialog(mContext, R.style.dialog, new ShowAlipayDialog.LeaveMyDialogListener() {
                        @Override
                        public void onClick(View view) {
                            switch (view.getId()) {
                                case R.id.iv_pay:
                                    //跳到订单详情界面
                                    ((MainActivity)mContext).addToBackStack(new MyIndentFragment());
                                    mDialog.dismiss();
                                    break;
                                case R.id.iv_cancel:
                                    mDialog.dismiss();
                                    break;
                            }
                        }
                    });
                }
                mDialog.show();
                break;
        }
    }

}
