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
import android.view.MotionEvent;
import android.view.View;

import com.hfkj.redchildsupermarket.adapter.HomeVPAdapter;
import com.hfkj.redchildsupermarket.bean.HomeVPBean;

import java.util.List;

public class HomeViewPage extends ViewPager {
    private List<HomeVPBean.HomeTopicBean> pageList;
    private  Context                        mContext;
    private long mCurrentTimeMillis;
    private HomeVPAdapter mHomeVPAdapter;
    public Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    int currentItem = HomeViewPage.this.getCurrentItem();
                    if (pageList.size() != 0) {
                        int pos = (currentItem+1)%pageList.size();
                        HomeViewPage.this.setCurrentItem(pos);
                    }
                   /* int currentItem = HomeViewPage.this.getCurrentItem();
                    System.out.println(currentItem);
                   HomeViewPage.this.setCurrentItem(currentItem+1);*/
                    mHandler.sendEmptyMessageDelayed(0,2000);
                    break;
            }
        }
    };
    private onViewPageChangeListener mOnViewPageChangeListener;


    /**
     * @param pageList viewpage的集合
     * @param context
     */
    public HomeViewPage(List<HomeVPBean.HomeTopicBean> pageList,Context context) {
        super(context);
        this.pageList=pageList;
        this.mContext=context;
        init();
    }

    //添加触摸事件
    private void init() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeCallbacksAndMessages(null);
                        mCurrentTimeMillis = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        break;
                }
                return false;
            }
        });
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (mOnViewPageChangeListener != null) {
                   /* position%=pageList.size();
                    if (position<0){
                        position = pageList.size()+position;
                    }*/
                    mOnViewPageChangeListener.onViewPageChange(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public interface onViewPageChangeListener{
        public void onViewPageChange(int position);
    }
    public void setOnViewPageChange(onViewPageChangeListener listener){
        this.mOnViewPageChangeListener=listener;
    }


    //启动viewpage
    public void start(){

        if (mHomeVPAdapter == null) {
            mHomeVPAdapter = new HomeVPAdapter(pageList, mContext);
            this.setAdapter(mHomeVPAdapter);
        } else {
            mHomeVPAdapter.notifyDataSetChanged();
        }
       mHandler.sendEmptyMessageDelayed(0,0);

    }
    public void stop(){
        mHandler.removeCallbacksAndMessages(null);
        }

}
