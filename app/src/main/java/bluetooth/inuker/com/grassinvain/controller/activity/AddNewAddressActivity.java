package bluetooth.inuker.com.grassinvain.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.network.body.request.AddAdressBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class AddNewAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private UserModel userModel;
    private CityPickerView mCityPickerView;
    private TextView alredly_choose;
    private MyEditText myEditText3;
    private MyEditText address_phone_add;
    private MyEditText address_name_add;
    private TextView dissmiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        userModel = new UserModel(this);
        initView();
    }

    private void initView() {
        //返回
        LinearLayout back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 布局
        dissmiss = (TextView) findViewById(R.id.dissmiss);
        // 收货人姓名
        address_name_add = (MyEditText) findViewById(R.id.address_name_add);
        // 收货人电话
        address_phone_add = (MyEditText) findViewById(R.id.address_phone_add);
        //详细地址
        myEditText3 = (MyEditText) findViewById(R.id.myEditText3);
        // 选择省市区
        RelativeLayout choose_address = (RelativeLayout) findViewById(R.id.choose_address);
        choose_address.setOnClickListener(this);
        //获取省市区
        alredly_choose = (TextView) findViewById(R.id.alredly_choose);
        // 保存信息
        TextView save_address = (TextView) findViewById(R.id.save_address);
        save_address.setOnClickListener(this);
        mCityPickerView = new CityPickerView(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.save_address:
                String trim = address_name_add.getText().toString().trim();
                String trim1 = address_phone_add.getText().toString().trim();
                String trim2 = alredly_choose.getText().toString().trim();
                String trim3 = myEditText3.getText().toString().trim();
                if (!"".equals(trim) || !"".equals(trim1) || !"".equals(trim2) || !"".equals(trim3)) {
                    AddAdressBody addAdressBody = new AddAdressBody();
                    addAdressBody.userName = trim;
                    addAdressBody.userMobile = trim1;
                    addAdressBody.address = trim2;
                    addAdressBody.addressDetail = trim3;
                    userModel.getAddAdress(addAdressBody, new Callback<Object>() {
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
                break;
            case R.id.choose_address:
                chooseAddress();
                break;
            case R.id.dissmiss:
                TextUtil.setdissmiss(getBaseContext());
                break;
        }
    }
    private void chooseAddress() {
        // 设置点击外部是否消失
        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
//        mCityPickerView.setTextSize(18f);
        // 设置标题
//        mCityPickerView.setTitle("我是标题");
        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);
        // 设置取消文字大小
//        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
        //   mCityPickerView.setSubmitTextColor(R.color.zhuticolor);
        // 设置确定文字大小
        //  mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
        //  mCityPickerView.setHeadBackgroundColor(R.color.zhuticolor);
        mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener() {
            @Override
            public void onCitySelect(String str) {

                super.onCitySelect(str);
                alredly_choose.setText(str);
            }

            @Override
            public void onCitySelect(String prov, String city, String area) {
                super.onCitySelect(prov, city, area);
            }
        });
        mCityPickerView.show();
    }

}
