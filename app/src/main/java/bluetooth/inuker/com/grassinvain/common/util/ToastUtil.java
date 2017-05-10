package bluetooth.inuker.com.grassinvain.common.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by 1 on 2017/3/22.
 */

public class ToastUtil {

    private static Toast toast;
    private final static int duration = 60;


    public static void show(Context context, String info) {
        if (toast == null) {
            toast = Toast.makeText(context, info, duration);
        }else {
            toast.setText(info);
        }
        toast.setGravity( Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void show(Context context, int info) {
        if (toast == null) {
            toast = Toast.makeText(context, info, duration);
        }else {
            toast.setText(info);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
