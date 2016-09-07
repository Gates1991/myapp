package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/7 9:43
 * @描述	$ 商品详情界面
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/7$
 * @更新描述	${TODO}
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;

public class DetailsFragment extends BaseFragment {

    private int mCid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, null);


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCid = bundle.getInt("id");
    }

    @Override
    public void initData() {
        Toast.makeText(mContext, ""+mCid, Toast.LENGTH_SHORT).show();
    }
}
