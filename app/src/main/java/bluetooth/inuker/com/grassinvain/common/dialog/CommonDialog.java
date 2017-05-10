package bluetooth.inuker.com.grassinvain.common.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bluetooth.inuker.com.grassinvain.R;

/**
 * 对话框
 */
@SuppressLint("InflateParams")
public class CommonDialog extends Dialog {

    private View dialogView;
    private CommonDialog commonDialog = null;
    private Button b_dialog_close, b_dialog_ok;
    private TextView tv_dialog_title, tv_dialog_message;

    public CommonDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog createDialog(Context context) {
        if (commonDialog == null) {
            commonDialog = new CommonDialog(context, R.style.CommonDialog);
            dialogView = LayoutInflater.from(context).inflate(R.layout.common_dialog, null, false);
            b_dialog_close = (Button) dialogView.findViewById(R.id.b_dialog_close);
            b_dialog_ok = (Button) dialogView.findViewById(R.id.b_dialog_ok);
            tv_dialog_message = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
            tv_dialog_title = (TextView) dialogView.findViewById(R.id.tv_dialog_title);
            commonDialog.setContentView(dialogView);
            commonDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            commonDialog.setCanceledOnTouchOutside(false);
            b_dialog_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commonDialog.dismiss();
                    if (onViewClickListener != null) {
                        onViewClickListener.ok(v);
                    }
                }
            });
            b_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commonDialog.dismiss();
                    if (onViewClickListener != null) {
                        onViewClickListener.close(v);
                    }
                }
            });
        }
        return this;
    }

    public CommonDialog setTitle(String title) {
        tv_dialog_title.setText(title);
        tv_dialog_title.setVisibility(View.VISIBLE);
        return this;
    }

    public CommonDialog setMessage(String message) {
        tv_dialog_message.setText(message);
        tv_dialog_message.setVisibility(View.VISIBLE);
        return this;
    }

    public void dismissCancle() {
        commonDialog.dismiss();
    }

    public void showDig() {
        commonDialog.show();
    }

    private OnViewClickListener onViewClickListener;

    public CommonDialog setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
        return this;
    }

    public interface OnViewClickListener {
        public void close(View view);

        public void ok(View view);
    }

}