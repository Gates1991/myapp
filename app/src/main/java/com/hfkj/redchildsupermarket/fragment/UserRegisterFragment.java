package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 19:10
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserRegisterFragment extends BaseFragment {

    @Bind(R.id.imgbtn_left)
    ImageButton imgbtnLeft;
    @Bind(R.id.tv_title_left)
    TextView tvTitleLeft;
    @Bind(R.id.tv_title_layout)
    TextView tvTitleLayout;
    @Bind(R.id.register_email)
    EditText registerEmail;
    @Bind(R.id.register_pw)
    EditText registerPw;
    @Bind(R.id.register_pw_confirm)
    EditText registerPwConfirm;
    @Bind(R.id.ib_register_user)
    ImageView ibRegisterUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_userregister, null);
        ButterKnife.bind(this,view);
        TextView mTv_title_layout = (TextView) view.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("用户注册");
        ImageButton mImgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) view.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");

        return view;

    }


    @OnClick({R.id.ib_register_user,R.id.imgbtn_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_register_user://注册按钮
                Toast.makeText(mContext, "注册按钮被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgbtn_left://返回键
                //TODO:退栈返回到更多页面,动画效果
                break;

        }

    }


}
