package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.TicketBean;
import com.hfkj.redchildsupermarket.gson.GsonConverterFactory;
import com.hfkj.redchildsupermarket.http.HttpApi;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 2:01
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MyTicketFragment extends BaseFragment {

    @Bind(R.id.imgbtn_left)
    ImageButton imgbtnLeft;
    @Bind(R.id.tv_title_layout)
    TextView tvTitleLayout;
    @Bind(R.id.btn_right)
    ImageButton btnRight;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    private View mView;
    private ListView lv_ticket;
    private TicketBean.CouponBean couponBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myticket, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        ButterKnife.bind(this, mView);
        //获取优惠券接口
      //  getTicket();
        return mView;
    }

    private void getTicket() {
        System.out.println("0000");
        String login_user_id = SpUtil.getinfo(mContext, "login_user_id", "");
        long login_token = SpUtil.getLonginfo(mContext, "login_token", 0);
        int cid = 1;
        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .getTicketData(cid,login_token,login_user_id)
                .enqueue(new Callback<TicketBean>() {
                    @Override
                    public void onResponse(Call<TicketBean> call, Response<TicketBean> response) {
                        System.out.println("9898");
                        if (response.isSuccessful()) {
                            TicketBean ticketBean = response.body();
                             couponBean = ticketBean.getCoupon();
                            System.out.println(couponBean.getName());
                                lv_ticket.setAdapter(new TicketAdapter());

                        } else {
                            Toast.makeText(mContext,"响应失败",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<TicketBean> call, Throwable throwable) {

                    }
                });


    }

    private void initTitleView() {

        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("我的优惠券");
        lv_ticket = (ListView) mView.findViewById(R.id.lv_ticket);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.imgbtn_left)
    public void onClick() {
        mMainActivity.popBackStack();
    }

    public class TicketAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.ticket_item, null);
                holder = new ViewHolder();
                holder.tv_intentNum = (TextView) convertView.findViewById(R.id.tv_intentNum);
                holder.tv_intentPrice = (TextView) convertView.findViewById(R.id.tv_intentPrice);
                holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
                holder.tv_indentTime = (TextView) convertView.findViewById(R.id.tv_indentTime);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //  TicketBean.CouponBean couponBean = getItem(mdatas.size()-1-position);


            holder.tv_intentNum.setText("优惠券类型:"+couponBean.getName());
            holder.tv_intentPrice.setText("满金额:"+couponBean.getFullmoney());


            holder.tv_state.setText("减金额:"+couponBean.getCutmoney());

            holder.tv_indentTime.setText(couponBean.getDesc());
            return convertView;
        }

        private class ViewHolder{
            TextView tv_intentNum;
            TextView tv_intentPrice;
            TextView tv_state;
            TextView tv_indentTime;



        }

    }

}
