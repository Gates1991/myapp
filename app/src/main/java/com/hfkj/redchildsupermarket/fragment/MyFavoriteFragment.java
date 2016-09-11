package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.FavoriteAdapter;
import com.hfkj.redchildsupermarket.bean.FavoriteBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 2:03
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MyFavoriteFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @Bind(R.id.imgbtn_left)
    ImageButton imgbtnLeft;
    private View mView;
    private String mLogin_user_id;
    private String mLogin_token;
    private ListView mLv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myfavorite, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initTitleView() {

        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("收藏夹");
        mLv = (ListView) mView.findViewById(R.id.lv_favorite);
    }

    @Override
    public void initData() {
        mLogin_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        mLogin_token = String.valueOf(SpUtil.getLonginfo(mContext, "login_token", 0));
        getNetData();
        mLv.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.imgbtn_left)
    public void onClick() {
        mMainActivity.popBackStack();
    }

    public void getNetData() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).getFavoriteData("1","10",mLogin_token,mLogin_user_id).enqueue(new Callback<FavoriteBean>() {
            @Override
            public void onResponse(retrofit2.Call<FavoriteBean> call, Response<FavoriteBean> response) {
                FavoriteBean favoriteBean = response.body();
                List<FavoriteBean.ProductListBean> productList = favoriteBean.getProductList();
                mLv.setAdapter(new FavoriteAdapter(mContext,productList));
            }

            @Override
            public void onFailure(retrofit2.Call<FavoriteBean> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FavoriteBean.ProductListBean bean = (FavoriteBean.ProductListBean) parent.getItemAtPosition(position);
        mMainActivity.addToBackStack(new ShangPingFragment(),bean.getId());
    }

    private interface HttpApi {

        @GET("favorites")
        Call<FavoriteBean> getFavoriteData(@Query("page") String page, @Query("pageNum") String pageNum, @Query
                ("token") String
                token, @Query("userid") String userid);

    }
}
