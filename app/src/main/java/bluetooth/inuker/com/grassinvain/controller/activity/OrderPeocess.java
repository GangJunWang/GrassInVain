package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.MyTabViewpagerThreeAdapter;

public class OrderPeocess extends AppCompatActivity {
    private List<Object> data = new ArrayList<>();
    private String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_peocess);
        Intent intent = getIntent();
        page = intent.getStringExtra("page");
        initData();
        initView();
    }

    private void initData() {
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());

    }

    private void initView() {
        // 返回监听
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // viewPager
        ViewPager viewpager_Three = (ViewPager) findViewById(R.id.viewPager_three);
        //准备适配器
        MyTabViewpagerThreeAdapter myTabViewpagerThreeAdapter = new MyTabViewpagerThreeAdapter(getSupportFragmentManager());
        viewpager_Three.setAdapter(myTabViewpagerThreeAdapter);


        //头部tab
        TabLayout tabLayoutThree = (TabLayout) findViewById(R.id.tablayout_three);
        tabLayoutThree.setTabMode(tabLayoutThree.MODE_FIXED);
        tabLayoutThree.setupWithViewPager(viewpager_Three);
        viewpager_Three.setCurrentItem(Integer.parseInt(page),false);

    }
}
