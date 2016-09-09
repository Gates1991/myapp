package com.hfkj.redchildsupermarket.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 1:56
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MyIndentFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {


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

    @Bind(R.id.indent_radio)
    RadioGroup indentRadio;
    @Bind(R.id.fl_indent)
    FrameLayout flIndent;
    @Bind(R.id.rb_SucessIndent)
    RadioButton rbSucessIndent;
    @Bind(R.id.rb_UnsucessIndent)
    RadioButton rbUnsucessIndent;
    @Bind(R.id.rb_CancelIndent)
    RadioButton rbCancelIndent;
    private View mView;
    private UnsucessIndentFragment mUnsucessIndentFragment;
    private SuccessIndentFragment mSuccessIndentFragment;

    private RadioButton previousChecked;
    private CancelIndentFragment mCancelIndentFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myindent, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        ButterKnife.bind(this, mView);
        indentRadio.setOnCheckedChangeListener(this);
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();

    }

    private void initFragment() {
        //完成订单
        mSuccessIndentFragment = new SuccessIndentFragment();
        //未完成订单
        mUnsucessIndentFragment = new UnsucessIndentFragment();
        //取消订单
        mCancelIndentFragment = new CancelIndentFragment();
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_SucessIndent:
                rbSucessIndent.setTextColor(Color.BLACK);
             //   rbSucessIndent.setBackgroundResource(R.drawable.id_segment_selected_1_bg);
                setCheckedTextColor(rbSucessIndent);
                changeFragment(mSuccessIndentFragment);
                break;
            case R.id.rb_UnsucessIndent:
                rbUnsucessIndent.setTextColor(Color.BLACK);
             //   rbUnsucessIndent.setBackgroundResource(R.drawable.id_segment_selected_2_bg);
                setCheckedTextColor(rbUnsucessIndent);
                changeFragment(mUnsucessIndentFragment);
                break;
            case R.id.rb_CancelIndent:
                rbCancelIndent.setTextColor(Color.BLACK);
             //   rbCancelIndent.setBackgroundResource(R.drawable.id_segment_selected_3_bg);
                setCheckedTextColor(rbCancelIndent);
                changeFragment(mCancelIndentFragment);
                break;

        }
    }

    public void changeFragment(BaseFragment frag) {
        FragmentManager supportFragmentManager = mMainActivity.getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.fl_indent, frag);
        transaction.commit();


    }

    private void setCheckedTextColor(RadioButton radioButton) {
        radioButton.setTextColor(Color.WHITE);
        if (previousChecked != null) {
            previousChecked.setTextColor(Color.BLACK);
        }
        previousChecked = radioButton;
    }


/*
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
    }*/
}
