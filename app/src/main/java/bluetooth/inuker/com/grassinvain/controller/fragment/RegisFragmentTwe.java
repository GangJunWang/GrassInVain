package bluetooth.inuker.com.grassinvain.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;

/**
 * Created by 1 on 2017/3/23.
 */

public class RegisFragmentTwe extends Fragment {

    private View view;
    private MyEditText shurumima;
    private MyEditText querenmima;
    private String shurumimaString, querenmimaString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.regis_activitytwe, null);
        initView();
        return view;
    }

    private void initView() {

        shurumima = (MyEditText) view.findViewById(R.id.shurumima);
        querenmima = (MyEditText) view.findViewById(R.id.querenmima);
        shurumima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                shurumimaString = shurumima.getText().toString().trim();
            }
        });
        querenmima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                querenmimaString = querenmima.getText().toString().trim();
            }
        });
    }

    public String getShurumimaString(){
        return shurumimaString;
    }
    public String getQuerenmimaString(){
        return querenmimaString;
    }
}
