package bluetooth.inuker.com.grassinvain.controller;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

import bluetooth.inuker.com.grassinvain.common.cache.CacheCenter;
import bluetooth.inuker.com.grassinvain.common.cache.ImageLoaderKit;
import bluetooth.inuker.com.grassinvain.network.http.RetrofitManager;

/**
 * Created by 1 on 2017/3/22.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        CacheCenter.getInstance().init(myApplication);
        // 初始化网络管理类
        //网络请求管理
        RetrofitManager.getInstance().init(this);
        //图片框架
        ImageLoaderKit.getInstance().init(myApplication);
        /**
         * XUtil3
         */
        x.Ext.init(this);

    }

    public static DbManager getTou() {
        DbManager.DaoConfig config = new DbManager.DaoConfig();
        config.setDbName("touxiang.db");
        config.setDbDir(new File("/sdcard/Music"));
        config.setDbVersion(1);
        DbManager db = x.getDb(config);
        return db;
    }



}
