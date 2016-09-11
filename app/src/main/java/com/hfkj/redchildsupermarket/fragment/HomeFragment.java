package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.MyGridAdapter;
import com.hfkj.redchildsupermarket.bean.HomeVPBean;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;
import com.hfkj.redchildsupermarket.view.CustomScorollView;
import com.hfkj.redchildsupermarket.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.rl)
    public RelativeLayout rl;//放viewpage的布局

    /* @Bind(R.id.lv_home)
     public ListView mLv;*/
    @Bind(R.id.rg)
    RadioGroup  mRg;
    @Bind(R.id.rb_1)
    RadioButton mRb1;
    @Bind(R.id.rb_2)
    RadioButton mRb2;
    @Bind(R.id.rb_3)
    RadioButton mRb3;
    @Bind(R.id.rb_4)
    RadioButton mRb4;
    @Bind(R.id.rb_5)
    RadioButton mRb5;
    @Bind(R.id.rb_6)
    RadioButton mRb6;
    private HomeViewPage mViewPage;//viewpage页面

    public List<HomeVPBean.HomeTopicBean> mHomeTopicBeen = new ArrayList<>();

    private CustomScorollView mCustomScorollView;
    private List<String> infoList = new ArrayList<>();
    private MyGridView mGridview;
    private String     mLogin_user_id;
    private Long       mLogin_token;
    private View       mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);

        mGridview = (MyGridView) mView.findViewById(R.id.gridview);
        mMainActivity.isMainFrament=1;
        mCustomScorollView = (CustomScorollView) mView.findViewById(R.id.csv);
        ButterKnife.bind(this, mView);
        mRg.setOnCheckedChangeListener(this);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        TextView tv_title_name = (TextView) mView.findViewById(R.id.tv_title_name);
        tv_title_name.setText("红孩子商城");
    }

    /**
     * 初始化数据
     */

    public void initData() {
        infoList.add("欢迎领导光临红孩子商城");
        infoList.add("喜迎国庆欢乐大酬宾");
        infoList.add("更多活动敬请关注红孩子商城");
        mCustomScorollView.setList(infoList);
        mCustomScorollView.startScroll();
        mGridview.setAdapter(new MyGridAdapter(mContext));
        getNetData();
        if (mGridview != null) {
            mGridview.setOnItemClickListener(this);
        }
    }


    private void getNetData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpRetrofit httpRetrofit = retrofit.create(HttpRetrofit.class);
        Call<HomeVPBean> call = httpRetrofit.getHomeData();
        call.enqueue(new Callback<HomeVPBean>() {
            @Override
            public void onResponse(Call<HomeVPBean> call, Response<HomeVPBean> response) {
                if (response.isSuccessful()) {
                    HomeVPBean homeData = response.body();
                    parseNetData(homeData);
                }
            }

            @Override
            public void onFailure(Call<HomeVPBean> call, Throwable throwable) {
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 解析网络数据
     *
     * @param homeData
     */
    private void parseNetData(HomeVPBean homeData) {
        if (mHomeTopicBeen != null) {
            mHomeTopicBeen.clear();
        }
        mHomeTopicBeen = homeData.getHomeTopic();
        if (mViewPage != null) {
            mViewPage.stop();
        }
        mViewPage = new HomeViewPage(mHomeTopicBeen, mContext);
        mViewPage.start();
        if (rl != null) {
            rl.removeAllViews();
            rl.addView(mViewPage);
        }


        mViewPage.setOnViewPageChange(new HomeViewPage.onViewPageChangeListener() {
            @Override
            public void onViewPageChange(int position) {

                switch (position) {
                    case 0:
                        mRg.check(R.id.rb_1);
                        break;
                    case 1:
                        mRg.check(R.id.rb_2);
                        break;
                    case 2:
                        mRg.check(R.id.rb_3);
                        break;
                    case 3:
                        mRg.check(R.id.rb_4);
                        break;
                    case 4:
                        mRg.check(R.id.rb_5);
                        break;
                    case 5:
                        mRg.check(R.id.rb_6);
                        break;
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LimitShoping();
                mMainActivity.addToBackStack(fragment);
                break;
            case 1:
                fragment = new SalesFragment();
                mMainActivity.addToBackStack(fragment);
                break;
            case 2:
                fragment = new DetailsFragment();
                mMainActivity.addToBackStack(fragment, 100);
                break;
            case 3:
                fragment = new DetailsFragment();
                mMainActivity.addToBackStack(fragment, 200);
                break;
            case 4:
                fragment = new recommendBrandFragment();
                mMainActivity.addToBackStack(fragment);
                break;
            case 5:
                mLogin_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
                mLogin_token = SpUtil.getLonginfo(mContext, "login_token", 0);
                if (TextUtils.isEmpty(mLogin_user_id) || mLogin_token == 0) {
                    mMainActivity.addToBackStack(new UserLoginFrament());
                } else {
                    Toast.makeText(mContext, "已经登录了", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_1:
                mViewPage.setCurrentItem(0);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                }, 3000);
                break;
            case R.id.rb_2:
                mViewPage.setCurrentItem(1);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                }, 3000);
                break;
            case R.id.rb_3:
                mViewPage.setCurrentItem(2, false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                }, 3000);
                break;
            case R.id.rb_4:
                mViewPage.setCurrentItem(3, false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                }, 3000);
                break;
            case R.id.rb_5:
                mViewPage.setCurrentItem(4, false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                }, 3000);
                break;
            case R.id.rb_6:
                mViewPage.setCurrentItem(5, false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                }, 3000);
                break;
        }
    }

    /**
     * retrofit框架的接口
     */
    private interface HttpRetrofit {
        @GET("home")
        Call<HomeVPBean> getHomeData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewPage.stop();
        mCustomScorollView.stopScroll();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
