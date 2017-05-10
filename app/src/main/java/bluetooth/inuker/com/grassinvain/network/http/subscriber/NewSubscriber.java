package bluetooth.inuker.com.grassinvain.network.http.subscriber;

import android.content.Context;

import com.shaojun.utils.log.Logger;

import bluetooth.inuker.com.grassinvain.common.dialog.ProgressDialog;


/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己统一对请求数据进行处理
 * Created by liukun on 16/3/10.
 */
public class NewSubscriber<T> extends SubAPICallback<T> implements ProgressDialog.CancelProgressListener {
    //出错提示
    private final String NETWORK_MSG = "网络不给力";
    private final String TIMEOUT_MSG = "网络不给力";
    private final String PARSE_MSG = "数据解析异常";
    private ProgressDialog progressDialog;
    private Context context;
    private boolean hasProgress = true;

    public NewSubscriber(Context context, boolean hasProgress) {
        this.context = context;
        this.hasProgress = hasProgress;
    }


    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelProgressListener(this);
        }
        if (hasProgress) {
            progressDialog.show();
        }
    }

    /**
     * 取消ProgressView
     */
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgress();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgress();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public final void onError(Throwable e) {
        dismissProgress();
        super.onError(e);
    }

    @Override
    public void onNext(T t) {
    }

    //Http异常处理
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;

    //针对详细错误定义的实现方法
    @Override
    protected void onError(ApiException ex) {
        String errorMsg = ex.getErrMessage();
        switch (ex.errorType) {
            case ApiException.ErrorType.IO:
            case ApiException.ErrorType.TIMEOUT:

                break;
            case ApiException.ErrorType.PARSE_JSON:

                break;
            case ApiException.ErrorType.SERVER:
                if (ex.getCode() == UNAUTHORIZED || ex.getCode() == FORBIDDEN) {
                    //登陆失效
                    onPermissionError();
                } else {

                }
                break;
            case ApiException.ErrorType.MANUAL:

                break;
            default:
                break;
        }
    }

    //没权限处理401、403Http错误码，重新登录
    protected final void onPermissionError() {
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intent);
//        //先判断避免崩溃，结束当前调用失败接口的activity，不需要关闭可以去掉
//        if (context instanceof BaseActivity) {
//            ((BaseActivity) context).finish();
//        }
    }


    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            Logger.i("解除订阅，请求取消!");
            this.unsubscribe();
        }
    }
}