package com.hfkj.redchildsupermarket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreFragment extends Fragment {

    @Bind(R.id.imgbtn_left)
    ImageButton mImgbtnLeft;

    @Bind(R.id.btn_right)
    ImageButton mBtnRight;
    @Bind(R.id.user_center)
    RelativeLayout mUserCenter;
    @Bind(R.id.browse_record)
    RelativeLayout mBrowseRecord;
    @Bind(R.id.help_center)
    RelativeLayout mHelpCenter;
    @Bind(R.id.user_feedback)
    RelativeLayout mUserFeedback;
    @Bind(R.id.about)
    RelativeLayout mAbout;
    @Bind(R.id.ib_tel)
    ImageButton mIbTel;
    private TextView mTvTitle;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, null);
        //标题textview
        TextView mTvTitleLayout = (TextView) view.findViewById(R.id.tv_title_layout);
        mTvTitleLayout.setText("更多");
        initData();
        mContext = getActivity();


        ButterKnife.bind(this, view);
      /*  mUserCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, UserInformationActivity.class));
            }
        });*/

        return view;
    }

    private void initData() {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.user_center, R.id.browse_record, R.id.help_center, R.id.user_feedback, R.id.about, R.id.ib_tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_center://用户中心

                //判断是否登录,默认是没有登录,()


               break;
            case R.id.browse_record://浏览记录
                break;
            case R.id.help_center://帮助中心
                break;
            case R.id.user_feedback://用户反馈
                break;
            case R.id.about://关于
                break;
            case R.id.ib_tel://电话
                break;
        }
    }
}
