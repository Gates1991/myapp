package com.hfkj.redchildsupermarket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
 * @创建时间 2016/9/6 10:08
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserRegisterActivity extends Activity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_userregister);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitleLayout.setText("用户注册");
        imgbtnLeft.setVisibility(View.VISIBLE);
        tvTitleLeft.setText("返回");


    }



    @OnClick(R.id.ib_register_user)
    public void onClick() {
        Toast.makeText(this, "注册按钮被点击", Toast.LENGTH_SHORT).show();
    }
}
