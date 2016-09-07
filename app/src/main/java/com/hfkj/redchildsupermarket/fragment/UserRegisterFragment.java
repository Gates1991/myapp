package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private FragmentManager supportFragmentManager;
    private View mView;
    private String mEmailData;
    private String mPwData;
    private String mPwDataConfirm;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_userregister, null);
        ButterKnife.bind(this, mView);
        initViewTitle();
        getFgmanager();

        //拿到填写信息
        initRegisterInfo();
        return mView;

    }

    private void initRegisterInfo() {
        mEmailData = registerEmail.getText().toString().trim();
        mPwData = registerPw.getText().toString().trim();
        mPwDataConfirm = registerPwConfirm.getText().toString().trim();


    }

    private void initViewTitle() {
        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("用户注册");
        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");

    }

    private void getFgmanager() {

        supportFragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
    }


    @OnClick({R.id.ib_register_user,R.id.imgbtn_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_register_user://注册按钮
                if (mEmailData.isEmpty() || mPwData.isEmpty() || mPwDataConfirm.isEmpty()) {
                    //邮箱或者密码不能为空
                    Toast.makeText(mContext, "Email或者密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!mPwData.equals(mPwDataConfirm)) {
                    //密码不一致
                    Toast.makeText(mContext, "您两次输入密码不一致", Toast.LENGTH_SHORT).show();

                } else if (!isEmail(mEmailData)) {
                    //邮箱格式不对
                    Toast.makeText(mContext, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    //信息填写正确,将邮箱,密码发送到数据库中获取json数据
                    sendToSql();

                }

                break;
            case R.id.imgbtn_left://返回键
                //TODO:退栈返回到更多页面,动画效果
                clearBackStack();
                break;

        }

    }

   // @POST("register")
  //  Call<registerResponse> register(@Field(mEmailData) String username,@Field(mPwData) String password);
    private void sendToSql() {


    }

    //邮箱格式判断
    private boolean isEmail(String strEmail) {

        String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();

    }


    @Override
    public void initData() {

    }

    /**
     * 清空栈
     */
    public void clearBackStack(){
        supportFragmentManager.popBackStack(null,1);//参数为0，清除栈顶的Fragment，参数为1，清空栈
    }



}
