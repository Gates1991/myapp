package com.hfkj.redchildsupermarket.fragment;

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
import com.hfkj.redchildsupermarket.adapter.HelpAdapter;
import com.hfkj.redchildsupermarket.bean.HelpCenterBean;
import com.hfkj.redchildsupermarket.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 18:26
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HelpCenterFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private View mView;
    private List<HelpCenterBean.HelpListBean> helpList = new ArrayList<>();
    private ListView mLvhelp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        mView = inflater.inflate(R.layout.fragment_helpcenter, null);
        mMainActivity.isMainFrament = 2;
        inintTitleView();
        initData();
        return mView;
    }

    private void inintTitleView() {

        Button mbtn_left = (Button) mView.findViewById(R.id.bt_title_left);
        mbtn_left.setVisibility(View.VISIBLE);

        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_name);
        mTv_title_layout.setText("帮助中心");

        mLvhelp = (ListView) mView.findViewById(R.id.lv_help);
        mbtn_left.setOnClickListener(this);

    }

    public void initData() {
        new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .gethelp(0).enqueue(new Callback<HelpCenterBean>() {
            @Override
            public void onResponse(Call<HelpCenterBean> call, Response<HelpCenterBean> response) {
                HelpCenterBean helpCenterBean = response.body();
                parseRespomse(helpCenterBean);
                System.out.println(helpCenterBean.toString());
            }

            @Override
            public void onFailure(Call<HelpCenterBean> call, Throwable throwable) {
                Toast.makeText(mContext, "网络异常,请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseRespomse(HelpCenterBean helpCenterBean) {
        helpList.clear();

        helpList = helpCenterBean.getHelpList();


        mLvhelp.setAdapter(new HelpAdapter(mContext,helpList));
        mLvhelp.setOnItemClickListener(this);
    }



    @Override
    public void onClick(View view) {
        mMainActivity.popBackStack();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//       String title = helpList.get(position).getTitle();
        mMainActivity.addToBackStack(new HelpdetailFragment(),position);
    }

    private interface HttpApi {
        @FormUrlEncoded
        @POST(Constant.URL_HELP)
        Call<HelpCenterBean> gethelp(@Field("version") int version);

    }
}
