package com.hfkj.redchildsupermarket.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.bean.SearchRecommandResponse;
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
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class SearchFragment extends Fragment {

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_title_name)
    TextView mTvTitleName;
    @Bind(R.id.bt_search)
    Button btSearch;
    @Bind(R.id.el_search)
    ExpandableListView elSearch;

    private TextView tvTitle;
    private List<String> mData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, null);

        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mTvTitleName.setText("搜索");


        httpGet();


        elSearch.setAdapter(new SearchAdapter());
    }

    private String httpGet() {
        new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpApi.class)
                .getSearchRecommend("search", "recommend").enqueue(new Callback<SearchRecommandResponse>() {
            @Override
            public void onResponse(Call<SearchRecommandResponse> call, Response<SearchRecommandResponse> response) {

                if (response.isSuccessful()) {
                    SearchRecommandResponse searchRecommandResponse = response.body();
                    parseRespomse(searchRecommandResponse);
                }
            }

            @Override
            public void onFailure(Call<SearchRecommandResponse> call, Throwable throwable) {

            }
        });
        return null;
    }

    private void parseRespomse(SearchRecommandResponse searchRecommandResponse) {
        List<String> searchKeywords = searchRecommandResponse.getSearchKeywords();
        mData.clear();
        for (int i = 0; i < searchKeywords.size(); i++) {
            mData.add(searchKeywords.get(i));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.bt_search)
    public void onClick() {
    }

    class SearchAdapter extends BaseExpandableListAdapter {
        //获取组的数量，ExpandableListView最外层的条目数量
        @Override
        public int getGroupCount() {
            return 1;
        }

        //获取对应组的子条目的数量
        @Override
        public int getChildrenCount(int i) {
            return mData.size();
        }

        //创建组条目的view界面
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                convertView = new TextView(getActivity().getApplicationContext());
            }
            String groupText = "热门搜索";

            TextView tv = (TextView) convertView;
            tv.setText(groupText);
            tv.setTextColor(Color.RED);
            tv.setBackgroundColor(Color.parseColor("#55000000"));
            tv.setPadding(8, 8, 8,8);
            tv.setTextSize(18);
            return tv;
        }

        //创建对应组，对应子条目的view界面
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                convertView = new TextView(getActivity().getApplicationContext());
            }

            TextView tv = (TextView) convertView;
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundColor(Color.WHITE);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(mData.get(0) + "\n" + mData.get(1));
            tv.setTextSize(14);
            return tv;
        }

        //设置子条目是否可以进行点击
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }

        //获取组条目的数据类似于baseadapter里面getitem方法
        @Override
        public Object getGroup(int i) {
            return null;
        }

        //获取哪一个组哪一个条目的数据
        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        //获取对应组的id
        @Override
        public long getGroupId(int i) {
            return 0;
        }

        //获取哪一个组哪一个条目的id
        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        //当前的id是否稳定
        @Override
        public boolean hasStableIds() {
            return false;
        }


    }

    private interface HttpApi {

        @GET("{search}/{recommend}")
        Call<SearchRecommandResponse> getSearchRecommend(@Path("search") String search, @Path("recommend") String recommend);// search/recommend
        //如果url中含有斜线，那么不能把带斜线的值传入（斜线会变乱码）

    }
}
