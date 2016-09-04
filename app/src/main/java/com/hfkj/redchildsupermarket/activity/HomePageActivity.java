package com.hfkj.redchildsupermarket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wf on 2016/9/4.
 */
public class HomePageActivity extends Activity {
    @Bind(R.id.main_radio)
    RadioGroup mMainRadio;

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
                    Toast.makeText(HomePageActivity.this, "你好", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }
}
