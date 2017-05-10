package bluetooth.inuker.com.grassinvain.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.network.body.request.SysmessageBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class Feedback extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout back;
    private LinearLayout submitYijinfankui;
    private MyEditText writeYijian;
    private MyEditText phoneNumber;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        userModel = new UserModel(this);
        initView();
    }

    private void initView() {
        // 返回
        back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 提交意见
        submitYijinfankui = (LinearLayout) findViewById(R.id.submit_yijian);
        submitYijinfankui.setOnClickListener(this);
        // 意见内容
        writeYijian = (MyEditText) findViewById(R.id.write_yijian);
        // 电话号码
        phoneNumber = (MyEditText) findViewById(R.id.phone_munber);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit_yijian:
                String s = writeYijian.getText().toString();
                String s1 = phoneNumber.getText().toString();
                if (!"".equals(s) && !"".equals(s1)) {
                    perform(s, s1);
                } else {
                    if ("".equals(s)) {
                        Toast.makeText(getBaseContext(), "请填写您的建议", Toast.LENGTH_SHORT).show();
                    }
                    if ("".equals(s1)) {
                        Toast.makeText(getBaseContext(), "请填写手机号码", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void perform(String s, String s1) {

        SysmessageBody sysmessageBody = new SysmessageBody();
        sysmessageBody.content = s;
        sysmessageBody.contactWay = s1;
        userModel.getSubmitSysMessage(sysmessageBody, new Callback<Object>() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getBaseContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onFailure(int resultCode, String message) {
                Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
