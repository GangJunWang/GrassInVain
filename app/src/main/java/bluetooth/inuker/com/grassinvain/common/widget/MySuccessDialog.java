package bluetooth.inuker.com.grassinvain.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import bluetooth.inuker.com.grassinvain.R;

/**
 * Created by 1 on 2017/3/29.
 */

public class MySuccessDialog extends Dialog {

    private Context context;


    public MySuccessDialog(Context context) {
        super(context);
        this.context = context;
    }

    public MySuccessDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_toast_succose);
    }
}

    ;

