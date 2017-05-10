package bluetooth.inuker.com.grassinvain.controller.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.MyFragmentPagerAdapter;

public class WorkResult extends AppCompatActivity {

    private TabLayout tabLayoutShouyi;
    private ViewPager shouyiViewpager;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_result);
        initData();
        initView();
    }

    /**
     * 准备数据源
     */
    private void initData() {


    }

    /**
     * 初始化控件321
     */
    private void initView() {
          // 初始化 viewpager  绑定适配器
        //  返回监听
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        shouyiViewpager = (ViewPager) findViewById(R.id.shouyi_viewpager);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        shouyiViewpager.setAdapter(myFragmentPagerAdapter);

        // 初始化tablayout  绑定viewpager
        tabLayoutShouyi = (TabLayout) findViewById(R.id.tablayout_shouyicheck);
        tabLayoutShouyi.setTabMode(tabLayoutShouyi.MODE_FIXED);
        tabLayoutShouyi.setupWithViewPager(shouyiViewpager);

        // 指定tab的位置    为了下一步添加给tab添加图片
        TabLayout.Tab tabAt = tabLayoutShouyi.getTabAt(0);
        TabLayout.Tab tabAt1 = tabLayoutShouyi.getTabAt(1);

        // 添加tab图片 项目不需要
        // tabAt.setIcon(R.mipmap.ic_launcher);


    }
}
