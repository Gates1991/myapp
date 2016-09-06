package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 17:19
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserLoginFrament extends BaseFragment {



    @Bind(R.id.tv_title_left)
    TextView mTvTitleLeft;
    @Bind(R.id.tv_title_layout)
    TextView mTvTitleLayout;
    @Bind(R.id.btn_right)
    ImageButton mBtnRight;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Bind(R.id.email)
    EditText mEmail;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.bt_login)
    Button mBtLogin;
    @Bind(R.id.bt_register)
    Button mBtRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_user_login, null);
        //标题textview
        TextView mTvTitleLayout = (TextView) view.findViewById(R.id.tv_title_layout);
        mTvTitleLayout.setText("用户登录");
        ImageButton mImgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) view.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");


        mContext = getActivity();


        ButterKnife.bind(this, view);


        return view;
    }
    @OnClick({R.id.bt_login, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:  //登录
                break;
            case R.id.bt_register://注册
                //点击跳转注册页面

                addToBackStack(new UserRegisterFragment());
                break;
            case R.id.imgbtn_left://返回按钮
                // 退栈,添加动画效果
                //TODO :
                break;
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
        /*transaction.setCustomAnimations(
                R.anim.fragment_slide_right_in,R.anim.fragment_slide_left_out,
                R.anim.fragment_slide_left_in,R.anim.fragment_slide_right_out
        );*/
        transaction.replace(R.id.fl_content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
