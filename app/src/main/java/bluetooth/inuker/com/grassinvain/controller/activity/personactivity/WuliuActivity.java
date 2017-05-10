package bluetooth.inuker.com.grassinvain.controller.activity.personactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bluetooth.inuker.com.grassinvain.R;

public class WuliuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliu);
        Intent intent = getIntent();
        String order = intent.getStringExtra("order");
        initData();
        initView();
    }

    private void initView() {

    }

    private void initData() {

    }
}
