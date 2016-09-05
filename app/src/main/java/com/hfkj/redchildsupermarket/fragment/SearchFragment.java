package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

public class SearchFragment extends Fragment {

	private TextView tvTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_search, null);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
