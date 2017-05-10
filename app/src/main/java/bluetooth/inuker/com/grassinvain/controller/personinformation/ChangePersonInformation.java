package bluetooth.inuker.com.grassinvain.controller.personinformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.activity.personactivity.ChangeGenderActivity;
import bluetooth.inuker.com.grassinvain.controller.activity.personactivity.ChangeNameActivity;
import bluetooth.inuker.com.grassinvain.controller.activity.personactivity.UserBankActivity;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class ChangePersonInformation extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout live_item_1, live_item_2, live_item_3, live_item_4, live_item_5;
    private TextView person_name;
    private TextView person_gender;
    private TextView bank_number;
    private UserInfo userInfo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_person_information);
        initData();
        initView();
    }
    private void initView() {
        // 返回
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 修改名字
        live_item_1 = (RelativeLayout) findViewById(R.id.live_item_1);
        live_item_1.setOnClickListener(this);
        // 修改性别
        live_item_2 = (RelativeLayout) findViewById(R.id.live_item_2);
        live_item_2.setOnClickListener(this);
        //收货地址
        live_item_3 = (RelativeLayout) findViewById(R.id.live_item_3);
        live_item_3.setOnClickListener(this);
        //申请离职
        live_item_4 = (RelativeLayout) findViewById(R.id.live_item_4);
        live_item_4.setOnClickListener(this);
        //银行卡
        live_item_5 = (RelativeLayout) findViewById(R.id.live_item_5);
        live_item_5.setOnClickListener(this);

        person_name = (TextView) findViewById(R.id.person_name);
        person_gender = (TextView) findViewById(R.id.person_gender);
        bank_number = (TextView) findViewById(R.id.bank_number);
    }
    private void initData() {
        UserModel userModel = new UserModel(this);
        userModel.getPersonCentern(new Callback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                userInfo1 = userInfo;
                setImformation(userInfo);
            }
            @Override
            public void onFailure(int resultCode, String message) {
            }
        });
    }

    private void setImformation(UserInfo userInfo) {

        person_name.setText(userInfo.userName);
        person_gender.setText(userInfo.gender);
        bank_number.setText(userInfo.bankCount);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.live_item_1: {
                Intent intent1 = new Intent(ChangePersonInformation.this, ChangeNameActivity.class);
                intent1.putExtra("name", userInfo1.userName);
                startActivity(intent1);
            }

            break;
            case R.id.live_item_2: {
                Intent intent1 = new Intent(ChangePersonInformation.this, ChangeGenderActivity.class);
                intent1.putExtra("gender", userInfo1.gender);
                startActivity(intent1);
            }

            break;
            case R.id.live_item_3: {
                Intent intent1 = new Intent(ChangePersonInformation.this, TheGoodsAddress.class);

                startActivity(intent1);
            }
            break;
            case R.id.live_item_4:
                break;
            case R.id.live_item_5:{
                Intent intent1 = new Intent(ChangePersonInformation.this, UserBankActivity.class);
                startActivity(intent1);
            }

                break;
        }
    }
}
