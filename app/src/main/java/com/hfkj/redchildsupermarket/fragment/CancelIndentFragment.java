package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/8 9:52
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CancelIndentFragment extends BaseFragment implements AdapterView.OnItemClickListener {

        private List<IndentBean.OrderListBean> mShowList = new ArrayList<>();

        private String userid;
        private long token;
        private int pageNum;
        private int pageSzie;

        @Bind(R.id.lv_succeed_indent)
        ListView lvSucceedIndent;
        private int type;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
            View view = inflater.inflate(R.layout.fragment_succeedindent, null);
            ButterKnife.bind(this, view);
            lvSucceedIndent.setOnItemClickListener(this);
            getIndentData();

            return view;
        }

        private void getIndentData() {

            //userid 值
            userid = SpUtil.getinfo(mContext, "login_user_id", "");
            //token  值
            long token = SpUtil.getLonginfo(mContext, "login_token", 0);
//            token = Long.parseLong(login_token);
            //pagenum 值
            pageNum = 1;
            //pagesize值
            pageSzie = 10;
            //type
            type = 2;

            new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(HttpApi.class)
                    .orderlist(userid,token,pageNum,pageSzie,type)
                    .enqueue(new Callback<IndentBean>() {
                        @Override
                        public void onResponse(Call<IndentBean> call, Response<IndentBean> response) {
                            if (response.isSuccessful()) {
                                IndentBean indentBean = response.body();
                                System.out.println(indentBean.response);
                                if (TextUtils.equals("error", indentBean.response)) {
                                    Toast.makeText(mContext, "ERROECODE:" + indentBean.error.code + "MSG:" + indentBean.error.msg,
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                } else {

//                                if (indentBean.getOrderList().size() > 0) {
                                    System.out.println("数据到这");
                                    lvSucceedIndent.setAdapter(new IndentAdapter(mContext,indentBean.getOrderList()));
//                                } else {
//
//                                }

                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<IndentBean> call, Throwable throwable) {
                            Toast.makeText(mContext, "获取数据失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        public void initData() {

        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext,"能跳",Toast.LENGTH_SHORT).show();
    }
}
