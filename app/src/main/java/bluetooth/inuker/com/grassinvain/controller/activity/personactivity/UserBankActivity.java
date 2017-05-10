package bluetooth.inuker.com.grassinvain.controller.activity.personactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.activity.TixianRecoder;
import bluetooth.inuker.com.grassinvain.controller.adapter.BankCardAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.BankCardBody;
import bluetooth.inuker.com.grassinvain.network.body.response.BankCardChiredBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class UserBankActivity extends AppCompatActivity implements View.OnClickListener {

    private List<BankCardChiredBody> data = new ArrayList<>();
    private BankCardAdapter bankCardAdapter;
    private TextView bianji_bankCard;
    private UserModel userModel;
    private RecyclerView bank_list_recycview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bank);
        userModel = new UserModel(this);
        initData();
        initView();
    }

    private void initData() {

        PageBody pageBody = new PageBody();
        pageBody.pageSize = 1;
        pageBody.pageNum = 10;
        userModel.getBankCardList(pageBody, new Callback<BankCardBody>() {
            @Override
            public void onSuccess(BankCardBody bankCardBody) {
                List<BankCardChiredBody> list = bankCardBody.list;
                data.clear();
                data.addAll(list);
                bankCardAdapter.clear();
                bankCardAdapter.addAll(list);
                bankCardAdapter.notifyDataSetHasChanged();
            }

            @Override
            public void onFailure(int resultCode, String message) {

            }
        });
    }

    private void initView() {
        //返回监听
        LinearLayout back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 编辑银行卡
        bianji_bankCard = (TextView) findViewById(R.id.bianji_bankcard);
        bianji_bankCard.setOnClickListener(this);
        // recyclerview
        bank_list_recycview = (RecyclerView) findViewById(R.id.bank_list_recycview);
        bank_list_recycview.setLayoutManager(new LinearLayoutManager(this));
        bankCardAdapter = new BankCardAdapter(UserBankActivity.this, data, R.layout.bank_list_contont);
        bank_list_recycview.setAdapter(bankCardAdapter);

        //点击条目 返回上一级目录 带走银行卡想对应的信息
        bankCardAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                Intent intent1 = new Intent(UserBankActivity.this, TixianRecoder.class);
                intent1.putExtra("bankcard", data.get(position));
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bianji_bankcard:
                if ("编辑".equals(bianji_bankCard.getText().toString())) {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).isClick = true;
                    }
                    bankCardAdapter.clear();
                    bankCardAdapter.addAll(data);
                    bankCardAdapter.notifyDataSetHasChanged();
                    bianji_bankCard.setText("删除");
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).isClick = false;
                    }
                    if ("删除".equals(bianji_bankCard.getText().toString())) {
                        digui();
                        bianji_bankCard.setText("编辑");
                        bankCardAdapter.clear();
                        bankCardAdapter.addAll(data);
                        bankCardAdapter.notifyDataSetHasChanged();
                    }
                }
                break;

        }

    }

    private void digui() {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isCheck) {
                data.remove(i);
                digui();
            }
        }
    }


}
