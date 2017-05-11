package bluetooth.inuker.com.grassinvain.network.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shaojun.utils.log.Logger;

import java.util.concurrent.TimeUnit;

import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import bluetooth.inuker.com.grassinvain.network.http.converter.GsonConverterFactory;
import bluetooth.inuker.com.grassinvain.network.service.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * retrofit管理类
 */
public class RetrofitManager {

    private Context mContext;
    private static String API_HOST = MConstants.BASE_URL;// 服务器地址
    private static Retrofit retrofit;
    private static Retrofit testRetrofit;
    private static ApiService apiService;//api

    private static class RetrofitManagerHolder {
        static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private RetrofitManager() {

    }

    public static RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        initRetrofit();
    }

    public void initRetrofit() {
        if (mContext == null) {
            Logger.e("mContext is null , you need call init(Context context)!");
            return;
        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(MConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(MConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor(mContext))
                .addInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ssZ")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 接口
     *
     * @return
     */
    public static ApiService getUserService() {
        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

}
