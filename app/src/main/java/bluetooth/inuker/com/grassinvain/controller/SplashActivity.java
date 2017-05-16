package bluetooth.inuker.com.grassinvain.controller;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                finish();
            }
        }, 1000);

    }
}
