package com.hfkj.redchildsupermarket.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
 * @创建时间 2016/9/6 18:28
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AboutFragment extends  BaseFragment implements View.OnClickListener {


    private View mView;
    private TextView mTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        mView = inflater.inflate(R.layout.fragment_about, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        initData();
        return mView;
    }

    private void initTitleView() {
        Button mBt_title_left = (Button) mView.findViewById(R.id.bt_title_left);
        mBt_title_left.setVisibility(View.VISIBLE);

        TextView mTtv_title = (TextView) mView.findViewById(R.id.tv_title_name);
        mTtv_title.setText("关于");
        mTtv_title.setTextSize(24);

        mTv = (TextView) mView.findViewById(R.id.tv);
        mBt_title_left.setOnClickListener(this);
    }

    public void initData() {
        try {
            mTv.setText("版本号："+ getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getVersionName() throws Exception{
        // 获取packagemanager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_title_left:
        		mMainActivity.popBackStack();
                break;

            default:
                break;
        }

    }
}
