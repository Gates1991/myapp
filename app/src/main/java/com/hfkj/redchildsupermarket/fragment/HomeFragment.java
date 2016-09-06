package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.HomeVPBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class HomeFragment extends BaseFragment {

    @Bind(R.id.rl)
    public RelativeLayout rl;//放viewpage的布局
    @Bind(R.id.ll_point)
    public LinearLayout   ll_point;//放小圆点的布局
    @Bind(R.id.bt_search)
    Button   mBtSearch;
    @Bind(R.id.et_search)
    EditText mEtSearch;
    @Bind(R.id.lv)
    ListView mLv;
    private HomeViewPage mViewPage;//viewpage页面
    private List<ImageView> dots = new ArrayList<>();//小圆点对象的数据集合

    public List<HomeVPBean.HomeTopicBean> mHomeTopicBeen = new ArrayList<>();
    private HomeLVAdapter mHomeLVAdapter;

    private List<ItemBean>  itemDatas     = new ArrayList<>();
    private int[]    itemPic   = {R.mipmap.home_classify_01, R.mipmap.home_classify_02, R.mipmap.home_classify_03,
            R.mipmap.home_classify_04, R.mipmap.home_classify_05};
    private String[]     itemTitel = {"限时抢购", "促销快报", "新品上市", "热门单品", "推荐品牌"};

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initDots(mHomeTopicBeen.size());
            if (mViewPage == null) {
                mViewPage = new HomeViewPage(mHomeTopicBeen, dots, mContext);
            }
            mViewPage.start();
            if (rl != null) {
                rl.removeAllViews();
                rl.addView(mViewPage);
            }

        }
    };

    /**
     * 动态创建小圆点
     *
     * @param size 小圆点的个数
     */
    private void initDots(int size) {
        dots.clear();
        if (ll_point != null) {
            ll_point.removeAllViews();
        }
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(mContext);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(15, 15);
            if (i == 0) {
                iv.setEnabled(true);
                iv.setImageResource(R.drawable.selector_point);
            } else {
                iv.setEnabled(false);
                iv.setImageResource(R.drawable.selector_point);
                params.leftMargin = 6;
            }
            iv.setLayoutParams(params);
            dots.add(iv);
            if (ll_point != null) {
                ll_point.addView(iv);
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    /**
     * 初始化数据
     */
    private void init() {
         initListView();
        getNetData();
    }

    private void initListView() {
        if (itemDatas != null && itemDatas.size() == 0) {
            for (int i = 0; i <itemPic.length; i++) {
                itemDatas.add(new ItemBean(itemTitel[i],itemPic[i]));
            }
        }
        if (mHomeLVAdapter == null) {
            mHomeLVAdapter = new HomeLVAdapter(mContext, itemDatas);
            mLv.setAdapter(mHomeLVAdapter);
        } else {
            mHomeLVAdapter.notifyDataSetChanged();
        }
    }


    private void getNetData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
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
        mHandler.sendEmptyMessage(0);

    }

    @OnClick(R.id.bt_search)
    public void onClick() {

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
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public class ItemBean {
        public String itemTitle;
        public int icon;
        public ItemBean( String title,int id){
            this.itemTitle=title;
            this.icon=id;
        }
    }
}
