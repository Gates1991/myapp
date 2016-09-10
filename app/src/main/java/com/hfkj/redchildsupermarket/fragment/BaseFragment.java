package com.hfkj.redchildsupermarket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hfkj.redchildsupermarket.activity.MainActivity;

/**
 * @author Chance
 *
 *进行了Retrofit的初始化
 *
 */
public abstract class BaseFragment extends Fragment {

	public Context mContext;
	public MainActivity mMainActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		mMainActivity = (MainActivity) mContext;
	}


}
