package com.hfkj.redchildsupermarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hfkj.redchildsupermarket.activity.HomePageActivity;

public class SplashActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,HomePageActivity.class));
                SplashActivity.this.finish();
            }
        }, 2000);
    }
}
