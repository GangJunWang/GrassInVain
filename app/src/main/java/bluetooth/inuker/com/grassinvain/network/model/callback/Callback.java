package bluetooth.inuker.com.grassinvain.network.model.callback;

/**
 * Created by qinwei on 16/7/26 下午3:26
 */
public interface Callback<T> {

    void onSuccess(T t);

    void onFailure(int resultCode, String message);
}
