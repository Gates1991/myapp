package com.hfkj.redchildsupermarket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author Chance
 *
 *进行了Retrofit的初始化
 *
 */
public abstract class BaseFragment extends Fragment {

	public Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		mContext = getActivity();

	}

	public abstract  void  initData();

}
