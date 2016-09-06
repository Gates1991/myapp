package com.hfkj.redchildsupermarket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 9:16
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserLoginActivity extends Activity {
    @Bind(R.id.imgbtn_left)
    ImageButton mImgbtnLeft;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTvTitleLayout.setText("用户登录");
        mImgbtnLeft.setVisibility(View.VISIBLE);
        mTvTitleLeft.setText("返回");

    }


    @OnClick({R.id.bt_login, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:  //登录
                break;
            case R.id.bt_register://注册
                //点击跳转注册页面
                startActivity(new Intent(UserLoginActivity.this,UserRegisterActivity.class));
                break;
        }
    }
}
