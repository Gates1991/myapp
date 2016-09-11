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


public class BrandFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {

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

    private int level = 0;
    private DetailsFragment mDetailsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brand, null);

        ButterKnife.bind(this, view);
        initData();
        mMainActivity.isMainFrament=1;
        mTvTitleName.setText("商品分类");
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initData() {

        getNetDatas();

        mListview.setOnItemClickListener(this);
        mBtTitleLeft.setOnClickListener(this);

    }

    private void getNetDatas() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()
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
        } else {
            mBrandAdapter.notifyDataSetChanged();
        }
        mListview.setAdapter(mBrandAdapter);
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
                // Toast.makeText(mContext, "这是孕妈专区", Toast.LENGTH_SHORT).show();
                refreshData(id1);
                mTvTitleName.setText(categoryBean.getName());
                level = 1;
                break;
            case 2:
                refreshData(id1);
                //Toast.makeText(mContext, "这是寝具服饰", Toast.LENGTH_SHORT).show();
                mTvTitleName.setText(categoryBean.getName());
                level = 1;
                break;
            case 3:
                refreshData(id1);
                // Toast.makeText(mContext, "这是宝宝用品", Toast.LENGTH_SHORT).show();
                mTvTitleName.setText(categoryBean.getName());
                level = 1;
                break;
            case 11:
                refreshData(id1);
                // Toast.makeText(mContext, "这是妈妈个人护理", Toast.LENGTH_SHORT).show();
                level = 2;
                mTvTitleName.setText(categoryBean.getName());
                break;
            case 12:
                refreshData(id1);
                //Toast.makeText(mContext, "这是孕妇服饰", Toast.LENGTH_SHORT).show();
                level = 2;
                mTvTitleName.setText(categoryBean.getName());
                break;
            case 13:
                refreshData(id1);
                // Toast.makeText(mContext, "这是孕妇内衣", Toast.LENGTH_SHORT).show();
                level = 2;
                mTvTitleName.setText(categoryBean.getName());
                break;
            case 21:
                refreshData(id1);
                //  Toast.makeText(mContext, "这是婴幼儿护齿", Toast.LENGTH_SHORT).show();
                level = 3;
                mTvTitleName.setText(categoryBean.getName());
                break;
            case 22:
                // Toast.makeText(mContext, "这是儿童玩具", Toast.LENGTH_SHORT).show();
                refreshData(id1);
                level = 3;
                mTvTitleName.setText(categoryBean.getName());
                break;
            case 31:
                refreshData(id1);
                //Toast.makeText(mContext, "这是女士围裙", Toast.LENGTH_SHORT).show();
                level = 4;
                mTvTitleName.setText(categoryBean.getName());
                break;
            case 32:
                refreshData(id1);
                // Toast.makeText(mContext, "这是男士围裙", Toast.LENGTH_SHORT).show();
                level = 4;
                mTvTitleName.setText(categoryBean.getName());
                break;
            case 111:
                //Toast.makeText(mContext, "洁牙护齿", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
            case 112:
                //Toast.makeText(mContext, "成人尿不湿", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
            case 121:
                //Toast.makeText(mContext, "韩版孕妇服饰", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
            case 131:
                //Toast.makeText(mContext, "欧美风孕妇内衣", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
            case 211:
                //Toast.makeText(mContext, "幼儿牙刷", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
            case 212:
                //Toast.makeText(mContext, "7周岁儿童玩具", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
            case 311:
                //Toast.makeText(mContext, "韩版围裙", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
            case 312:
               // Toast.makeText(mContext, "日版围裙", Toast.LENGTH_SHORT).show();
                skipDetailsFragment(id1);
                break;
        }
    }

    private void skipDetailsFragment(int id1) {

        mDetailsFragment = new DetailsFragment();

        mMainActivity.addToBackStack(mDetailsFragment,id1);
    }

    private void refreshData(int id) {
        mCategoryData.clear();
        for (int i = 0; i < mCategoryBeen.size(); i++) {
            if (mCategoryBeen.get(i).getParentId() == id) {
                mCategoryData.add(mCategoryBeen.get(i));
            }
        }
        mBrandAdapter.notifyDataSetChanged();
        mBtTitleLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        if (level == 1) {
            // Toast.makeText(mContext, "这是1级目录", Toast.LENGTH_SHORT).show();
            refreshData(0);
            mBtTitleLeft.setVisibility(View.GONE);
            mTvTitleName.setText("商品分类");
        } else if (level == 2) {
            //Toast.makeText(mContext, "这是2级目录", Toast.LENGTH_SHORT).show();
            refreshData(1);
            mTvTitleName.setText("孕妈专区");
            level = 1;
        } else if (level == 3) {
            //Toast.makeText(mContext, "这是3级目录", Toast.LENGTH_SHORT).show();
            refreshData(3);
            mTvTitleName.setText("宝宝用品");
            level = 1;
        } else if (level == 4) {
            refreshData(2);
            mTvTitleName.setText("寝具服饰");
            level = 1;
        }

    }


    private interface HttpApi {
        @FormUrlEncoded
        @POST("category")
        Call<BrandBean> getBrandData(@Field("version") String value);
    }

}
