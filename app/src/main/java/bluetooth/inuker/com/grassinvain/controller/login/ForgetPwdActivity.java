package bluetooth.inuker.com.grassinvain.controller.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.common.util.ToastUtil;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.common.widget.MySuccessDialog;
import bluetooth.inuker.com.grassinvain.network.model.RequestModel.UserBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class ForgetPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private MyEditText forgetPonemunber;
    private MyEditText forgetYanzhegnma;
    private TextView forgetSendYanzhegnma;
    private MyEditText forgetFistpwd;
    private MyEditText forgetSecond;
    private Button forgetSubmit;
    private Context context;

    private int time = 60;
    private LinearLayout activityForget;
    private ImageView forgetBack;
    private UserModel userModel;


    //获取短信验证码接口返回
    private String smsCaptchaToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        userModel = new UserModel(this);

        initView();
    }

    private void initView() {
        forgetPonemunber = (MyEditText) findViewById(R.id.forget_phonenumber);
        forgetYanzhegnma = (MyEditText) findViewById(R.id.forget_yanzhegnma);
        forgetSendYanzhegnma = (TextView) findViewById(R.id.forget_send_yanzhegnma);
        forgetFistpwd = (MyEditText) findViewById(R.id.forget_firstpwd);
        forgetSecond = (MyEditText) findViewById(R.id.forget_secondpwd);
        forgetSubmit = (Button) findViewById(R.id.forget_submit);
        forgetSendYanzhegnma.setOnClickListener(this);
        forgetSubmit.setOnClickListener(this);
        activityForget = (LinearLayout) findViewById(R.id.activity_forget);
        forgetBack = (ImageView) findViewById(R.id.forget_back);
        forgetBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.forget_back:
                finish();
                break;
            case R.id.forget_send_yanzhegnma:
                if (!"".equals(forgetPonemunber.getText().toString())) {
                    countdown();
                    UserBody userBody = new UserBody();
                    userBody.userMobile = forgetPonemunber.getText().toString();
                    userBody.op = MConstants.SMSCAPTCHA_OP_FORGET;
                    userBody.type = "0";
                    userModel.smsCaptcha(userBody, new Callback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            smsCaptchaToken = s;
                        }

                        @Override
                        public void onFailure(int resultCode, String message) {

                        }
                    });
                } else {
                    ToastUtil.show(ForgetPwdActivity.this, "请输入手机号");
                }
                break;

            case R.id.forget_submit:

                if (!"".equals(forgetPonemunber.getText().toString()) && !"".equals(forgetYanzhegnma.getText().toString())) {

                    boolean b = TextUtil.checkTwicePasswordIsEqual(ForgetPwdActivity.this, forgetFistpwd.getText().toString(), forgetSecond.getText().toString());
                    if (b) {


                        UserBody userBody = new UserBody();
                        userBody.userMobile = forgetPonemunber.getText().toString();
                        userBody.captcha = forgetYanzhegnma.getText().toString();
                        userBody.userPwd = forgetFistpwd.getText().toString();
                        userBody.confirmPwd = forgetSecond.getText().toString();
                        userBody.captchaToken = smsCaptchaToken;
                        userBody.op = MConstants.SMSCAPTCHA_OP_REGISTER;
                        userBody.type = "0";
                        userModel.restPwd(userBody, new Callback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                perform();
                                finish();
                            }

                            @Override
                            public void onFailure(int resultCode, String message) {

                            }
                        });


                    } else {
                        ToastUtil.show(ForgetPwdActivity.this, "两次输入的密码不一致");
                    }
                } else {
                    ToastUtil.show(ForgetPwdActivity.this, "请补充完整信息");
                }

                break;
            case R.id.activity_forget:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                break;
        }
    }

    private void perform() {

        MySuccessDialog mySuccessDialog = new MySuccessDialog(ForgetPwdActivity.this, R.style.Dialog);
        mySuccessDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ForgetPwdActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);


    }

    /**
     * 倒计时
     */

    void countdown() {
        timer.start();
    }

    int count = 59;
    CountDownTimer timer = new CountDownTimer(time * 1000 + time, 1000) {
        @Override
        public void onTick(long l) {
            forgetSendYanzhegnma.setEnabled(false);
            forgetSendYanzhegnma.setText(count + "");
            count--;
        }

        @Override
        public void onFinish() {
            count = 59;
            forgetSendYanzhegnma.setText("获取验证码");
            forgetSendYanzhegnma.setEnabled(true);
        }
    };
}
