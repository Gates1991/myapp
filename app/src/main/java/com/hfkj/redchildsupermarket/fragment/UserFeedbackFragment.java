package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 18:27
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserFeedbackFragment extends  BaseFragment {


    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        mView = inflater.inflate(R.layout.fragment_userfeedback, null);
        mMainActivity.isMainFrament = false;
        initTitleView();
        return mView;
    }

    private void initTitleView() {
        Button mBt_title_left = (Button) mView.findViewById(R.id.bt_title_left);
        mBt_title_left.setVisibility(View.VISIBLE);
        Button mTt_title_right = (Button) mView.findViewById(R.id.bt_title_right);
        mTt_title_right.setText("提交");
        mTt_title_right.setVisibility(View.VISIBLE);
        TextView mTv_title_name = (TextView) mView.findViewById(R.id.tv_title_name);
        mTv_title_name.setText("用户反馈");

    }

    public void initData() {

    }
}
