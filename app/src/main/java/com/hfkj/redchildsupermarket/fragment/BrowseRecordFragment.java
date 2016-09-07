package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfkj.redchildsupermarket.R;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 18:23
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class BrowseRecordFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_browserecord,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void initData() {

    }
}
