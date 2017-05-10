package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.network.body.request.AddAdressBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class ChangeAddressimformation extends AppCompatActivity implements View.OnClickListener {

    private UserModel userModel;
    private TextView save_change_imformation;
    private ImageView back;
    private String address_name;
    private String address_phone;
    private String address_privince;
    private String address_detail;
    private String address_id;
    private MyEditText bianji_name;
    private MyEditText bianji_phone;
    private MyEditText bianji_detail;
    private TextView bianji_province;
    private TextView delete_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_addressimformation);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        address_name = intent.getStringExtra("address_name");
        address_phone = intent.getStringExtra("address_phone");
        address_privince = intent.getStringExtra("address_privince");
        address_detail = intent.getStringExtra("address_detail");
        address_id = intent.getStringExtra("address_Id");
        initView();
    }

    private void initView() {
        //返回
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 保存用户信息
        save_change_imformation = (TextView) findViewById(R.id.save_change_imformation);
        save_change_imformation.setOnClickListener(this);
        // 编辑名字
        bianji_name = (MyEditText) findViewById(R.id.bianji_name);
        // 编辑电话
        bianji_phone = (MyEditText) findViewById(R.id.bianji_phone);
        // 编辑详细地址
        bianji_detail = (MyEditText) findViewById(R.id.myEditText2);
        //编辑省市区
        bianji_province = (TextView) findViewById(R.id.bianji_province);
        // 删除当前地址
        delete_address = (TextView) findViewById(R.id.delete_address);
        delete_address.setOnClickListener(this);

        /**
         * 拿到接过来的值 赋值给每个相应控件
         */
        bianji_name.setText(address_name);
        bianji_phone.setText(address_phone);
        bianji_province.setText(address_privince);
        bianji_detail.setText(address_detail);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.delete_address:
                userModel.getDeleteAddress(address_id,new Callback<Object>()
                        {
                            @Override
                            public void onSuccess (Object o){
                                Toast.makeText(ChangeAddressimformation.this, "删除成功", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                            @Override
                            public void onFailure ( int resultCode, String message){
                                Toast.makeText(ChangeAddressimformation.this, "删除失败，网络异常", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                break;
            case R.id.save_change_imformation:
                /**
                 * 拿到控件上边的所有值  带着地址Id一块传给后台
                 */
                String trim = bianji_name.getText().toString().trim();
                String trim1 = bianji_phone.getText().toString().trim();
                String trim2 = bianji_province.getText().toString().trim();
                String trim3 = bianji_detail.getText().toString().trim();

                AddAdressBody addAdressBody = new AddAdressBody();
                addAdressBody.userName = trim;
                addAdressBody.userMobile = trim1;
                addAdressBody.address = trim2;
                addAdressBody.addressDetail = trim3;
                addAdressBody.userAddressId = address_id;

                userModel.getChangeAdress(addAdressBody, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getBaseContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(int resultCode, String message) {

                    }
                });
                break;
        }
    }


}
