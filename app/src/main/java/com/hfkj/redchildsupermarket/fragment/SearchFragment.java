package com.hfkj.redchildsupermarket.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;
import com.hfkj.redchildsupermarket.bean.SearchRecommandResponse;
import com.hfkj.redchildsupermarket.utils.Constant;
import com.hfkj.redchildsupermarket.utils.SpUtil;

import java.util.ArrayList;
import java.util.LinkedList;
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

public class SearchFragment extends BaseFragment {

    @Bind(R.id.et_search)
    EditText           etSearch;
    @Bind(R.id.tv_title_name)
    TextView           mTvTitleName;
    @Bind(R.id.bt_search)
    Button             btSearch;
    @Bind(R.id.el_search)
    ExpandableListView elSearch;

    private List<String> mData = new ArrayList<>();

    private String[] title = {"热门搜索", "搜索历史"};

    private LinkedList<String> searchOld; //本地缓存的搜索历史数据集合

    private SearchAdapter searchAdapter;

    private int prevousPosition = -1;//记录上一次被点击后打开的组的索引
    private String keywords;
    private String regex = "#####";
    private String sp_keyword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, null);

        ButterKnife.bind(this, view);
        getsearchwords();
        initData();
        return view;
    }

    private void getsearchwords() {
        searchOld = new LinkedList<>();
        sp_keyword = SpUtil.getinfo(mContext, Constant.SEARCH_KEYWORD, "");
        if (TextUtils.isEmpty(sp_keyword)) {

        } else {
            String[] split = sp_keyword.split(regex);
            for (int i = 0; i < split.length; i++) {
                if (!searchOld.contains(split[i])) {

                    searchOld.add(split[i]);
                }
            }
        }
    }

    public void initData() {

        mTvTitleName.setText("搜索");


        httpGet();


        //        elSearch.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
        //            @Override
        //            public boolean onGroupClick(ExpandableListView parent, View v,
        //                                        int groupPosition, long id) {
        //                if (prevousPosition != -1 && prevousPosition != groupPosition) {
        //                    elSearch.collapseGroup(prevousPosition);
        //                }
        //                if (elSearch.isGroupExpanded(groupPosition)) {
        //                    elSearch.collapseGroup(groupPosition);
        //                } else {
        //                    elSearch.expandGroup(groupPosition);
        //                    prevousPosition = groupPosition;
        //                    elSearch.setSelectedGroup(groupPosition);
        //                }
        //                return true;
        //            }
        //        });

        //给子条目设置点击事件
        elSearch.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if (childPosition < mData.size()) {

                    etSearch.setText(mData.get(childPosition));
                } else {

                    etSearch.setText(searchOld.get(childPosition));
                }
                onClick();

                return true;
            }
        });

    }

    //获取热门搜索的数据
    private void httpGet() {
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

    }

    //解析热门搜索的数据
    private void parseRespomse(SearchRecommandResponse searchRecommandResponse) {
        List<String> searchKeywords = searchRecommandResponse.getSearchKeywords();
        mData.clear();
        for (int i = 0; i < searchKeywords.size(); i++) {
            mData.add(searchKeywords.get(i));
        }

        //设置适配器
        searchAdapter = new SearchAdapter();
        elSearch.setAdapter(searchAdapter);

        //遍历所有group,将所有项设置成默认展开
        int groupCount = elSearch.getCount();

        for (int i = 0; i < groupCount; i++) {

            elSearch.expandGroup(i);

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
        keywords = etSearch.getText().toString();
        if (!TextUtils.isEmpty(keywords)) {
            etSearch.setSelection(keywords.length());
            GoodsFragment goodsFragment = new GoodsFragment();
            ((MainActivity) mContext).addToBackStack(goodsFragment, keywords);

            //保存查询关键字记录到sp中
            SpUtil.saveinfo(mContext, Constant.SEARCH_KEYWORD, keywords + regex + sp_keyword);

        } else {
            Toast.makeText(getContext(), "您输入的关键字为空", Toast.LENGTH_SHORT).show();
        }
    }


    class SearchAdapter extends BaseExpandableListAdapter {
        //获取组的数量，ExpandableListView最外层的条目数量
        @Override
        public int getGroupCount() {
            return 2;
        }

        //获取对应组的子条目的数量
        @Override
        public int getChildrenCount(int groupPosition) {
            if (groupPosition == 0) {

                return mData.size();
            } else {
                return searchOld.size();
            }
        }

        //创建组条目的view界面
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {

            convertView = (LinearLayout) View.inflate(getActivity(), R.layout.wowocoupons_parent_item, null);


            TextView parentTextView = (TextView) convertView.findViewById(R.id.parentitem);
            parentTextView.setText(title[groupPosition]);
            ImageView parentImageViw = (ImageView) convertView.findViewById(R.id.arrow);
            //判断isExpanded就可以控制是按下还是关闭，同时更换图片
            if (isExpanded) {
                parentImageViw.setImageResource(R.drawable.arrow_down);
            } else {
                parentImageViw.setImageResource(R.drawable.arrow);
            }

            return convertView;


        }


        //创建对应组对应子条目的view界面
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                TextView tv = new TextView(getActivity().getApplicationContext());
                tv.setBackgroundResource(R.drawable.shape_search_et);
                tv.setPadding(30, 12, 0, 0);
                convertView = tv;
            }
            TextView tv = (TextView) convertView;
            tv.setTextColor(Color.parseColor("#55000000"));

            //            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams();
            //            convertView.setLayoutParams(params);
            tv.setTextSize(14);
            if (groupPosition == 0) {
                tv.setText(mData.get(childPosition));
            } else {
                tv.setText(searchOld.get(childPosition));
            }

            return tv;
        }

        //设置子条目是否可以进行点击
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
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

        //        //POST 请求PSOT参数
        //        @FormUrlEncoded  //进行表单url编码
        //        @POST("search")
        //        Call<SearchGoodsBean> search(@Field("page") int page, @Field("pageNum") int pageNum, @Field("orderby") String orderby, @Field("keyword") String keyword);

    }


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
}
