package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 20:30
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.RecommendExpandAdapter;
import com.hfkj.redchildsupermarket.bean.RecommandExpandBean;
import com.hfkj.redchildsupermarket.http.HttpApi;
import com.hfkj.redchildsupermarket.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class recommendBrandFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.elv_recommend)//扩张的listview
    ExpandableListView mElvRecommend;
    private List<RecommandExpandBean.BrandBean> mBrand=new ArrayList<>();
    private List<RecommandExpandBean.BrandBean.ValueBean> mValueBeen=new ArrayList<>();
    private RecommendExpandAdapter mRecommendExpandAdapter;
    TextView tv_title_name;

    Button   bt_title_left;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendbrand, null);
            initData();
        tv_title_name = (TextView) view.findViewById(R.id.tv_title_name);
        bt_title_left = (Button) view.findViewById(R.id.bt_title_left);
        bt_title_left.setOnClickListener(this);
        mMainActivity.isMainFrament = false;
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_name.setText("促销快报");
        ButterKnife.bind(this, view);

        return view;
    }

    public void initData() {
        getNetData();

    }

    private void getNetData() {
        HttpApi httpApi = RetrofitUtil.createHttpApiInstance();
        Call<RecommandExpandBean> call = httpApi.getRecommandExpand();
        call.enqueue(new Callback<RecommandExpandBean>() {
            @Override
            public void onResponse(Call<RecommandExpandBean> call, Response<RecommandExpandBean> response) {
                if (response.isSuccessful()) {
                    RecommandExpandBean recommandExpandBean = response.body();
                        parseNetData(recommandExpandBean);
                }
            }

            @Override
            public void onFailure(Call<RecommandExpandBean> call, Throwable throwable) {
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void parseNetData(RecommandExpandBean recommandExpandBean) {
        mBrand.clear();
        mValueBeen.clear();
        mBrand = recommandExpandBean.getBrand();
        for (int i = 0; i < mBrand.size(); i++) {
            mValueBeen = mBrand.get(i).value;
        }
        mRecommendExpandAdapter = new RecommendExpandAdapter(mContext, mBrand, mValueBeen);
        mElvRecommend.setAdapter(mRecommendExpandAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onClick(View v) {
        mMainActivity.popBackStack();
    }

}
