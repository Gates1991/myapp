package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.BrandAdapter;
import com.hfkj.redchildsupermarket.bean.BrandBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class BrandFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @Bind(R.id.bt_title_left)
    Button mBtTitleLeft;
    @Bind(R.id.tv_title_name)
    TextView mTvTitleName;
    @Bind(R.id.iv_title_icon)
    ImageView mIvTitleIcon;
    @Bind(R.id.bt_title_right)
    Button mBtTitleRight;
    @Bind(R.id.listview)
    ListView mListview;
    private TextView tvTitle;
    private List<BrandBean.CategoryBean> mCategoryData = new ArrayList<>();
    private BrandAdapter mBrandAdapter;
    private List<BrandBean.CategoryBean> mCategoryBeen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brand, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mTvTitleName.setText("商品分类");

        getNetDatas();

        mListview.setOnItemClickListener(this);

    }

    private void getNetDatas() {
        new Retrofit.Builder().baseUrl(Constant.BaseUrl).addConverterFactory(GsonConverterFactory.create()
        ).build().create(HttpApi.class).getBrandData("0").enqueue(new Callback<BrandBean>() {
            @Override
            public void onResponse(Call<BrandBean> call, Response<BrandBean> response) {
                if (response.isSuccessful()) {
                    BrandBean brandBean = response.body();
                    parseRespomse(brandBean);
                }
            }

            @Override
            public void onFailure(Call<BrandBean> call, Throwable throwable) {
                Toast.makeText(mContext, "访问服务器异常,请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseRespomse(BrandBean brandBean) {
        mCategoryBeen = brandBean.getCategory();
        mCategoryData.clear();
        for (int i = 0; i < mCategoryBeen.size(); i++) {
            if (mCategoryBeen.get(i).getParentId() == 0) {
                mCategoryData.add(mCategoryBeen.get(i));
            }
        }
        if (mBrandAdapter == null) {
            mBrandAdapter = new BrandAdapter(mContext, mCategoryData);
            mListview.setAdapter(mBrandAdapter);
        } else {
            mBrandAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BrandBean.CategoryBean categoryBean = mBrandAdapter.getItem(position);
        int id1 = categoryBean.getId();
        switch (id1) {
            case 1:
                Toast.makeText(mContext, "这是孕妈专区", Toast.LENGTH_SHORT).show();

                mCategoryData.clear();
                for (int i = 0; i <mCategoryBeen.size() ; i++) {
                    if (mCategoryBeen.get(i).getParentId() == 1) {
                        mCategoryData.add(mCategoryBeen.get(i));
                    }
                }
                mBrandAdapter.notifyDataSetChanged();
                break;
            case 2:
                Toast.makeText(mContext, "这是寝具装饰", Toast.LENGTH_SHORT).show();
                mCategoryData.clear();
                for (int i = 0; i <mCategoryBeen.size() ; i++) {
                    if (mCategoryBeen.get(i).getParentId() == 2) {
                        mCategoryData.add(mCategoryBeen.get(i));
                    }
                }
                mBrandAdapter.notifyDataSetChanged();

                break;
            case 3:
                Toast.makeText(mContext, "这是宝宝用品", Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext, "这是寝具装饰", Toast.LENGTH_SHORT).show();
                mCategoryData.clear();
                for (int i = 0; i <mCategoryBeen.size() ; i++) {
                    if (mCategoryBeen.get(i).getParentId() == 3) {
                        mCategoryData.add(mCategoryBeen.get(i));
                    }
                }
                mBrandAdapter.notifyDataSetChanged();
                break;


        }

    }

    private interface HttpApi {
        @FormUrlEncoded
        @POST("category")
        Call<BrandBean> getBrandData(@Field("version") String value);
    }

}
