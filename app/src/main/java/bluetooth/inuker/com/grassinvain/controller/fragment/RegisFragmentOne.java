package bluetooth.inuker.com.grassinvain.controller.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.network.model.RequestModel.UserBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by 1 on 2017/3/23.
 */

public class RegisFragmentOne extends Fragment {

    private View view;
    private MyEditText tuijianren, zijiren, yanzhegnma;
    private String tuijianrenString, zijirenString, yanzhengmaString;
    private int time = 60;
    private TextView sendYanzhengma;


    //获取短信验证码接口返回
    public String smsCaptchaToken = "";
    private UserModel userModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.regis_activity, null);
        userModel = new UserModel(getActivity());
        initView();
        return view;

    }

    private void initView() {
        tuijianren = (MyEditText) view.findViewById(R.id.tuiijanren);
        zijiren = (MyEditText) view.findViewById(R.id.zijiren);
        yanzhegnma = (MyEditText) view.findViewById(R.id.yanzhegnma);

        sendYanzhengma = (TextView) view.findViewById(R.id.send_yanzhegnma);
        sendYanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!"".equals(tuijianren.getText().toString()) && !"".equals(zijiren.getText().toString())) {


                    if (TextUtil.checkMobileNO(getActivity(), zijiren.getText().toString())) {
                        countdown(); // 倒计时
                        UserBody userBody = new UserBody();
                        userBody.userMobile = zijiren.getText().toString();
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
                        Toast.makeText(getActivity(), "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "请补充完整数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tuijianren.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tuijianrenString = tuijianren.getText().toString();
            }
        });
        zijiren.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                zijirenString = zijiren.getText().toString();
            }
        });

        yanzhegnma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                yanzhengmaString = yanzhegnma.getText().toString();
            }
        });
    }

    public String getTuijianrenString() {
        return tuijianrenString;
    }

    public String getZijirenString() {
        return zijirenString;
    }

    public String getYanzhengmaString() {
        return yanzhengmaString;
    }

    public String getSmsCaptchaToken() {
        return smsCaptchaToken;
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
            sendYanzhengma.setEnabled(false);
            sendYanzhengma.setText(count + "");
            count--;
        }

        @Override
        public void onFinish() {
            count = 59;
            sendYanzhengma.setText("发送");
            sendYanzhengma.setEnabled(true);
        }
    };
}
