package bluetooth.inuker.com.grassinvain.common.aliyun;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.shaojun.utils.log.Logger;

import java.io.File;
import java.util.HashMap;

/**
 * Created by oss on 2015/12/7 0007.
 * 支持普通上传，普通下载和断点上传
 */
public class OssUploadService {


    private OSS oss;
    private String bucket;
    private UIDisplayer UIDisplayer;
    private MultiPartUploadManager multiPartUploadManager;
    private String callbackAddress;
    //根据实际需求改变分片大小
    private final static int partSize = 256 * 1024;

    private UploadListener uploadListener;

    public static interface UploadListener {
        void onUploadSuccess(PutObjectRequest request, PutObjectResult result);

        void onUploadFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException);

        void onUploadProgress(PutObjectRequest request, long currentSize, long totalSize);
    }

    public void setUploadListener(UploadListener uploadListener) {
        this.uploadListener = uploadListener;
    }

    public OssUploadService(OSS oss, String bucket) {
        this.oss = oss;
        this.bucket = bucket;
    }

    public OssUploadService(OSS oss, String bucket, UIDisplayer UIDisplayer) {
        this.oss = oss;
        this.bucket = bucket;
        this.UIDisplayer = UIDisplayer;
        this.multiPartUploadManager = new MultiPartUploadManager(oss, bucket, partSize, UIDisplayer);
    }


    public void SetBucketName(String bucket) {
        this.bucket = bucket;
    }

    public void InitOss(OSS _oss) {
        this.oss = _oss;
    }

    public void setCallbackAddress(String callbackAddress) {
        this.callbackAddress = callbackAddress;
    }


    public OSSAsyncTask asyncPutFile(String object, String localFile) {
        if (object.equals("")) {
            Logger.w("AsyncPutImage", "ObjectNull");
            return null;
        }

        File file = new File(localFile);
        if (!file.exists()) {
            Logger.w("AsyncPutImage", "FileNotExist");
            Logger.w("LocalFile", localFile);
            return null;
        }

        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, object, localFile);

        if (callbackAddress != null) {
            // 传入对应的上传回调参数，这里默认使用OSS提供的公共测试回调服务器地址
            put.setCallbackParam(new HashMap<String, String>() {
                {
                    put("callbackUrl", callbackAddress);
                    //callbackBody可以自定义传入的信息
                    put("callbackBody", "filename=${object}");
                }
            });
        }

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                if (uploadListener != null) {
                    uploadListener.onUploadProgress(request, currentSize, totalSize);
                }
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                if (uploadListener != null) {
                    uploadListener.onUploadSuccess(request, result);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                String info = "";
//                // 请求异常
//                if (clientExcepion != null) {
//                    // 本地异常如网络异常等
//                    clientExcepion.printStackTrace();
//                    info = clientExcepion.toString();
//                }
//                if (serviceException != null) {
//                    // 服务异常
//                    Logger.e("ErrorCode", serviceException.getErrorCode());
//                    Logger.e("RequestId", serviceException.getRequestId());
//                    Logger.e("HostId", serviceException.getHostId());
//                    Logger.e("RawMessage", serviceException.getRawMessage());
//                    info = serviceException.toString();
//                }
                if (uploadListener != null) {
                    uploadListener.onUploadFailure(request, clientExcepion, serviceException);
                }
            }
        });

        return task;
    }

    public OSSAsyncTask asyncPutFileWithPro(String object, String localFile) {
        if (object.equals("")) {
            Logger.w("AsyncPutImage", "ObjectNull");
            return null;
        }

        File file = new File(localFile);
        if (!file.exists()) {
            Logger.w("AsyncPutImage", "FileNotExist");
            Logger.w("LocalFile", localFile);
            return null;
        }


        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, object, localFile);

        if (callbackAddress != null) {
            // 传入对应的上传回调参数，这里默认使用OSS提供的公共测试回调服务器地址
            put.setCallbackParam(new HashMap<String, String>() {
                {
                    put("callbackUrl", callbackAddress);
                    //callbackBody可以自定义传入的信息
                    put("callbackBody", "filename=${object}");
                }
            });
        }

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                if (uploadListener != null) {
                    uploadListener.onUploadProgress(request, currentSize, totalSize);
                }
                if (UIDisplayer != null) {
                    int progress = (int) (100 * currentSize / totalSize);
                    UIDisplayer.updateProgress(progress);
                    UIDisplayer.displayInfo("上传进度: " + String.valueOf(progress) + "%");
                }
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                if (uploadListener != null) {
                    uploadListener.onUploadSuccess(request, result);
                }
                if (UIDisplayer != null) {
                    UIDisplayer.uploadComplete();
                    UIDisplayer.displayInfo("Bucket: " + bucket
                            + "\nObject: " + request.getObjectKey()
                            + "\nETag: " + result.getETag()
                            + "\nRequestId: " + result.getRequestId()
                            + "\nCallback: " + result.getServerCallbackReturnBody());
                }

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                if (uploadListener != null) {
                    uploadListener.onUploadFailure(request, clientExcepion, serviceException);
                }

                String info = "";
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    Logger.e("ErrorCode", serviceException.getErrorCode());
                    Logger.e("RequestId", serviceException.getRequestId());
                    Logger.e("HostId", serviceException.getHostId());
                    Logger.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
                if (UIDisplayer != null) {
                    UIDisplayer.uploadFail(info);
                    UIDisplayer.displayInfo(info);
                }
            }
        });
        return task;

    }


    //断点上传，返回的task可以用来暂停任务
    public PauseableUploadTask asyncMultiPartUpload(String object, String localFile) {
        if (object.equals("")) {
            Logger.w("AsyncMultiPartUpload", "ObjectNull");
            return null;
        }

        File file = new File(localFile);
        if (!file.exists()) {
            Logger.w("AsyncMultiPartUpload", "FileNotExist");
            Logger.w("LocalFile", localFile);
            return null;
        }

        Logger.d("MultiPartUpload", localFile);
        PauseableUploadTask task = multiPartUploadManager.asyncUpload(object, localFile);
        return task;
    }

}
