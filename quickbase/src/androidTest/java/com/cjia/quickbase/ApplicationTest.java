package com.cjia.quickbase;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}

////基础图片缓存框架
//public class TestApplication extends Application(){
//        public void onCreate(){
//        super.onCreate();
////        创建默认的ImageLoader配置参数
//        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
////        Initialize ImageLoader with configuration
//        ImageLoader.getInstance().init(configuration);
//        }
//        }
//
////RxJava
//Observable.from(folders).flatMap(new Func1<File,Observable<Flie>>){
//
//        }