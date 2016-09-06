package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends Fragment {

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_title_name)
    TextView mTvTitleName;
    @Bind(R.id.bt_search)
    Button btSearch;
    @Bind(R.id.el_search1)
    ExpandableListView elSearch1;
    @Bind(R.id.el_search2)
    ExpandableListView elSearch2;
    private TextView tvTitle;

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
}
