package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 20:03
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.CommentAdapter;
import com.hfkj.redchildsupermarket.bean.BaseResponse;
import com.hfkj.redchildsupermarket.bean.CommentBean;
import com.hfkj.redchildsupermarket.bean.DetailsBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ShangPingFragment extends BaseFragment {

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
    @Bind(R.id.ratingBar1)
    RatingBar mRatingBar1;
    @Bind(R.id.tv_vipPrice)
    TextView mTvVipPrice;
    @Bind(R.id.rl_color)
    RelativeLayout mRlColor;
    @Bind(R.id.tv_location)
    TextView mTvLocation;
    @Bind(R.id.tv_comment)
    TextView mTvComment;
    @Bind(R.id.rl_comment_content)
    RelativeLayout mRlCommentContent;
    @Bind(R.id.lv_comment)
    ListView mLvComment;

    @Bind(R.id.bt_car)
    Button mBtCar;
    @Bind(R.id.bt_now)
    Button mBtNow;
    private int mPid;
    private List<CommentBean.ProductBean> mConmmentData = new ArrayList();
    private int mId;
    private EditText mEdCommodityNum;
    private String mPnum;
    private String mLogin_user_id;
    private String mLogin_token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shangping, null);
        mEdCommodityNum = (EditText) view.findViewById(R.id.ed_commodity_num);
        Bundle bundle = getArguments();
        mMainActivity.isMainFrament = 3;
        mPid = bundle.getInt("id");
        initData();
        ButterKnife.bind(this, view);
        mBtTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleName.setText("商品详情");

        return view;
    }

    @Override
    public void initData() {
        getNetData();
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
        //商品id
        mId = product.getId();
        float score = product.getScore();
        mRatingBar1.setRating(score);


        mLogin_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        mLogin_token = SpUtil.getinfo(mContext, "login_token", "");
    }

    public void getNetData2() {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).geCommntData("product", "comment", String.valueOf(1), String.valueOf(10),
                String.valueOf(1)).enqueue
                (new Callback<CommentBean>() {
                    @Override
                    public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                        if (response.isSuccessful()) {

                            CommentBean commentBean = response.body();
                            List<CommentBean.ProductBean> product = commentBean.getProduct();
                            mConmmentData.addAll(product);
                            mLvComment.setAdapter(new CommentAdapter(mContext, mConmmentData));
                            setListViewHeightBasedOnChildren(mLvComment);

                        } else {
                            System.out.println(response.errorBody());
                        }

                    }

                    @Override
                    public void onFailure(Call<CommentBean> call, Throwable throwable) {

                    }
                });
    }

    //布局设置
    private void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null)
            return;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rl_color, R.id.bt_car, R.id.bt_now,R.id.bt_title_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_color:
                break;
            case R.id.bt_car:
                mPnum = mEdCommodityNum.getText().toString().trim();
                if (TextUtils.isEmpty(mLogin_user_id) || TextUtils.isEmpty(mLogin_token)) {
                    mMainActivity.addToBackStack(new UserLoginFrament());
                } else {
                    postData2Server(mLogin_token, mLogin_user_id, Integer.valueOf(mPnum), mId, 0);
                    showDialog();
                }
                break;
            case R.id.bt_now:
                mPnum = mEdCommodityNum.getText().toString().trim();
                postData2Server(mLogin_token, mLogin_user_id, Integer.valueOf(mPnum), mId, 0);
                mMainActivity.addToBackStack(new CarFragment());
                break;
            case R.id.bt_title_left:
                mMainActivity.popBackStack2();
                break;
        }
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("商品加入购物车成功,是否去购物车结算").setPositiveButton("去购物车", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "去购物车", Toast.LENGTH_SHORT).show();
                mMainActivity.addToBackStack(new CarFragment());
            }
        })
                .setNegativeButton("继续购物", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(mContext, "继续购物", Toast.LENGTH_SHORT).show();

                    }
                }).show();
    }

    private interface HttpApi {

        @GET("product")
        Call<DetailsBean> getDetailsData(@Query("pId") int cId);

        @GET("{product}/{comment}")
            //@GET("comment")
        Call<CommentBean> geCommntData(@Path("product") String product, @Path("comment") String comment, @Query
                ("page") String page, @Query("pageNum") String pageNum, @Query("pId") String pId);

        @FormUrlEncoded
        @POST(Constant.URL_CART_ADD)
        Call<BaseResponse> editShoppingNum(@Field("token") String token,
                                           @Field("userid") String userid,
                                           @Field("pnum") int pnum,
                                           @Field("pid") int pid,
                                           @Field("cid") int cid);

    }

    private void postData2Server(String s, String s1, int num, int pid, int id) {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class)
                .editShoppingNum(s, s1, num, pid, id).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Toast.makeText(mContext, "加入购物车成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable throwable) {
                Toast.makeText(mContext, "加入购物车失败,请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
