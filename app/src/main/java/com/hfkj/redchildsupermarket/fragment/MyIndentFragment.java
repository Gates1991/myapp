package com.hfkj.redchildsupermarket.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.adapter.IndentAdapter;
import com.hfkj.redchildsupermarket.bean.IndentBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.http.HttpApi;
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

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 1:56
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MyIndentFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, AdapterView
        .OnItemClickListener {


    @Bind(R.id.imgbtn_left)
    ImageButton imgbtnLeft;
    @Bind(R.id.tv_title_left)
    TextView tvTitleLeft;
    @Bind(R.id.tv_title_layout)
    TextView tvTitleLayout;
    @Bind(R.id.btn_right)
    ImageButton btnRight;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;

    @Bind(R.id.indent_radio)
    RadioGroup indentRadio;

    @Bind(R.id.rb_SucessIndent)
    RadioButton rbSucessIndent;
    @Bind(R.id.rb_UnsucessIndent)
    RadioButton rbUnsucessIndent;
    @Bind(R.id.rb_CancelIndent)
    RadioButton rbCancelIndent;
    private View mView;
    private RadioButton previousChecked;
    private ImageButton mMImgbtn_left;
    private int mType =1;
    private String userid;
    private long mToken;
    private int pageNum;
    private int pageSzie;
    private List<IndentBean.OrderListBean> mOrderList = new ArrayList<>();
    private ListView mListView;
    private IndentAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myindent, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        ButterKnife.bind(this, mView);
        mListView = (ListView) mView.findViewById(R.id.listview);
        //userid 值
        userid = SpUtil.getinfo(mContext, "login_user_id", "");
        //token  值
        mToken = SpUtil.getLonginfo(mContext, "login_token", 0);
        //pagenum 值
        pageNum = 1;
        //pagesize值
        pageSzie = 10;


        indentRadio.setOnCheckedChangeListener(this);
        rbSucessIndent.setChecked(true);
        mListView.setOnItemClickListener(this);
        return mView;
    }





    private void initTitleView() {

        mMImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mMImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("订单列表");
    }


    public void initData() {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        switch (checkedId) {
            case R.id.rb_SucessIndent:
                rbSucessIndent.setTextColor(Color.BLACK);
                //这是已完成订单 type= 1
                setCheckedTextColor(rbSucessIndent);
                mType = 1;
               getNetData(mType);
                break;
            case R.id.rb_UnsucessIndent:

                rbUnsucessIndent.setTextColor(Color.BLACK);
                //   rbUnsucessIndent.setBackgroundResource(R.drawable.id_segment_selected_2_bg);
                setCheckedTextColor(rbUnsucessIndent);
                mType = 0;
                getNetData(mType);

                break;
            case R.id.rb_CancelIndent:
                //这是已   取消订单 type= 2
                rbCancelIndent.setTextColor(Color.BLACK);
                setCheckedTextColor(rbCancelIndent);
                mType = 2;
                getNetData(mType);
                break;

        }
    }




    private void setCheckedTextColor(RadioButton radioButton) {
        radioButton.setTextColor(Color.WHITE);
        if (previousChecked != null) {
            previousChecked.setTextColor(Color.BLACK);
        }
        previousChecked = radioButton;
    }


    @OnClick(R.id.imgbtn_left)
    public void onClick() {
        mMainActivity.popBackStack();
    }


    private void getNetData(int type) {


        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .orderlist(userid, mToken, pageNum, pageSzie,type)
                .enqueue(new Callback<IndentBean>() {
                    @Override
                    public void onResponse(Call<IndentBean> call, Response<IndentBean> response) {
                        if (response.isSuccessful()) {
                            IndentBean indentBean = response.body();
                            parseNetData(indentBean);
                        }
                    }
                    @Override
                    public void onFailure(Call<IndentBean> call, Throwable throwable) {
                        Toast.makeText(mContext, "获取数据失败",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void parseNetData(IndentBean indentBean) {
        List<IndentBean.OrderListBean> orderList = indentBean.getOrderList();
        mOrderList.clear();
        mOrderList.addAll(orderList);
        if (mAdapter == null){
            mAdapter = new IndentAdapter(mContext, mOrderList);
        }else {
            mAdapter.notifyDataSetChanged();
        }

        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IndentBean.OrderListBean orderListBean= (IndentBean.OrderListBean) parent.getItemAtPosition(parent.getCount()-1-position);
        System.out.println("orderListBean.getCarts().size()"+orderListBean.getCarts().size());
        UnfinishOrderDetailFragement unfinishOrderDetailFragement = new UnfinishOrderDetailFragement();
        mMainActivity.addToBackStack(unfinishOrderDetailFragement);
        unfinishOrderDetailFragement.setitemBean(orderListBean);

    }
}
