package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.SearchRecommandResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 18:26
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HelpCenterFragment extends BaseFragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        mView = inflater.inflate(R.layout.fragment_helpcenter, null);
        mMainActivity.isMainFrament = false;
        inintTitleView();
        return mView;
    }

    private void inintTitleView() {

        ImageButton mImgbtn_left = (ImageButton) mView.findViewById(R.id.imgbtn_left);
        mImgbtn_left.setVisibility(View.VISIBLE);
        TextView mTv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        mTv_title_left.setText("返回");
        TextView mTv_title_layout = (TextView) mView.findViewById(R.id.tv_title_layout);
        mTv_title_layout.setText("帮助中心");
    }

    public void initData() {

    }

    private interface HttpApi {

        @POST("help")
        Call<SearchRecommandResponse> getSearchRecommend(@Path("version") String version);// search/recommend
        //如果url中含有斜线，那么不能把带斜线的值传入（斜线会变乱码）

        //        //POST 请求PSOT参数
        //        @FormUrlEncoded  //进行表单url编码
        //        @POST("search")
        //        Call<SearchGoodsBean> search(@Field("page") int page, @Field("pageNum") int pageNum, @Field("orderby") String orderby, @Field("keyword") String keyword);

    }
}
