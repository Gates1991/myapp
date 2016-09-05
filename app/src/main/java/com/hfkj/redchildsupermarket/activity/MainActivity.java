package com.hfkj.redchildsupermarket.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.allpage.BasePage;
import com.hfkj.redchildsupermarket.allpage.ShoppingPage;
import com.hfkj.redchildsupermarket.view.NoScrollViewPager;

import java.util.ArrayList;

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
    NoScrollViewPager mViewPager;
    private RadioButton previousChecked;   //记录上次被点击的RadioButton
    private ArrayList<BasePage> mAllPagesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        mAllPagesList.add(new ShoppingPage(MainActivity.this));
        mAllPagesList.add(new ShoppingPage(MainActivity.this));
        mAllPagesList.add(new ShoppingPage(MainActivity.this));
        mAllPagesList.add(new ShoppingPage(MainActivity.this));
        mAllPagesList.add(new ShoppingPage(MainActivity.this));
        mViewPager.setAdapter(new MainAdapter() );
        mMainRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.menu:
                        mMenu.setTextColor(Color.RED);
                        setCheckedTextColor(mMenu);
                        Toast.makeText(MainActivity.this,"主页被点击了",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        mSearch.setTextColor(Color.RED);
                        setCheckedTextColor(mSearch);
                        break;
                    case R.id.brand:
                        mBrand.setTextColor(Color.RED);
                        setCheckedTextColor(mBrand);
                        break;
                    case R.id.shopping:
                        mShopping.setTextColor(Color.RED);
                        setCheckedTextColor(mShopping);
                        mViewPager.setCurrentItem(3,false);
                        mAllPagesList.get(3).initData();
                        break;
                    case R.id.more:
                        mMore.setTextColor(Color.RED);
                        setCheckedTextColor(mMore);
                        break;
                    default:
                        break;
                }
            }
        });
        mMenu.setChecked(true);
    }

    private void setCheckedTextColor(RadioButton radioButton) {
        if (previousChecked != null) {
            previousChecked.setTextColor(Color.WHITE);
        }
        previousChecked = radioButton;
    }
    private class MainAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mAllPagesList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mAllPagesList.get(position).getRootView();
            container.addView(view);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    public RadioButton getRadioButton() {
        return  mBrand;
    }
}
