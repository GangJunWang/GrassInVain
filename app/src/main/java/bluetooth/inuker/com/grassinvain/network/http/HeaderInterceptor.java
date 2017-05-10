package bluetooth.inuker.com.grassinvain.network.http;

import android.content.Context;
import android.text.TextUtils;

import com.shaojun.utils.log.Logger;

import java.io.IOException;

import bluetooth.inuker.com.grassinvain.common.cache.CacheCenter;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * header拦截器{处理token}
 */
public class HeaderInterceptor implements Interceptor {
    private Context context;

    public HeaderInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request newRequest;

        String tokenOld = CacheCenter.getInstance().getUserToken();
        String wytoken = CacheCenter.getInstance().getNimToken();

        if (!TextUtils.isEmpty(tokenOld) && !TextUtils.isEmpty(wytoken)) {
            Logger.d("token:" + tokenOld);
            //统一设置请求Header
            newRequest = chain.request().newBuilder()
                    .addHeader(MConstants.KEY_USER_TOKEN, tokenOld)
                    .addHeader(MConstants.KEY_USER_NIM_TOKEN, wytoken)
                    .build();
        } else if (!TextUtils.isEmpty(tokenOld)) {
            newRequest = chain.request().newBuilder()
                    .addHeader(MConstants.KEY_USER_TOKEN, tokenOld)
                    .build();
        } else if (!TextUtils.isEmpty(wytoken)) {
            newRequest = chain.request().newBuilder()
                    .addHeader(MConstants.KEY_USER_NIM_TOKEN, wytoken)
                    .build();
        } else {
            Logger.d("user token为空");
            newRequest = chain.request().newBuilder()
                    .build();
        }

        okhttp3.Response response = null;
        try {
            response = chain.proceed(newRequest);
            String tokenNew = response.header(MConstants.KEY_USER_TOKEN);
            String wytokenNew = response.header(MConstants.KEY_USER_NIM_TOKEN);
            if (!TextUtils.isEmpty(tokenNew)) {
                Logger.d("获取到新的token:" + tokenNew);
                CacheCenter.getInstance().setUserToken(tokenNew);
            }
            if (!TextUtils.isEmpty(wytokenNew)) {
                Logger.d("获取到新的云信wytoken:" + wytokenNew);
                CacheCenter.getInstance().setNimToken(wytokenNew);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;

    }
}