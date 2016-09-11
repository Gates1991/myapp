package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.HelpDetailBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 栁年 on 2016/9/8.
 */
public class HelpdetailFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.bt_title_left)
    Button mBt_back;
    @Bind(R.id.tv_title_name)
    TextView mTv_title;

    @Bind(R.id.tv_center1)
    TextView mTv_center;

    private View view;
    private int id;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_helpdetai, null);
        ButterKnife.bind(this, view);
        initView();
        initData();

        return view;
    }


    private void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id")+1;
        mTv_title.setText("标题"+id);
        mTv_title.setTextSize(24);

        mBt_back.setVisibility(View.VISIBLE);
        mBt_back.setOnClickListener(this);
    }


    public void initData() {
        new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .getcenter(id).enqueue(new Callback<HelpDetailBean>() {
            @Override
            public void onResponse(Call<HelpDetailBean> call, Response<HelpDetailBean> response) {
                HelpDetailBean Bean = response.body();
                List<HelpDetailBean.HelpDetailListBean> detailListBean = Bean.getHelpDetailList();


                mTv_center.setText(detailListBean.get(0).getTitle()+"\n\n"+detailListBean.get(0).getContent());

                mTv_center.setPadding(30,30,30,30);
                mTv_center.setTextSize(24);


            }

            @Override
            public void onFailure(Call<HelpDetailBean> call, Throwable throwable) {
                Toast.makeText(getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        mMainActivity.popBackStack();
    }

    private interface HttpApi {

        @GET(Constant.URL_HELPDETAIA)
        Call<HelpDetailBean> getcenter(@Query("id") int id);
    }
}
