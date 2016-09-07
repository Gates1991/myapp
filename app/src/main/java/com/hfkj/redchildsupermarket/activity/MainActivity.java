package com.hfkj.redchildsupermarket.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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


    private BaseFragment mFragHome;
    private BaseFragment mFragSearch;
    private BaseFragment mFragBrand;
    private BaseFragment mFragCar;
    private BaseFragment mFragMore;
    private RadioButton  previousChecked;

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.menu:
                    mMenu.setTextColor(Color.RED);
                    setCheckedTextColor(mMenu);
                    changeFragment(mFragHome);
                    break;
                case R.id.search:
                    mSearch.setTextColor(Color.RED);
                    setCheckedTextColor(mSearch);
                    changeFragment(mFragSearch);
                    break;
                case R.id.brand:
                    mBrand.setTextColor(Color.RED);
                    setCheckedTextColor(mBrand);
                    changeFragment(mFragBrand);
                    break;
                case R.id.shopping:
                    mShopping.setTextColor(Color.RED);
                    setCheckedTextColor(mShopping);
                    changeFragment(mFragCar);
                    break;
                case R.id.more:
                    mMore.setTextColor(Color.RED);
                    setCheckedTextColor(mMore);
                    changeFragment(mFragMore);
                    break;
                default:
                    break;
            }
        }
    };
    private FragmentManager supportFragmentManager;
    private long            mTimeMillis;
    private long mCurrentTimeMillis;
    private boolean isFirst;
    private long currentClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        supportFragmentManager = getSupportFragmentManager();
        initFragment();
        mMainRadio.setOnCheckedChangeListener(listener);
        supportFragmentManager = getSupportFragmentManager();
        mMenu.setChecked(true);
    }

    private void initFragment() {
        mFragHome = new HomeFragment();
        mFragSearch = new SearchFragment();
        mFragBrand = new BrandFragment();
        mFragCar = new CarFragment();
        mFragMore = new MoreFragment();
    }

    public void changeFragment(BaseFragment frag) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, frag);
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


    /**
     * 添加Fragment到回退栈,并且添加动画
     *
     * @param fragment
     */
    public void addToBackStack(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.fragment_slide_right_in, R.anim.fragment_slide_left_out,
                R.anim.fragment_slide_left_in, R.anim.fragment_slide_right_out
        );
        transaction.replace(R.id.fl_content, fragment);
        transaction.addToBackStack(null);
        mMainRadio.setVisibility(View.GONE);
        transaction.commit();

    }
    public void addToBackStack(BaseFragment fragment,int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.fragment_slide_right_in, R.anim.fragment_slide_left_out,
                R.anim.fragment_slide_left_in, R.anim.fragment_slide_right_out
        );
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragment.setArguments(bundle);
        transaction.replace(R.id.fl_content, fragment);
        transaction.addToBackStack(null);
        mMainRadio.setVisibility(View.GONE);
        transaction.commit();

    }
    public void addToBackStack(BaseFragment fragment,String keyword) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.fragment_slide_right_in, R.anim.fragment_slide_left_out,
                R.anim.fragment_slide_left_in, R.anim.fragment_slide_right_out
        );
        Bundle bundle = new Bundle();

        bundle.putString("keyword",keyword);

        fragment.setArguments(bundle);
        transaction.replace(R.id.fl_content, fragment);
        transaction.addToBackStack(null);
        mMainRadio.setVisibility(View.GONE);
        transaction.commit();

    }

    /**
     * 清空栈
     */
    public void clearBackStack(){
        supportFragmentManager.popBackStack(null,1);//参数为0，清除栈顶的Fragment，参数为1，清空栈
    }

    /**
     * 模拟退栈
     */
    public void popBackStack(){
        mMainRadio.setVisibility(View.VISIBLE);
        supportFragmentManager.popBackStack(null,0);//参数为0，清除栈顶的Fragment，参数为1，清空栈
    }

    public RadioButton getBrand() {
        return mBrand;
    }

    @Override
    public void onBackPressed() {
       long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - currentClickTime >2000){
            Toast.makeText(this, "再次点击则退出应用", Toast.LENGTH_SHORT).show();
            currentClickTime = currentTimeMillis;
        }else {
            finish();
        }


    }
}
