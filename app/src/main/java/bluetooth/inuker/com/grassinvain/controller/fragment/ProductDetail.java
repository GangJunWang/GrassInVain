package bluetooth.inuker.com.grassinvain.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bluetooth.inuker.com.grassinvain.R;

/**
 * Created by 1 on 2017/3/31.
 */

public class ProductDetail extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_produec_detail, null);
        initView();
        return view;

    }
    private void initView() {

    }
}
