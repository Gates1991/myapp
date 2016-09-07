package com.hfkj.redchildsupermarket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreFragment extends BaseFragment {

    @Bind(R.id.imgbtn_left)
    ImageButton mImgbtnLeft;

    @Bind(R.id.btn_right)
    ImageButton mBtnRight;
    @Bind(R.id.user_center)
    RelativeLayout mUserCenter;
    @Bind(R.id.browse_record)
    RelativeLayout mBrowseRecord;
    @Bind(R.id.help_center)
    RelativeLayout mHelpCenter;
    @Bind(R.id.user_feedback)
    RelativeLayout mUserFeedback;
    @Bind(R.id.about)
    RelativeLayout mAbout;
    @Bind(R.id.ib_tel)
    ImageButton mIbTel;
    private TextView mTvTitle;
    private Context mContext;

   // private FragmentManager supportFragmentManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, null);
        //标题textview
        TextView mTvTitleLayout = (TextView) view.findViewById(R.id.tv_title_layout);
        mTvTitleLayout.setText("更多");

        initData();
        mContext = getActivity();


        ButterKnife.bind(this, view);


        return view;
    }
    //创建集合,将所有fragment存入到集合中
    List<BaseFragment> mFragments = new ArrayList<>();

    public void initData() {
        mFragments.add(new UserLoginFrament()); //账户中心
        mFragments.add(new BrowseRecordFragment());//浏览记录
        mFragments.add(new HelpCenterFragment()); //帮助中心
        mFragments.add(new UserFeedbackFragment());//用户反馈
        mFragments.add(new AboutFragment()); //帮助


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.user_center, R.id.browse_record, R.id.help_center, R.id.user_feedback, R.id.about, R.id.ib_tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_center://用户中心
                //TODO:将跳转页面添加到栈中,并设置动画

                //判断是否登录,默认是没有登录,()
                Toast.makeText(mContext,"用户中心被点击了",Toast.LENGTH_SHORT).show();
            //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //

                //先获取token中判断是否登录,已登录跳转到用户信息界面,未登录跳转到登录界面
                islogin();
/*
                addToBackStack(mFragments.get(0));
                //TODO "这边有bug
                RadioGroup mMain_radio = (RadioGroup) ((MainActivity) mContext).findViewById(R.id.main_radio);
                mMain_radio.setVisibility(View.INVISIBLE);*/
                break;
            case R.id.browse_record://浏览记录
                break;
            case R.id.help_center://帮助中心
                break;
            case R.id.user_feedback://用户反馈
                break;
            case R.id.about://关于
                break;
            case R.id.ib_tel://电话
                break;
        }
    }

    private void islogin() {
        //拿sp中的login时的token值
        String login_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        String login_token = SpUtil.getinfo(mContext, "login_token", "");
        if (login_user_id.isEmpty() || login_token.isEmpty()) {
            //跳转到登录界面
            addToBackStack(new UserLoginFrament());
        } else {
            //跳转到用户信息界面
            addToBackStack(new UserInformationFragment());
        }


    }

    /**
     * 添加Fragment到回退栈,并且添加动画
     *
     * @param fragment
     */

    public void addToBackStack(Fragment fragment){
        FragmentManager supportFragmentManager=((MainActivity)mContext).getSupportFragmentManager();
        FragmentTransaction transaction=supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.fragment_slide_right_in,R.anim.fragment_slide_left_out,
                R.anim.fragment_slide_left_in,R.anim.fragment_slide_right_out
        );
        transaction.replace(R.id.fl_content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
