package com.hfkj.redchildsupermarket.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.allpage.BasePage;
import com.hfkj.redchildsupermarket.allpage.BrandPage;
import com.hfkj.redchildsupermarket.allpage.HomePage;
import com.hfkj.redchildsupermarket.allpage.MorePage;
import com.hfkj.redchildsupermarket.allpage.SearchPage;
import com.hfkj.redchildsupermarket.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wf on 2016/9/4.
 */
public class MainActivity extends Activity {
    @Bind(R.id.main_radio)
    RadioGroup  mMainRadio;
    @Bind(R.id.menu)
    RadioButton mMenu;
    @Bind(R.id.search)
    RadioButton mSearch;
    @Bind(R.id.brand)
    RadioButton mBrand;
    @Bind(R.id.shopping)
    RadioButton mShopping;
    @Bind(R.id.more)
    RadioButton mMore;

    @Bind(R.id.nsvp)
    public NoScrollViewPager mNoScrollViewPager;
    private RadioButton previousChecked;   //记录上次被点击的RadioButton
    private List<BasePage>    mPageList;
    private MainPageAdapter mMainPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {

        mPageList = new ArrayList<>();
        mPageList.add(new HomePage(MainActivity.this));
        mPageList.add(new BrandPage(MainActivity.this));
        mPageList.add(new MorePage(MainActivity.this));
        mPageList.add(new SearchPage(MainActivity.this));

         mMainRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.menu:
                        mMenu.setTextColor(Color.RED);
                        setCheckedTextColor(mMenu);
                        mNoScrollViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.search:
                        mSearch.setTextColor(Color.RED);
                        setCheckedTextColor(mSearch);
                        mNoScrollViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.brand:
                        mBrand.setTextColor(Color.RED);
                        setCheckedTextColor(mBrand);
                        mNoScrollViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.shopping:
                        mShopping.setTextColor(Color.RED);
                        setCheckedTextColor(mShopping);
                        mNoScrollViewPager.setCurrentItem(3,false);
                        break;
                    case R.id.more:
                        mMore.setTextColor(Color.RED);
                        setCheckedTextColor(mMore);
                        mNoScrollViewPager.setCurrentItem(4,false);
                        break;
                    default:
                        break;
                }
            }
        });
        mMenu.setChecked(true);
        mMainPageAdapter = new MainPageAdapter();
        mNoScrollViewPager.setAdapter(mMainPageAdapter);

    }

    private  class MainPageAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePage basePage = mPageList.get(position);
            container.addView(basePage.getRootView());
            return basePage.getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }

    private void setCheckedTextColor(RadioButton radioButton) {
        if (previousChecked != null) {
            previousChecked.setTextColor(Color.WHITE);
        }
        previousChecked = radioButton;
    }
}
