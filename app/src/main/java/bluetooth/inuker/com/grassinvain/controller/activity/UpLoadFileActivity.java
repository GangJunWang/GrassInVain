package bluetooth.inuker.com.grassinvain.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.aliyun.Oss;
import bluetooth.inuker.com.grassinvain.common.aliyun.OssUploadService;
import bluetooth.inuker.com.grassinvain.common.aliyun.UIDisplayer;
import bluetooth.inuker.com.grassinvain.common.dialog.ProgressDialog;
import bluetooth.inuker.com.grassinvain.common.model.OssAuth;
import bluetooth.inuker.com.grassinvain.common.util.CommonUtil;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;

public class UpLoadFileActivity extends AppCompatActivity {

    public static String KEY_OSSAUTH = "ossAuth";
    public static String KEY_FILEPATH = "filePath";
    public static String KEY_SHOW_UPLOAD_PROGRESS = "showUploadProgress";//是否显示下载进度
    public static String KEY_RESULT_URL = "resultUrl";//上传成功回传的地址

    private OssAuth ossAuth;//阿里云认证
    private String filePath;//上传文件路径
    private boolean isShowProgress = false; //显示进度还是显示弹出框

    private RelativeLayout uploadRootview;
    public static ProgressBar progressBar;

    private TextView uploadInfo;
    private ImageView uploadClose;
    private String objectKey = "";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtil.setBarImmersive(this);
        setContentView(R.layout.activity_common_upload);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ossAuth = (OssAuth) getIntent().getSerializableExtra(KEY_OSSAUTH);
        filePath = getIntent().getStringExtra(KEY_FILEPATH);
        isShowProgress = getIntent().getBooleanExtra(KEY_SHOW_UPLOAD_PROGRESS, false);
        progressDialog = new ProgressDialog(this);

        initWidget();
        bindEven();
        setView();

    }


    protected void initWidget() {
        uploadRootview = (RelativeLayout) findViewById(R.id.upload_rootview);
        if(progressBar==null) {
            progressBar = (ProgressBar) findViewById(R.id.upload_progressbar);
        }
        uploadInfo = (TextView) findViewById(R.id.upload_text);
        uploadClose = (ImageView) findViewById(R.id.upload_close);
    }

    protected void bindEven() {
        uploadClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFinish();
            }
        });
    }

    protected void setView() {
        //显示进度还是显示弹出框
        if (isShowProgress) {
            uploadRootview.setVisibility(View.VISIBLE);
            OssUploadService ossService = Oss.initOSS(this, ossAuth, new UIDisplayer(null, progressBar, uploadInfo, this));
            objectKey = ossAuth.filePath + "/" + CommonUtil.getUUIDMediaName(MConstants.UPLOAD_FILE_TYPE_PICTURE);
            ossService.asyncPutFileWithPro(objectKey, filePath);
        } else {
            uploadRootview.setVisibility(View.GONE);
            OssUploadService ossService = Oss.initOSS(this, ossAuth, new UIDisplayer(null, progressBar, uploadInfo, this));
            objectKey = ossAuth.filePath + "/" + CommonUtil.getUUIDMediaName(MConstants.UPLOAD_FILE_TYPE_PICTURE);
            ossService.setUploadListener(new OssUploadService.UploadListener() {
                @Override
                public void onUploadSuccess(PutObjectRequest request, PutObjectResult result) {
                    progressDialog.dismiss();
                    //实际可以访问的地址为域名+objectKey
                    String url = ossAuth.domainUrl + "/" + objectKey;
                    // 实例化 Bundle，设置需要传递的参数
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_RESULT_URL, url);
                    setResult(RESULT_OK, getIntent().putExtras(bundle));
                    toFinish();
                }

                @Override
                public void onUploadFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                    progressDialog.dismiss();
                }

                @Override
                public void onUploadProgress(PutObjectRequest request, long currentSize, long totalSize) {
                    progressBar.setProgress((int)(currentSize/totalSize));
                }
            });
            final OSSAsyncTask task = ossService.asyncPutFile(objectKey, filePath);
            progressDialog.setCancelProgressListener(new ProgressDialog.CancelProgressListener() {
                @Override
                public void onCancelProgress() {
                    if (!task.isCompleted()) {
                        task.cancel();
                        Toast.makeText(getBaseContext(),"上传已取消",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            progressDialog.show();

        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toFinish();
    }

    private void toFinish() {
        finish();
        overridePendingTransition(0, 0);
    }
    public static void setProgressBar(ProgressBar progressBar) {
        UpLoadFileActivity.progressBar = progressBar;
    }
}
