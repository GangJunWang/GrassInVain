package bluetooth.inuker.com.grassinvain.common.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by 1 on 2016/11/24.
 */
public class ZizhuHandler extends Handler {
    WeakReference<Activity> mWeakReference;
    public ZizhuHandler(Activity activity) {
        mWeakReference=new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        final Activity activity = mWeakReference.get();
        if(activity != null && onHandlerListener != null){
            onHandlerListener.handler(msg);
        }
    }

    private OnHandlerListener onHandlerListener;

    public void setOnHandlerListener(OnHandlerListener onHandlerListener) {
        this.onHandlerListener = onHandlerListener;
    }

    public interface OnHandlerListener{
        void handler(Message msg);
    }

}
