package bluetooth.inuker.com.grassinvain.controller.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.base.BaseActivity;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.controller.MainActivity;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.model.RequestModel.UserBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class LoginActivity extends BaseActivity {

    private ImageView login_back;
    private MyEditText phonenumber;
    private MyEditText phonepwd;
    private Button login;
    private TextView fastregis;
    private TextView forgetpwd;
    private LinearLayout activityLogin;
    private UserModel userModel;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.Login_back:
                finish();
                break;
            case R.id.login:
                String zhanghao = phonenumber.getText().toString();
                String mima = phonepwd.getText().toString();
                boolean b = TextUtil.checkMobileNO(LoginActivity.this, zhanghao);
                if (b){
                    doLogin(zhanghao,mima);
                }else {
                    showToast("手机号码格式不正确");
                }
                break;
            case R.id.fastregis:
                startActivity(RegisteredActivity.class);
                break;
            case R.id.forgetpwd:

                startActivity(ForgetPwdActivity.class);
                break;
            case R.id.activity_login:
                TextUtil.setdissmiss(getBaseContext());
                break;
        }
    }

    private void doLogin(String zhanghao, String mima) {


        UserBody userBody = new UserBody();
        userBody.userMobile = zhanghao;
        userBody.userPwd = mima;
        userModel.login(userBody, new Callback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {

                Toast.makeText(getBaseContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                startActivity(MainActivity.class);
            }

            @Override
            public void onFailure(int resultCode, String message) {

                Toast.makeText(getBaseContext(),message,Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void initParms(Bundle parms) {
    }
    @Override
    public View bindView() {
        return null;
    }
    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        login_back = (ImageView) view.findViewById(R.id.Login_back);
        phonenumber = (MyEditText) view.findViewById(R.id.phonenumber);
        phonepwd = (MyEditText) view.findViewById(R.id.phonepwd);
        login = (Button) view.findViewById(R.id.login);
        fastregis = (TextView) view.findViewById(R.id.fastregis);
        forgetpwd = (TextView) view.findViewById(R.id.forgetpwd);
        activityLogin = (LinearLayout) view.findViewById(R.id.activity_login);
    }

    @Override
    public void setListener() {
        login_back.setOnClickListener(this);
        phonenumber.setOnClickListener(this);
        phonepwd.setOnClickListener(this);
        login.setOnClickListener(this);
        fastregis.setOnClickListener(this);
        forgetpwd.setOnClickListener(this);
        activityLogin.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        userModel = new UserModel(this);
    }


    public void setdissmiss(){

    }
}
