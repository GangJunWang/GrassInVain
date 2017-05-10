package bluetooth.inuker.com.grassinvain.controller.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.other.SelectPicActivity;
import bluetooth.inuker.com.grassinvain.common.util.CommonUtil;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;

/**
 * Created by 1 on 2017/3/23.
 */

public class RegisFragmentthree extends Fragment implements View.OnClickListener {

    private View view;
    private MyEditText personIdCard;
    private String personIdCardString, IdCardzXhengmianString, IdCardFanmianString;
    private ImageView idCardZhengmiam;
    private ImageView idCardFanmiam;

    private String imagePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.regis_activitythree, null);
        initView();
        return view;
    }

    private void initView() {
        personIdCard = (MyEditText) view.findViewById(R.id.person_id_card);
        idCardZhengmiam = (ImageView) view.findViewById(R.id.id_card_zhengmian);
        idCardZhengmiam.setOnClickListener(this);
        idCardFanmiam = (ImageView) view.findViewById(R.id.id_card_fanmian);
        idCardFanmiam.setOnClickListener(this);
        personIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                personIdCardString = personIdCard.getText().toString();
            }
        });

    }

    public String getPersonIdCardString() {
        return personIdCardString;
    }



    Boolean flag = true;
    public  void  setImageIdcard(String s){
        if (flag){
            idCardZhengmiam.setImageURI(Uri.parse(s));
            flag = false;

        }else {

            idCardFanmiam.setImageURI(Uri.parse(s));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_card_zhengmian:
                CommonUtil.choicePicture(getActivity(), SelectPicActivity.class, 1);
                break;
            case R.id.id_card_fanmian:
                CommonUtil.choicePicture(getActivity(), SelectPicActivity.class, 1);
                break;
        }
    }
}
