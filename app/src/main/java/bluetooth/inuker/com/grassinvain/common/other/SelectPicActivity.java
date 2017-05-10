package bluetooth.inuker.com.grassinvain.common.other;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.shaojun.utils.log.Logger;

import java.io.File;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.CommonUtil;
import bluetooth.inuker.com.grassinvain.common.util.FileHelper;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import bluetooth.inuker.com.grassinvain.common.util.SDCardTools;

public class SelectPicActivity extends Activity implements OnClickListener {

    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private LinearLayout layout;
    private Intent intent;
    private Animation animationIn;
    private Animation animationOut;
    private int minetype;
    private int imageSize;

//    private File tempIconFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CommonUtil.setBarImmersive(this);
        minetype = getIntent().getIntExtra("mime_type", -1);
        imageSize = getIntent().getIntExtra("image_size", -1);
        setContentView(R.layout.activity_common_choice_pic_model);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        initWidget();
        bindEven();
    }


    protected void initWidget() {
        intent = getIntent();
        btn_take_photo = (Button) this.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (Button) this.findViewById(R.id.btn_pick_photo);
        btn_cancel = (Button) this.findViewById(R.id.btn_cancel);
        layout = (LinearLayout) findViewById(R.id.pop_layout);
    }

    protected void bindEven() {
        // 添加按钮监听
        btn_cancel.setOnClickListener(this);
        btn_pick_photo.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
        // 添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
        layout.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void setView() {
        animationIn = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        layout.setAnimation(animationIn);
        layout.setVisibility(View.VISIBLE);
    }

    // 实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        toFinish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            toFinish();
            return;
        }

        if (requestCode == MConstants.PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                intent.setData(uri);
                CommonUtil.crop(SelectPicActivity.this, uri, imageSize);
                toFinish();
            }
        } else if (requestCode == MConstants.PHOTO_REQUEST_CAMERA) {
            // 从相机返回的数据
            if (SDCardTools.isSDCardExist()) {
                Uri uri = Uri.fromFile(new File(FileHelper.getAppCachePath(),
                        MConstants.PHOTO_FILE_NAME));
                intent.setData(uri);
                setResult(RESULT_OK, intent);
//                if (imageSize < 0) {
//                    setResult(RESULT_OK, intent);
//                } else {
//                    CommonUtil.crop(SelectPicActivity.this, uri, imageSize);
//                }
            } else {
              //  ToastUtils.show("未找到存储卡，无法存储照片！");
            }
            toFinish();
        } else if (requestCode == MConstants.PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {

//                Bitmap bitmap = data.getParcelableExtra("data");

                if (data.getExtras() != null) {
                    intent.putExtras(data.getExtras());

                }
                if (data.getData() != null) {
                    Logger.d("裁剪后的uri" + data.getData().toString());
                    intent.setData(data.getData());
                }
                setResult(RESULT_OK, intent);

                toFinish();
            } else {

            }
        } else if (requestCode == MConstants.FILE_REQUEST_SELECT || requestCode == MConstants.PHOTO_REQUEST_SELECT) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                intent.setData(uri);
                setResult(RESULT_OK, intent);
            }
            toFinish();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
                try {
                    // 激活相机
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 判断存储卡是否可以用，可用进行存储
                    if (SDCardTools.isSDCardExist()) {
                        File tempFile = new File(FileHelper.getAppCachePath(),
                                MConstants.PHOTO_FILE_NAME);
                        // 从文件中创建uri
                        Uri uri = Uri.fromFile(tempFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    }
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                    startActivityForResult(intent, MConstants.PHOTO_REQUEST_CAMERA);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_pick_photo:
                try {
                    //选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
                    //有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
                    if (minetype == 1) {
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*");
                        startActivityForResult(intent, MConstants.FILE_REQUEST_SELECT);
                    } else if (minetype == 2) {
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, MConstants.PHOTO_REQUEST_GALLERY);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, MConstants.PHOTO_REQUEST_SELECT);
                    }
//                    intent.setAction(Intent.ACTION_GET_CONTENT);//选择文件
//                    intent.setAction(Intent.ACTION_PICK);//系统相册
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_cancel:
                toFinish();
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toFinish();
    }

    private void toFinish() {
        animationOut = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
        layout.setAnimation(animationOut);
        layout.setVisibility(View.GONE);
        finish();
        overridePendingTransition(0, 0);
    }


}
