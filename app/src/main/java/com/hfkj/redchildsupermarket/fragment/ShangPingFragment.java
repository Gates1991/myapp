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
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.CommentAdapter;
import com.hfkj.redchildsupermarket.adapter.shangPingAdapter;
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

public class ShangPingFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.bt_title_left)
    Button mBtTitleLeft;
    @Bind(R.id.tv_title_name)
    TextView mTvTitleName;
    /* @Bind(R.id.iv_icon)
     ImageView mIvIcon;*/
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
    private ViewPager mVp;
    private ScrollView mScroll;
    private TextView mTvNumber;
    private Dialog mDialog;
    private Button mButton;
    private RelativeLayout mRl_sc;
    private TextView mTv_sc;
    private boolean isCollect = false;
    private boolean mLoding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shangping, null);
        ButterKnife.bind(this, view);
        initView(view);
        Bundle bundle = getArguments();
        mMainActivity.isMainFrament = 3;
        mPid = bundle.getInt("id");
        initData();
        return view;
    }

    private void initView(View view) {
        mEdCommodityNum = (EditText) view.findViewById(R.id.ed_commodity_num);
        mVp = (ViewPager) view.findViewById(R.id.vp_icon);
        mScroll = (ScrollView) view.findViewById(R.id.sv_scroll);
        mRl_sc = (RelativeLayout) view.findViewById(R.id.rl_sc);
        mButton = (Button) view.findViewById(R.id.bt_right);
        mTv_sc = (TextView) view.findViewById(R.id.tv_sc);
        mRl_sc.setVisibility(View.VISIBLE);


        mBtTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleName.setText("商品详情");
        mButton.setOnClickListener(this);
    }


    public void initData() {
        getNetData();
        mLoding = isLoding();


    }

    private boolean isLoding() {
        //获取登陆信息
        mLogin_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        mLogin_token = String.valueOf(SpUtil.getLonginfo(mContext, "login_token", 0));
        if (TextUtils.isEmpty(mLogin_user_id) || TextUtils.isEmpty(mLogin_token)) {
            return false;
        } else {
            return true;
        }
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
        // Glide.with(mContext).load(Constant.BASE_URL + bigPic.get(0)).into(mIvIcon);
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
        //商品id;
        mId = product.getId();
        //商品评分
        float score = product.getScore();
        mRatingBar1.setRating(score);


        //
        List<String> pics = product.getPics();
        mVp.setAdapter(new shangPingAdapter(mContext, pics));


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
                            mConmmentData.clear();
                            mConmmentData.addAll(product);
                            mLvComment.setAdapter(new CommentAdapter(mContext, mConmmentData));
                            mScroll.scrollTo(0, 0);
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

    @OnClick({R.id.rl_color, R.id.bt_car, R.id.bt_now, R.id.bt_title_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_color:
                mPnum = mEdCommodityNum.getText().toString().trim();
                showColorDialog(mPnum);
                break;
            case R.id.bt_car:
                mPnum = mEdCommodityNum.getText().toString().trim();
                if (Integer.valueOf(mPnum) < 1 || mPnum.isEmpty()) {
                    Toast.makeText(mContext, "请输入正确的商品数量", Toast.LENGTH_SHORT).show();
                } else {
                    if (!mLoding) {
                        mMainActivity.addToBackStack(new UserLoginFrament());
                    } else {
                        postData2Server(mLogin_token, mLogin_user_id, Integer.valueOf(mPnum), mId, 0);
                        showDialog();
                    }
                }

                break;
            case R.id.bt_now:
                mPnum = mEdCommodityNum.getText().toString().trim();
                if (Integer.valueOf(mPnum) < 1 || mPnum.isEmpty()) {
                    Toast.makeText(mContext, "请输入正确的商品数量", Toast.LENGTH_SHORT).show();
                } else {
                    showColorDialog(mPnum);
                }
                break;
            case R.id.bt_title_left:
                mMainActivity.popBackStack2();
                break;
            case R.id.tv_add:
                int num = Integer.valueOf(mTvNumber.getText().toString()) + 1;
                mTvNumber.setText(num + "");
                break;
            case R.id.tv_minus:
                int num1 = Integer.valueOf(mTvNumber.getText().toString()) - 1;
                if (num1 < 1) {
                    num1 = 1;
                }
                mTvNumber.setText(num1 + "");
                break;
            case R.id.btn_addCars:
                int num3 = Integer.valueOf(mTvNumber.getText().toString());
                if (!mLoding) {
                    mMainActivity.addToBackStack(new UserLoginFrament());
                } else {
                    postData2Server(mLogin_token, mLogin_user_id, Integer.valueOf(num3), mId, 0);
                    Toast.makeText(mContext, "加入购物车成功", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                    mEdCommodityNum.setText("1");
                }
                break;
            case R.id.bt_right:
                if (!isCollect) {
                    mButton.setBackgroundResource(R.drawable.ysc);
                   // mTv_sc.setText("已收藏");
                    if (mLoding){
                        postCollectData(mId,mLogin_token,mLogin_user_id);
                        showDialog2();
                    }else {
                        mMainActivity.addToBackStack(new UserLoginFrament());
                    }

                    isCollect = true;
                } else {
                    mButton.setBackgroundResource(R.drawable.sc);
                    //mTv_sc.setText("收藏");
                    Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
                    isCollect = false;
                }

                break;
        }
    }

    private void showColorDialog(String pnum) {
        mDialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);

        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        mDialog.setContentView(R.layout.product_detail_more);
        mDialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = 480;
        wlp.alpha = 0.9f;
        window.setWindowAnimations(R.style.mystyle);
        window.setAttributes(wlp);

        mDialog.show();

        mTvNumber = (TextView) mDialog.findViewById(R.id.tv_number);
        mTvNumber.setText(pnum);
        Button bt = (Button) mDialog.findViewById(R.id.btn_addCars);
        TextView tvMinus = (TextView) mDialog.findViewById(R.id.tv_minus);
        TextView tvAdd = (TextView) mDialog.findViewById(R.id.tv_add);
        tvMinus.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        bt.setOnClickListener(this);
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
    private void showDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("商品加入收藏夹成功,是否去收藏夹看看").setPositiveButton("去收藏夹", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                mMainActivity.addToBackStack(new MyFavoriteFragment());
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

        @GET("addfavorites")
        Call<BaseResponse> addCollectData(@Query("pid") int pid, @Query("token") String token, @Query("userid")
        String userid);
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

    private void postCollectData(int pid, String token, String userid) {
        new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(HttpApi.class).addCollectData(pid, token, userid).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                //Toast.makeText(mContext, "加入收藏夹成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable throwable) {

            }
        });
    }

}
