package bluetooth.inuker.com.grassinvain.network.http.subscriber;

import java.io.InterruptedIOException;
import java.net.ConnectException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class SubAPICallback<T> extends Subscriber<T> {
    private final String unknownMsg = "网络不给力1";

    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        if (e instanceof InterruptedIOException || e instanceof ConnectException) {
            onError(new ApiException(ApiException.ErrorType.TIMEOUT, e.getMessage()));
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            onError(new ApiException(ApiException.ErrorType.SERVER, httpException.code(), httpException.message()));
        } else if (e instanceof ApiException) {    //服务器返回的错误
            onError((ApiException) e);
        } else {
            onError(new ApiException(ApiException.ErrorType.IO, unknownMsg));
        }
        e.printStackTrace();
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);

    @Override
    public void onCompleted() {

    }


}