package bluetooth.inuker.com.grassinvain.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import bluetooth.inuker.com.grassinvain.R;


/**
 * Create custom Dialog windows for your application Custom dialogs rely on
 * custom layouts wich allow you to create and use your own look & feel.
 * <p/>
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 *
 * @author antoine vianey
 */
public class ProgressDialog extends Dialog implements DialogInterface.OnCancelListener {
    private Context context;
    private boolean hasCancel;
    private CancelProgressListener cancelProgressListener;

    public ProgressDialog(Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.common_progress_dialog);
        setCanceledOnTouchOutside(false);
        this.setOnCancelListener(this);

    }

    public void setCancelProgressListener(CancelProgressListener cancelProgressListener) {
        this.cancelProgressListener = cancelProgressListener;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (cancelProgressListener != null) {
            cancelProgressListener.onCancelProgress();
        }

    }


    public interface CancelProgressListener {
        void onCancelProgress();
    }
}