package com.hfkj.redchildsupermarket.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.fragment.BaseFragment;
import com.hfkj.redchildsupermarket.fragment.BrandFragment;
import com.hfkj.redchildsupermarket.fragment.CarFragment;
import com.hfkj.redchildsupermarket.fragment.HomeFragment;
import com.hfkj.redchildsupermarket.fragment.MoreFragment;
import com.hfkj.redchildsupermarket.fragment.SearchFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wf on 2016/9/4.
 */
public class MainActivity extends FragmentActivity {
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


    private Fragment mFragHome;
    private Fragment mFragSearch;
    private Fragment mFragBrand;
    private Fragment mFragCar;
    private Fragment mFragMore;
    private RadioButton previousChecked;

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.menu:
                    setCheckedTextColor(mMenu);
                    changeFragment((BaseFragment) mFragHome);
                    break;
                case R.id.search:
                    setCheckedTextColor(mSearch);
                    changeFragment((BaseFragment) mFragSearch);
                    break;
                case R.id.brand:
                    setCheckedTextColor(mBrand);
                    changeFragment((BaseFragment) mFragBrand);
                    break;
                case R.id.shopping:
                    setCheckedTextColor(mShopping);
                    changeFragment((BaseFragment) mFragCar);
                    break;
                case R.id.more:
                    setCheckedTextColor(mMore);
                    changeFragment((BaseFragment) mFragMore);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        mMainRadio.setOnCheckedChangeListener(listener);
        mMenu.setChecked(true);
    }

    private void  initFragment(){
        mFragHome =new HomeFragment();
        mFragSearch =new SearchFragment();
        mFragBrand =new BrandFragment();
        mFragCar =new CarFragment();
        mFragMore =new MoreFragment();
    }

    public void changeFragment(BaseFragment frag){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content,frag);
        transaction.commit();

        // frag.initData();

    }
    private void setCheckedTextColor(RadioButton radioButton) {
        radioButton.setTextColor(Color.RED);
        if (previousChecked != null) {
            previousChecked.setTextColor(Color.WHITE);
        }
        previousChecked = radioButton;
    }
    public RadioButton getBrand () {
        return  mBrand;
    }

}
