package bluetooth.inuker.com.grassinvain.controller.activity.personactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.WuliuRecycAdapter;

public class WuliuActivity extends AppCompatActivity {

    private RecyclerView wuliu_recyclerview;
    private List<Object> data = new ArrayList<>();
    private LinearLayout zanwuwuliu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliu);
        Intent intent = getIntent();
        String order = intent.getStringExtra("order");
        initView();
        initData();
    }

    private void initView() {

        // 返回
        LinearLayout back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        zanwuwuliu = (LinearLayout) findViewById(R.id.zanwuwuliu);
        wuliu_recyclerview = (RecyclerView) findViewById(R.id.wuliu_recyclerview);
        wuliu_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        WuliuRecycAdapter wuliuRecycAdapter = new WuliuRecycAdapter(getBaseContext(), data, R.layout.wuliu_recyc_contont);
        wuliu_recyclerview.setAdapter(wuliuRecycAdapter);
    }

    private void initData() {

        if (0 == data.size()) {
            zanwuwuliu.setVisibility(View.VISIBLE);
            wuliu_recyclerview.setVisibility(View.GONE);
        }
        if (0 != data.size()) {
            zanwuwuliu.setVisibility(View.GONE);
            wuliu_recyclerview.setVisibility(View.VISIBLE);
        }
    }
}
