package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.bean.LoginBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.http.HttpApi;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 17:19
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserLoginFrament extends BaseFragment {


    @Bind(R.id.imgbtn_left)
    ImageButton mBtnLeft;
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
    private FragmentManager supportFragmentManager;
    private String mEmailValue;
    private String mPasswordValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        //注册页面退栈返回,email回显
        String emailVlaue = SpUtil.getinfo(mContext, "register_Email", null);
        mEmail.setText(emailVlaue);

        super.onResume();
    }

    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_userlogin, null);
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
    @OnClick({R.id.bt_login, R.id.bt_register,R.id.imgbtn_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:  //登录
                //TODO:进行判断,email和密码获取,联网与服务器中的数据匹配,正确存在则进入
           //
                mEmailValue = mEmail.getText().toString().trim();
                mPasswordValue = mPassword.getText().toString().trim();
                if (mEmailValue.isEmpty() || mPasswordValue.isEmpty()) {
                    Toast.makeText(mContext, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //链接服务器
                    getServerJson();
                }



                break;
            case R.id.bt_register://注册
                //点击跳转注册页面

                addToBackStack(new UserRegisterFragment());
                break;
            case R.id.imgbtn_left://返回按钮
                // 退栈,添加动画效果
                mMainActivity.popBackStack();
                break;
        }
    }

    private void getServerJson() {
        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class).login(mEmailValue,mPasswordValue)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        if (response.isSuccessful()) {
                            LoginBean loginBean = response.body();
                            System.out.println(loginBean);
                            processLoginBean(loginBean);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable throwable) {

                    }
                });



    }
    //处理loginBean
    private void processLoginBean(LoginBean loginBean) {
        LoginBean.UserInfoBean userInfo = loginBean.getUserInfo();
        if (TextUtils.equals("error", loginBean.response)) {
            Toast.makeText(mContext, "ERRORCODE:" + loginBean.error.code + "MSG:" + loginBean.error.msg, Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(userInfo.toString());

            SpUtil.saveinfo(mContext,"login_user_id",userInfo.getUser_id());
            SpUtil.saveinfo(mContext,"login_token", String.valueOf(userInfo.getToken()));
            Toast.makeText(mContext, "LOGIN", Toast.LENGTH_SHORT).show();
            addToBackStack(new UserInformationFragment());
        }


    }


    /**
     * 添加Fragment到回退栈,并且添加动画
     *
     * @param fragment
     */

    public void addToBackStack(Fragment fragment){
        supportFragmentManager = ((MainActivity)mContext).getSupportFragmentManager();
        FragmentTransaction transaction= supportFragmentManager.beginTransaction();
        /*transaction.setCustomAnimations(
                R.anim.fragment_slide_right_in,R.anim.fragment_slide_left_out,
                R.anim.fragment_slide_left_in,R.anim.fragment_slide_right_out
        );*/
        transaction.replace(R.id.fl_content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




}
