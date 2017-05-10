package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.base.BaseActivity;


public class AboutUs extends BaseActivity {


    private LinearLayout back;

    @Override
    public void widgetClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
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
        return R.layout.activity_about_us;
    }

    @Override
    public void initView(View view) {
        back = (LinearLayout) view.findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }
    @Override
    public void doBusiness(Context mContext) {

    }
}
