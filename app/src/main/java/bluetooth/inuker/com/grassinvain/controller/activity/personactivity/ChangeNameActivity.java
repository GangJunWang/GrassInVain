package bluetooth.inuker.com.grassinvain.controller.activity.personactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class ChangeNameActivity extends AppCompatActivity {

    private String name;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
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
        final MyEditText change_name = (MyEditText) findViewById(R.id.change_name);
        change_name.setText(name);
        TextView save = (TextView) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo userInfo = new UserInfo();
                String trim = change_name.getText().toString().trim();
                if (!"".equals(trim)) {
                    userInfo.userClassName = trim;
                    userModel.getUpdateUser(userInfo, new Callback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getBaseContext(), "提交成功，等待审核", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        @Override
                        public void onFailure(int resultCode, String message) {
                            Toast.makeText(getBaseContext(), "更新失败，请检查网络", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(), "请输入姓名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
