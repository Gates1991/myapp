package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 1:56
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MyIndentFragment extends BaseFragment {


    @Bind(R.id.imgbtn_left)
    ImageButton imgbtnLeft;
    @Bind(R.id.tv_title_left)
    TextView tvTitleLeft;
    @Bind(R.id.tv_title_layout)
    TextView tvTitleLayout;
    @Bind(R.id.btn_right)
    ImageButton btnRight;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tv_SucessIndent)
    TextView tvSucessIndent;
    @Bind(R.id.tv_UnsucessIndent)
    TextView tvUnsucessIndent;
    @Bind(R.id.tv_CancelIndent)
    TextView tvCancelIndent;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myindent, null);
        mMainActivity.isMainFrament = false;
        initTitleView();
        ButterKnife.bind(this, mView);
        return mView;
    }

    private void initTitleView() {

        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("订单列表");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_SucessIndent, R.id.tv_UnsucessIndent, R.id.tv_CancelIndent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_SucessIndent:  // 已完成订单
               // Toast.makeText(mContext,"可以点",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_UnsucessIndent: //未完成订单
                break;
            case R.id.tv_CancelIndent: //取消订单
                break;
        }
    }
}
