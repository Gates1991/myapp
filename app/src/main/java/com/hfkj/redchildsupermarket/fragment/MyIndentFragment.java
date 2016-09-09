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
import butterknife.OnClick;

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
   /* private String userid;
    private long token;
    private int pageNum;
    private int pageSzie;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myindent, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        ButterKnife.bind(this, mView);
        indentRadio.setOnCheckedChangeListener(this);

        //对接接口拿数据
        // getSpValue();
        //设默认选中项
        rbSucessIndent.setChecked(true);
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
//
//        android.app.FragmentManager fragmentManager = mMainActivity.getFragmentManager();
//        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        //拿sp中的当前登录token,userid

       /* //userid 值
        userid = SpUtil.getinfo(mContext, "login_user_id", "");
        //token  值
        String login_token =  SpUtil.getinfo(mContext, "login_token", "");
        token = Long.parseLong(login_token);
        //pagenum 值
        pageNum = 1;
        //pagesize值
        pageSzie = 10;*/

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


    @OnClick(R.id.imgbtn_left)
    public void onClick() {
        mMainActivity.popBackStack();
    }


}
