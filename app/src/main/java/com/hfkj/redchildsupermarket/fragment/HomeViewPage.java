package com.hfkj.redchildsupermarket.fragment;/*
 * @创建者  	bubble
 * @创建时间 	2016/9/6 11:31
 * @描述	${TODO}
 * 
 * @更新者      $Author$
 * @更新时间	2016/9/6$
 * @更新描述	${TODO}
 */

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.hfkj.redchildsupermarket.adapter.HomeVPAdapter;
import com.hfkj.redchildsupermarket.bean.HomeVPBean;

import java.util.List;

public class HomeViewPage extends ViewPager {
    private List<HomeVPBean.HomeTopicBean> pageList;
    private  Context                        mContext;
    private HomeVPAdapter mHomeVPAdapter;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    int currentItem = HomeViewPage.this.getCurrentItem();
                    if (pageList.size() != 0) {
                        int pos = (currentItem+1)%pageList.size();
                        HomeViewPage.this.setCurrentItem(pos,false);
                    }
                    System.out.println("22222");
                    mHandler.sendEmptyMessageDelayed(0,2000);
                    break;
            }

        }
    };

    public HomeViewPage(List<HomeVPBean.HomeTopicBean> pageList,Context context) {
        super(context);
        this.pageList=pageList;
        this.mContext=context;
    }

    public HomeViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public void start(){

        if (mHomeVPAdapter == null) {
            mHomeVPAdapter = new HomeVPAdapter(pageList, mContext);
            this.setAdapter(mHomeVPAdapter);
        } else {
            mHomeVPAdapter.notifyDataSetChanged();
        }
        mHandler.sendEmptyMessageDelayed(0,2000);

    }

}
