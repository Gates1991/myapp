package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 20:03
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.CommentAdapter;
import com.hfkj.redchildsupermarket.bean.CommentBean;
import com.hfkj.redchildsupermarket.bean.DetailsBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
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
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ShangPingFragment extends BaseFragment {


    private static final String BASE_URL = "http://10.0.2.2:8080/market/product/";
    @Bind(R.id.bt_title_left)
    Button mBtTitleLeft;
    @Bind(R.id.tv_title_name)
    TextView mTvTitleName;
    @Bind(R.id.iv_icon)
    ImageView mIvIcon;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_marketPrice)
    TextView mTvMarketPrice;
    @Bind(R.id.tv_vipPrice)
    TextView mTvVipPrice;
    @Bind(R.id.ed_commodity_num)
    EditText mEdCommodityNum;
    @Bind(R.id.tv_location)
    TextView mTvLocation;
    @Bind(R.id.tv_comment)
    TextView mTvComment;
    @Bind(R.id.rl_comment_content)
    RelativeLayout mRlCommentContent;
    @Bind(R.id.lv_comment)
    ListView mLvComment;
    @Bind(R.id.iv_car)
    ImageView mIvCar;
    @Bind(R.id.iv_star)
    ImageView mIvStar;
    @Bind(R.id.bt_car)
    Button mBtCar;
    @Bind(R.id.bt_now)
    Button mBtNow;
    private int mPid;
    private List<CommentBean.ProductBean> mConmmentData = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shangping, null);

        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mMainActivity.isMainFrament = false;
        initView();
        mPid = bundle.getInt("id");
        initData();

        return view;
    }

    private void initView() {
        mBtTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleName.setText("商品详情");

    }

    @Override
    public void initData() {
        getNetData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getNetData() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).getDetailsData(mPid).enqueue(new Callback<DetailsBean>() {
            @Override
            public void onResponse(Call<DetailsBean> call, Response<DetailsBean> response) {
                DetailsBean detailsBean = response.body();
                parseRespomse(detailsBean);
                getNetData2();
            }

            @Override
            public void onFailure(Call<DetailsBean> call, Throwable throwable) {

            }
        });


    }

    private void parseRespomse(DetailsBean detailsBean) {
        DetailsBean.ProductBean product = detailsBean.getProduct();
        //大图
        List<String> bigPic = product.getBigPic();
        Glide.with(mContext).load(Constant.BASE_URL + bigPic.get(0)).into(mIvIcon);
        //商品名
        String name = product.getName();
        mTvName.setText(name);
        //市场价
        int marketPrice = product.getMarketPrice();
        mTvMarketPrice.setText("¥" + marketPrice);
        mTvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //会员价
        int limitPrice = product.getLimitPrice();
        mTvVipPrice.setText("¥" + limitPrice);
        //地址
        String inventoryArea = product.getInventoryArea();
        mTvLocation.setText(inventoryArea);
        //评论数量
        int commentCount = product.getCommentCount();
        mTvComment.setText("(" + commentCount + "人评论)");
        mEdCommodityNum.setText("1");

    }

    @OnClick({R.id.bt_title_left, R.id.rl_comment_content, R.id.iv_car, R.id.iv_star, R.id.bt_car, R.id.bt_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_title_left:
                mMainActivity.popBackStack();
                break;
            case R.id.rl_comment_content:
                break;
            case R.id.iv_car:
                mMainActivity.addToBackStack(new CarFragment());
                break;
            case R.id.iv_star:
                break;
            case R.id.bt_car:
                break;
            case R.id.bt_now:
                break;
        }
    }

    public void getNetData2() {
           new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).geCommntData(String.valueOf(1),String.valueOf(10),String.valueOf(1)).enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                if (response.isSuccessful()) {

                    CommentBean commentBean = response.body();
                    List<CommentBean.ProductBean> product = commentBean.getProduct();
                    mConmmentData.addAll(product);
                    mLvComment.setAdapter(new CommentAdapter(mContext, mConmmentData));
                    setListViewHeightBasedOnChildren(mLvComment);

                }else{
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable throwable) {

            }
        });
    }
    private void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private interface HttpApi {

        @GET("product")
        Call<DetailsBean> getDetailsData(@Query("pId") int cId);

        //@GET("{product}/{comment}")
        @GET("comment")
        Call<CommentBean> geCommntData( @Query("page") String page, @Query("pageNum") String
                pageNum,@Query("pId") String pId);
    }
}
