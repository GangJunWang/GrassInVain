package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaojun.widget.superAdapter.OnItemClickListener;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.model.ImageModel;
import bluetooth.inuker.com.grassinvain.common.model.OssAuth;
import bluetooth.inuker.com.grassinvain.common.other.SelectPicActivity;
import bluetooth.inuker.com.grassinvain.common.util.CommonUtil;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.controller.adapter.FeedBackAdapter;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class EvaluationGoods extends AppCompatActivity implements View.OnClickListener {

    private List<Object> data = new ArrayList<>();
    // 问题图片的实体类
    private List<ImageModel> imageModels = new ArrayList<ImageModel>();
    private RecyclerView evaluation_recycView;
    private MyEditText myEditText;
    private RecyclerView request_submit_recycView;
    private TextView tiJiao;
    private FeedBackAdapter feedBackAdapter;
    private UserModel userModel;
    private String imagePath;
    //盛放图片地址
    private List<String> picdata = new ArrayList<>();
    private String order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_goods);
        Intent intent = getIntent();
        order = intent.getStringExtra("order");
        userModel = new UserModel(this);
        initData();
        initView();
    }

    private void initView() {
        // 返回
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        //商品信息
        evaluation_recycView = (RecyclerView) findViewById(R.id.evaluation_recycView);
        evaluation_recycView.setLayoutManager(new LinearLayoutManager(this));
        //商品信息适配器


        // 反馈意见
        myEditText = (MyEditText) findViewById(R.id.myEditText);
        //反馈图片
        request_submit_recycView = (RecyclerView) findViewById(R.id.request_submit_recycView);
        request_submit_recycView.setLayoutManager(new GridLayoutManager(this,4));
        imageModels.add(new ImageModel(null, R.mipmap.shangchuanpic));
        feedBackAdapter = new FeedBackAdapter(this,imageModels, R.layout.wentifankui_tupian_tianjia);
        feedBackAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                feedBackAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int viewType, int position) {
                        if (position == imageModels.size() - 1) {
                            CommonUtil.choicePicture(EvaluationGoods.this, SelectPicActivity.class, 1);
                        }
                    }
                });
            }
        });

        request_submit_recycView.setAdapter(feedBackAdapter);
        //提交
        tiJiao = (TextView) findViewById(R.id.tijiao_speak);
    }

    private void initData() {



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tijiao_speak:
                Toast.makeText(getBaseContext(),"提交成功",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == MConstants.PHOTO_REQUEST_SELECT) {
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    File file = null;
                    if (uri.toString().contains(MConstants.MEDIAFROM_TYPE_CONTENT)) {
                        imagePath = CommonUtil.getRealPathFromUri(this, uri);
                        file = new File(imagePath);
                    } else if (uri.toString().contains(MConstants.MEDIAFROM_TYPE_FILE)) {
                        try {
                            file = new File(new URI(uri.toString()));
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    if (file != null) {
                        ossAuth(file.getPath());
                    }
                }
            } else if (requestCode == MConstants.REQUESTCODE_UPLOAD) {

                if (data.hasExtra(UpLoadFileActivity.KEY_RESULT_URL)) {
                    final String url = data.getStringExtra(UpLoadFileActivity.KEY_RESULT_URL);
                    //添加图片地址到数组里，一块上传的后台
                    picdata.add(url);
                    //上传成功更新用户信息
                    ImageModel imageModel = new ImageModel(url, 0);
                    int position = imageModels.size() - 1;
                    imageModels.add(position, imageModel);
                    feedBackAdapter.add(position, imageModel);
                    feedBackAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    /**
     * 上传头像
     */
    public void ossAuth(final String filePath) {
        userModel.ossAuth(MConstants.UPLOAD_TYPE_AVATAR, new Callback<OssAuth>() {
            @Override
            public void onSuccess(OssAuth ossAuth) {
               Toast.makeText(getBaseContext(),"请求成功",Toast.LENGTH_SHORT).show();
                //调用上传
                Intent intent = new Intent(EvaluationGoods.this, UpLoadFileActivity.class);
                intent.putExtra(UpLoadFileActivity.KEY_OSSAUTH, ossAuth);
                intent.putExtra(UpLoadFileActivity.KEY_FILEPATH, filePath);
                intent.putExtra(UpLoadFileActivity.KEY_SHOW_UPLOAD_PROGRESS, false);
                startActivityForResult(intent, MConstants.REQUESTCODE_UPLOAD);
            }
            @Override
            public void onFailure(int resultCode, String message) {
                Toast.makeText(getBaseContext(),"请求失败",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
