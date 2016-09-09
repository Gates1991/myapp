package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 16:12
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.SalesAdapter;
import com.hfkj.redchildsupermarket.bean.SalesBean;
import com.hfkj.redchildsupermarket.http.HttpApi;
import com.hfkj.redchildsupermarket.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    private ListView mLv;
    private List<SalesBean.TopicBean> mTopicBeen = new ArrayList<>();
    private SalesAdapter mSalesAdapter;

    TextView tv_title_name;

    Button   bt_title_left;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_sales, null);
        mLv = (ListView) view.findViewById(R.id.lv_sales);
         tv_title_name = (TextView) view.findViewById(R.id.tv_title_name);
        bt_title_left = (Button) view.findViewById(R.id.bt_title_left);
        bt_title_left.setOnClickListener(this);
        mMainActivity.isMainFrament = false;
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_name.setText("促销快报");
        initData();
        mLv.setOnItemClickListener(this);
        return view;
    }


    public void initData() {
        getNetData();

    }


    private void getNetData() {
        HttpApi httpApi = RetrofitUtil.createHttpApiInstance();
        Call<SalesBean> call = httpApi.getSalesData("1", "2");
        call.enqueue(new Callback<SalesBean>() {
            @Override
            public void onResponse(Call<SalesBean> call, Response<SalesBean> response) {
                SalesBean saleBean = response.body();
                pasreNetData(saleBean);
            }

            @Override
            public void onFailure(Call<SalesBean> call, Throwable throwable) {
                Toast.makeText(mContext, "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pasreNetData(SalesBean saleBean) {
        mTopicBeen.clear();
        mTopicBeen = saleBean.getTopic();
        mSalesAdapter = new SalesAdapter(mContext, mTopicBeen);
        mLv.setAdapter(mSalesAdapter);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailsFragment detailsFragment = new DetailsFragment();
                mMainActivity.addToBackStack(detailsFragment,300);


    }
}
