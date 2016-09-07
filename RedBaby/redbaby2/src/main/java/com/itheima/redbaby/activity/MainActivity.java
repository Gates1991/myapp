package com.itheima.redbaby.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.itheima.redbaby.R;
import com.itheima.redbaby.fragment.BrandFragment;
import com.itheima.redbaby.fragment.CarFragment;
import com.itheima.redbaby.fragment.HomeFragment;
import com.itheima.redbaby.fragment.MoreFragment;
import com.itheima.redbaby.fragment.SearchFragment;

public class MainActivity extends BaseActivty {

    private RadioGroup bottomBar;
    private RadioButton rbBottomHome;
    private RadioButton rbBottomSearch;
    private RadioButton rbBottomBrand;
    private RadioButton rbBottomCart;
    private RadioButton rbBottomMore;

    /**
     * 当前选中的Fragment
     */
    private Fragment mCurrentFragment;
    private Fragment mFragHome, mFragSearch, mFragBrand, mFragCar, mFragMore;
    private OnCheckedChangeListener listener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_bottom_home:
                    changeFragment(mFragHome);
                    break;
                case R.id.rb_bottom_search:
                    changeFragment(mFragSearch);
                    break;
                case R.id.rb_bottom_brand:
                    changeFragment(mFragBrand);

                    break;
                case R.id.rb_bottom_car:
                    changeFragment(mFragCar);

                    break;

                case R.id.rb_bottom_more:
                    changeFragment(mFragMore);

                    break;

            }
        }
    };


    private void changeFragment(Fragment frag){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content,frag);
        transaction.commit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initFragment();
        initView();

        changeFragment(mFragHome);
    }

    private void initView() {
        bottomBar = (RadioGroup) findViewById(R.id.bottom_bar);
        bottomBar.setOnCheckedChangeListener(listener);

    }

    private void  initFragment(){
        mFragHome=new HomeFragment();
        mFragSearch=new SearchFragment();
        mFragBrand=new BrandFragment();
         mFragCar=new CarFragment();
        mFragMore=new MoreFragment();
    }

}
