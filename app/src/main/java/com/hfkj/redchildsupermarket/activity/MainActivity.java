package com.hfkj.redchildsupermarket.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;

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
    private RadioButton previousChecked;   //记录上次被点击的RadioButton
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
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
}
