package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.BrandBean;

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


public class BrandFragment extends BaseFragment {

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

    private String BaseUrl = "http://10.0.2.2:8080/market/";

    private List<BrandBean.CategoryBean> mCategoryData = new ArrayList<>();
    private BrandAdapter mBrandAdapter;

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

        new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()
        ).build().create(HttpApi.class).getBrandData("0").enqueue(new Callback<BrandBean>() {
            @Override
            public void onResponse(Call<BrandBean> call, Response<BrandBean> response) {
                if (response.isSuccessful()) {
                    BrandBean brandBean = response.body();
                    List<BrandBean.CategoryBean> categoryBeen = brandBean.getCategory();
                    mCategoryData.clear();
                    for (int i = 0; i < categoryBeen.size(); i++) {
                        if (categoryBeen.get(i).getParentId() == 0) {
                            mCategoryData.add(categoryBeen.get(i));
                        }
                    }

                    mBrandAdapter = new BrandAdapter(mCategoryData);
                    mListview.setAdapter(mBrandAdapter);
                }
            }

            @Override
            public void onFailure(Call<BrandBean> call, Throwable throwable) {
                Toast.makeText(mContext, "访问服务器异常,请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });

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

    private interface HttpApi {
        @FormUrlEncoded
        @POST("category")
        Call<BrandBean> getBrandData(@Field("version") String value);
    }

    private class BrandAdapter extends BaseAdapter {
        public BrandAdapter(List<BrandBean.CategoryBean> categoryData) {
        }

        @Override
        public int getCount() {
            return mCategoryData.size();
        }

        @Override
        public BrandBean.CategoryBean getItem(int position) {
            return mCategoryData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_brand, null);
                holder.mItemIcon = (ImageView) convertView.findViewById(R.id.item_icon);
                holder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.mTvTag = (TextView) convertView.findViewById(R.id.tv_tag);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String uri = getItem(position).getPic();

            Glide.with(mContext).load(BaseUrl + uri).into(holder.mItemIcon);
            holder.mTvName.setText(getItem(position).getName());
            holder.mTvTag.setText(getItem(position).getTag());
            return convertView;
        }

        private class ViewHolder {
            ImageView mItemIcon;
            TextView mTvName;
            TextView mTvTag;
        }
    }


}
