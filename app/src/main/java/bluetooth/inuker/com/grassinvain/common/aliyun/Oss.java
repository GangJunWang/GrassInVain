package bluetooth.inuker.com.grassinvain.common.aliyun;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;

import bluetooth.inuker.com.grassinvain.common.model.OssAuth;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:jeremy
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Oss {

    /**
     * 初始化一个OssService用来上传下载
     *
     * @param context
     * @param ossAuth
     * @return
     */
    public static OssUploadService initOSS(Context context, final OssAuth ossAuth) {
        //如果希望直接使用accessKey来访问的时候，可以直接使用OSSPlainTextAKSKCredentialProvider来鉴权。
        //OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
        OSSFederationCredentialProvider ossFederationCredentialProvider = new OSSFederationCredentialProvider() {
            @Override
            public OSSFederationToken getFederationToken() {
                return new OSSFederationToken(ossAuth.credentials.accessKeyId, ossAuth.credentials.accessKeySecret, ossAuth.credentials.securityToken, ossAuth.credentials.expiration);
            }
        };
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(context, ossAuth.endPoint, ossFederationCredentialProvider, conf);
        return new OssUploadService(oss, ossAuth.bucket);
    }

    /**
     * 初始化一个OssService用来上传下载
     *
     * @param context
     * @param ossAuth
     * @param displayer
     * @return
     */
    public static OssUploadService initOSS(Context context, final OssAuth ossAuth, UIDisplayer displayer) {
        //如果希望直接使用accessKey来访问的时候，可以直接使用OSSPlainTextAKSKCredentialProvider来鉴权。
        //OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
        OSSFederationCredentialProvider ossFederationCredentialProvider = new OSSFederationCredentialProvider() {
            @Override
            public OSSFederationToken getFederationToken() {
                return new OSSFederationToken(ossAuth.credentials.accessKeyId, ossAuth.credentials.accessKeySecret, ossAuth.credentials.securityToken, ossAuth.credentials.expiration);
            }
        };
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(context, ossAuth.endPoint, ossFederationCredentialProvider, conf);
        return new OssUploadService(oss, ossAuth.bucket, displayer);
    }
//    /**
//     * 初始化一个OssService用来上传下载
//     *
//     * @param context
//     * @param ossAuth
//     * @param displayer
//     * @return
//     */
//    public static OssService initOSS(Context context, final OssAuth ossAuth, UIDisplayer displayer) {
//        //如果希望直接使用accessKey来访问的时候，可以直接使用OSSPlainTextAKSKCredentialProvider来鉴权。
//        //OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
//        OSSFederationCredentialProvider ossFederationCredentialProvider = new OSSFederationCredentialProvider() {
//            @Override
//            public OSSFederationToken getFederationToken() {
//                return new OSSFederationToken(ossAuth.credentials.accessKeyId, ossAuth.credentials.accessKeySecret, ossAuth.credentials.securityToken, ossAuth.credentials.expiration);
//            }
//        };
//        ClientConfiguration conf = new ClientConfiguration();
//        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
//        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
//        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
//        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
//        OSS oss = new OSSClient(context, ossAuth.endPoint, ossFederationCredentialProvider, conf);
//        return new OssService(oss, ossAuth.bucket, displayer);
//    }

}
