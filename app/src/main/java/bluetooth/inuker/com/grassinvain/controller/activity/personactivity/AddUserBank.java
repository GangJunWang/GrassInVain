package bluetooth.inuker.com.grassinvain.controller.activity.personactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.network.body.request.AddbankCardBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class AddUserBank extends AppCompatActivity {

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_bank);
        userModel = new UserModel(this);
        initView();
    }

    private void initView() {

        LinearLayout back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final MyEditText name = (MyEditText) findViewById(R.id.name);
        final MyEditText number = (MyEditText) findViewById(R.id.number);
        final MyEditText cardtype = (MyEditText) findViewById(R.id.cardtype);
        final MyEditText phone = (MyEditText) findViewById(R.id.phone);
        TextView submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!"".equals(name.getText().toString()) && !"".equals(number.getText().toString()) && !"".equals(cardtype.getText().toString()) && !"".equals(phone.getText().toString())) {
                    AddbankCardBody addbankCardBody = new AddbankCardBody();
                    addbankCardBody.cardHolder = name.getText().toString();
                    addbankCardBody.cardCode = number.getText().toString();
                    addbankCardBody.bankName = cardtype.getText().toString();
                    addbankCardBody.reservedPhone = phone.getText().toString();
                    userModel.getAddBankCard(addbankCardBody, new Callback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getBaseContext(), "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        @Override
                        public void onFailure(int resultCode, String message) {
                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(), "请完善信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
