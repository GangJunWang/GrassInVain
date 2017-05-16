package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.controller.adapter.ZhangDanDetailAdapter;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonShouyiZDChiredBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonShouyiZhangdanBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class ShouyiDetail extends AppCompatActivity implements View.OnClickListener {

    private TextView curronMonery;
    private TextView personShouyi;
    private TextView teanShouyi;
    private TextView jiaoyuJiangjin;
    private TextView jitixianJine;
    private TextView shouruJine;
    private RecyclerView zhangdanRecycView;
    private UserModel userModel;
    private List<PersonShouyiZDChiredBody> data = new ArrayList<>();
    private TextView tiXian;
    private ImageView back;
    private UserInfo userInfo;
    private ZhangDanDetailAdapter zhangDanDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouyi_detail);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("detail");
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        initData();
        super.onResume();

    }

    private void initData() {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = 10;
        pageBody.pageSize = 1;
        userModel.getPerZhangDList(pageBody, new Callback<PersonShouyiZhangdanBody>() {
            @Override
            public void onSuccess(PersonShouyiZhangdanBody personShouyiZhangdanBody) {
                List<PersonShouyiZDChiredBody> list = personShouyiZhangdanBody.list;
                data.clear();
                zhangDanDetailAdapter.clear();
                data.addAll(list);
                zhangDanDetailAdapter.addAll(list);
                zhangDanDetailAdapter.notifyDataSetHasChanged();
            }

            @Override
            public void onFailure(int resultCode, String message) {

            }
        });

        /**
         * 实时更新用户余额
         */

        userModel.getPersonCentern(new Callback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                String balance = userInfo.account.balance;
                String s = TextUtil.fen2yuan(Integer.parseInt(balance));
                curronMonery.setText(s);
            }

            @Override
            public void onFailure(int resultCode, String message) {

            }
        });
    }

    private void initView() {
        // 返回
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 提现
        tiXian = (TextView) findViewById(R.id.textView5);
        tiXian.setOnClickListener(this);
        // 当前余额
        curronMonery = (TextView) findViewById(R.id.textView1);
        if ("0".equals(userInfo.account.balance)) {
            curronMonery.setText("0.00");
        } else {
            String s = TextUtil.fen2yuan(Integer.parseInt(userInfo.account.balance));
            curronMonery.setText(s);
        }
        // 个人收益
        personShouyi = (TextView) findViewById(R.id.person_shouyi);
        if ("0".equals(userInfo.account.personalProfit)) {
            personShouyi.setText("0.00");
        } else {
            String s = TextUtil.insertString(userInfo.account.personalProfit);
            personShouyi.setText(s);
        }

        // 团队收益
        teanShouyi = (TextView) findViewById(R.id.team_shouyi);
        if ("0".equals(userInfo.account.teamProfit)) {
            teanShouyi.setText("0.00");
        } else {
            String s = TextUtil.insertString(userInfo.account.teamProfit);
            teanShouyi.setText(s);
        }
        // 教育奖金
        jiaoyuJiangjin = (TextView) findViewById(R.id.jiaoyu_jiangjin);
        if ("0".equals(userInfo.account.educationProfit)) {
            jiaoyuJiangjin.setText("0.00");
        } else {
            String s = TextUtil.insertString(userInfo.account.educationProfit);
            jiaoyuJiangjin.setText(s);
        }
        //收入金额
        shouruJine = (TextView) findViewById(R.id.textView6);
        if ("0".equals(userInfo.account.balance)) {
            shouruJine.setText("0.00");
        } else {
            String s = TextUtil.insertString(userInfo.account.balance);
            shouruJine.setText(s);
        }
        // 提现金额
        jitixianJine = (TextView) findViewById(R.id.textView7);
        if ("0".equals(userInfo.account.cashBalance)) {
            jitixianJine.setText("0.00");
        } else {
            String s = TextUtil.insertString(userInfo.account.cashBalance);
            jitixianJine.setText(s);
        }
        jitixianJine.setText(userInfo.account.cashBalance);
        // 账单列表
        zhangdanRecycView = (RecyclerView) findViewById(R.id.zhangdan_recyclerView);
        zhangdanRecycView.setLayoutManager(new LinearLayoutManager(this));
        zhangDanDetailAdapter = new ZhangDanDetailAdapter(this, data, R.layout.zhangdan_liebiao_contont);
        zhangdanRecycView.setAdapter(zhangDanDetailAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView5:
                Intent intent = new Intent(ShouyiDetail.this, TixianRecoder.class);
                intent.putExtra("uesrmonery", userInfo.account.balance);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
