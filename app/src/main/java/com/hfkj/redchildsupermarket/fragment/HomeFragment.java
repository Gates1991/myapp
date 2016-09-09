package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.HomeLVAdapter;
import com.hfkj.redchildsupermarket.bean.HomeVPBean;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.view.CustomScorollView;

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

    @Bind(R.id.lv_home)
    public ListView mLv;
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

    private List<ItemBean> itemDatas = new ArrayList<>();
    private int[]          itemPic   = {R.mipmap.home_classify_01, R.mipmap.home_classify_02, R.mipmap.home_classify_03,
            R.mipmap.home_classify_04, R.mipmap.home_classify_05};
    private String[]       itemTitel = {"限时抢购", "促销快报", "新品上市", "热门单品", "推荐品牌"};
    private HomeLVAdapter mHomeLVAdapter;
    private CustomScorollView mCustomScorollView;
    private  List<String> infoList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mLv = (ListView) view.findViewById(R.id.lv_home);
        mCustomScorollView = (CustomScorollView) view.findViewById(R.id.csv);

        ButterKnife.bind(this, view);
        mRg.setOnCheckedChangeListener(this);
        initData();
        return view;
    }
    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        infoList.add("欢迎领导光临红孩子商城");
        infoList.add("喜迎国庆欢乐大酬宾");
        infoList.add("更多活动敬请关注红孩子商城");
         mCustomScorollView.setList(infoList);
        mCustomScorollView.startScroll();

        initListView();
        getNetData();
        if (mLv != null) {
            mLv.setOnItemClickListener(this);
        }
    }

    private void initListView() {
        if (itemDatas != null && itemDatas.size() == 0) {
            for (int i = 0; i < itemPic.length; i++) {
                itemDatas.add(new ItemBean(itemTitel[i], itemPic[i]));
            }
        }
        mHomeLVAdapter = new HomeLVAdapter(mContext, itemDatas);
        mLv.setAdapter(mHomeLVAdapter);
        mHomeLVAdapter.notifyDataSetChanged();

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
                },3000);
                break;
            case R.id.rb_2:
                mViewPage.setCurrentItem(1);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                },3000);
                break;
            case R.id.rb_3:
                mViewPage.setCurrentItem(2,false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                },3000);
                break;
            case R.id.rb_4:
                mViewPage.setCurrentItem(3,false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                },3000);
                break;
            case R.id.rb_5:
                mViewPage.setCurrentItem(4,false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                },3000);
                break;
            case R.id.rb_6:
                mViewPage.setCurrentItem(6,false);
                mViewPage.mHandler.removeCallbacksAndMessages(null);
                mViewPage.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewPage.start();
                    }
                },3000);
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


    public class ItemBean {
        public String itemTitle;
        public int    icon;

        public ItemBean(String title, int id) {
            this.itemTitle = title;
            this.icon = id;
        }
    }
}
