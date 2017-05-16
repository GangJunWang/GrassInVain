package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.controller.activity.personactivity.UserBankActivity;
import bluetooth.inuker.com.grassinvain.network.body.request.TixianBody;
import bluetooth.inuker.com.grassinvain.network.body.response.BankCardChiredBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class TixianRecoder extends AppCompatActivity {

    private LinearLayout back;
    private LinearLayout change_bank_card;
    private MyEditText tixian_number;
    private TextView all_tixian;
    private TextView current_yue;
    private TextView tixian;
    private String uesrmonery;
    private TextView user_banknumber;
    private UserModel userModel;
    private int requestCode1 = 1;
    private int requestCode2 = 2;
    private BankCardChiredBody bankcard;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_recoder);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        uesrmonery = intent.getStringExtra("uesrmonery");
        initView();

    }


    private void initView() {
        // 返回
        back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // 切换银行卡
        change_bank_card = (LinearLayout) findViewById(R.id.change_bank_card);
        change_bank_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TixianRecoder.this, UserBankActivity.class);
                startActivityForResult(intent1, requestCode1);
            }
        });

        // 银行卡账号
        user_banknumber = (TextView) findViewById(R.id.user_banknumber);
        //提现金额
        tixian_number = (MyEditText) findViewById(R.id.tixian_number);
        // 当前可用余额
        current_yue = (TextView) findViewById(R.id.current_yue);
        if ("0".equals(uesrmonery)) {
            current_yue.setText("可用余额￥0.00元,");
        } else {

            String s = TextUtil.fen2yuan(Integer.parseInt(uesrmonery));
            current_yue.setText("可用余额￥" + s + "元,");
        }
        //全部提现
        all_tixian = (TextView) findViewById(R.id.all_tixian);
        all_tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("0".equals(uesrmonery)) {
                    tixian_number.setText("0");
                } else {
                    tixian_number.setText(uesrmonery.substring(0, uesrmonery.length() - 2));
                }

            }
        });
        // 确认提现
        tixian = (TextView) findViewById(R.id.tixian);
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(tixian_number.getText().toString())) {
                    Toast.makeText(getBaseContext(), "请输入提现金额", Toast.LENGTH_SHORT).show();
                } else if (Long.parseLong(uesrmonery) < Long.parseLong(tixian_number.getText().toString())) {
                    Toast.makeText(getBaseContext(), "超出账户余额", Toast.LENGTH_SHORT).show();
                } else if ("请选择银行卡".equals(user_banknumber.getText().toString())) {
                    Toast.makeText(getBaseContext(), "请选择银行卡", Toast.LENGTH_LONG).show();
                } else {

                    TixianBody tixianBody = new TixianBody();
                    tixianBody.bankCardId = bankcard.banksId;
                    String s = TextUtil.insertString2(tixian_number.getText().toString());
                    tixianBody.applyCount = s;
                    userModel.getTixian(tixianBody, new Callback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getBaseContext(), "提现成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(int resultCode, String message) {
                            Toast.makeText(getBaseContext(), "提现失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                Bundle extras = data.getExtras();
                bankcard = (BankCardChiredBody) extras.getSerializable("bankcard");
                user_banknumber.setText(bankcard.cardCode);

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
