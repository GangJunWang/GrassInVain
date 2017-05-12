package bluetooth.inuker.com.grassinvain.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.base.BaseActivity;
import bluetooth.inuker.com.grassinvain.common.cache.CacheCenter;
import bluetooth.inuker.com.grassinvain.common.model.OssAuth;
import bluetooth.inuker.com.grassinvain.common.util.CommonUtil;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import bluetooth.inuker.com.grassinvain.common.widget.MyDialog;
import bluetooth.inuker.com.grassinvain.common.widget.SlidingMenu;
import bluetooth.inuker.com.grassinvain.controller.activity.AboutUs;
import bluetooth.inuker.com.grassinvain.controller.activity.SystemSetting;
import bluetooth.inuker.com.grassinvain.controller.activity.UpLoadFileActivity;
import bluetooth.inuker.com.grassinvain.controller.fragment.HomeFragment;
import bluetooth.inuker.com.grassinvain.controller.fragment.MessageFragment;
import bluetooth.inuker.com.grassinvain.controller.fragment.PersonCenterFragment;
import bluetooth.inuker.com.grassinvain.controller.fragment.ShoppingFragment;
import bluetooth.inuker.com.grassinvain.controller.login.LoginActivity;
import bluetooth.inuker.com.grassinvain.controller.personinformation.ChangePersonInformation;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;


public class MainActivity extends BaseActivity {

    private SlidingMenu mMenu;
    private ImageView startcrhua;
    private RelativeLayout shouyeitem;
    private LinearLayout cehuaitem1, cehuaitem2, cehuaitem3, cehuaitem4;
    private ImageView cehuaitem1image, cehuaitem2image, cehuaitem3image, cehuaitem4image;
    private TextView cehuaitem1text, cehuaitem2text, cehuaitem3text, cehuaitem4text;
    private TextView cehuaitem1xian, cehuaitem2xian, cehuaitem3xian, cehuaitem4xian;
    private FrameLayout change_type_fragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private LinearLayout setting, aboutUs, outApp;
    private TextView titleDrawable;
    private static RelativeLayout titltBackContron;
    private TextView bianji;
    private ImageView srarch;
    private int flag = 1;
    private String gooddetails;
    private String imagePath;
    private ShoppingFragment shoppingFragment;
    private UserModel userModel;
    //盛放图片地址
    private List<String> picdata = new ArrayList<>();

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.startcehua:
                mMenu.toggle();
                break;
            case R.id.cehuaitem1:
                cehuaitem2image.setImageResource(R.mipmap.gouwucheweixuan);
                cehuaitem2text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem2xian.setVisibility(View.INVISIBLE);
                cehuaitem1image.setImageResource(R.mipmap.shouyexuanzhong);
                cehuaitem1text.setTextColor(this.getResources().getColor(R.color.zhuticolor));
                cehuaitem1xian.setVisibility(View.VISIBLE);

                cehuaitem3image.setImageResource(R.mipmap.personweixun);
                cehuaitem3text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem3xian.setVisibility(View.INVISIBLE);

                cehuaitem4image.setImageResource(R.mipmap.messageweixuan);
                cehuaitem4text.setTextColor(this.getResources().getColor(R.color.write));
                //cehuaitem4xian.setVisibility(View.INVISIBLE);

                change(fragmentList.get(0));
                //    mMenu.toggle();
                titleDrawable.setText("商品列表");
                titleDrawable.setTextColor(this.getResources().getColor(R.color.black));
                titltBackContron.setBackgroundResource(R.drawable.shape_corner_write);
                startcrhua.setImageResource(R.mipmap.daohangs);
                srarch.setVisibility(View.VISIBLE);
                bianji.setVisibility(View.GONE);
                flag = 1;
                break;
            case R.id.cehuaitem2:

                cehuaitem2image.setImageResource(R.mipmap.gouwuchexuanzhong);
                cehuaitem2text.setTextColor(this.getResources().getColor(R.color.zhuticolor));
                cehuaitem2xian.setVisibility(View.VISIBLE);

                cehuaitem1image.setImageResource(R.mipmap.shouyeweixuan);
                cehuaitem1text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem1xian.setVisibility(View.INVISIBLE);

                cehuaitem3image.setImageResource(R.mipmap.personweixun);
                cehuaitem3text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem3xian.setVisibility(View.INVISIBLE);

                cehuaitem4image.setImageResource(R.mipmap.messageweixuan);
                cehuaitem4text.setTextColor(this.getResources().getColor(R.color.write));
                // cehuaitem4xian.setVisibility(View.INVISIBLE);
                change(fragmentList.get(1));
                mMenu.toggle();
                titleDrawable.setText("购物车");
                srarch.setVisibility(View.INVISIBLE);
                bianji.setVisibility(View.VISIBLE);
                flag = 2;
                break;
            case R.id.cehuaitem3:
                cehuaitem2image.setImageResource(R.mipmap.gouwucheweixuan);
                cehuaitem2text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem2xian.setVisibility(View.INVISIBLE);

                cehuaitem1image.setImageResource(R.mipmap.shouyeweixuan);
                cehuaitem1text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem1xian.setVisibility(View.INVISIBLE);


                cehuaitem3image.setImageResource(R.mipmap.personxuanzhong);
                cehuaitem3text.setTextColor(this.getResources().getColor(R.color.zhuticolor));
                cehuaitem3xian.setVisibility(View.VISIBLE);

                cehuaitem4image.setImageResource(R.mipmap.messageweixuan);
                cehuaitem4text.setTextColor(this.getResources().getColor(R.color.write));
                //  cehuaitem4xian.setVisibility(View.INVISIBLE);
//
                change(fragmentList.get(2));
                mMenu.toggle();

                titleDrawable.setText("个人中心");
                titleDrawable.setTextColor(this.getResources().getColor(R.color.write));
                titltBackContron.setBackgroundResource(0x0000);
                startcrhua.setImageResource(R.mipmap.daohang);
                bianji.setVisibility(View.VISIBLE);
                srarch.setVisibility(View.INVISIBLE);
                flag = 3;
                break;
            case R.id.cehuaitem4:

                cehuaitem2image.setImageResource(R.mipmap.gouwucheweixuan);
                cehuaitem2text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem2xian.setVisibility(View.INVISIBLE);

                cehuaitem1image.setImageResource(R.mipmap.shouyeweixuan);
                cehuaitem1text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem1xian.setVisibility(View.INVISIBLE);

                cehuaitem3image.setImageResource(R.mipmap.personweixun);
                cehuaitem3text.setTextColor(this.getResources().getColor(R.color.write));
                cehuaitem3xian.setVisibility(View.INVISIBLE);

                cehuaitem4image.setImageResource(R.mipmap.messagexuanzhong);
                cehuaitem4text.setTextColor(this.getResources().getColor(R.color.zhuticolor));

                change(fragmentList.get(3));
                mMenu.toggle();

                titleDrawable.setText("消息");
                startcrhua.setImageResource(R.mipmap.daohangs);
                titleDrawable.setTextColor(this.getResources().getColor(R.color.black));
                titltBackContron.setBackgroundResource(R.drawable.shape_corner_write);

                srarch.setVisibility(View.GONE);
                bianji.setVisibility(View.GONE);

                flag = 4;
                break;

            case R.id.bianji:
                if (3 == flag) {
                    Intent intent = new Intent(MainActivity.this, ChangePersonInformation.class);
                    startActivity(intent);
                }
                if ((2 == flag)) {
                    if (bianji.getText().toString().trim().equals("编辑")) {
                        bianji.setText("完成");
                        shoppingFragment.operation();
                    } else {
                        bianji.setText("编辑");
                        shoppingFragment.complete();
                    }
                }
                break;
            case R.id.setting: {
                Intent intent = new Intent(this, SystemSetting.class);
                startActivity(intent);
            }
            break;
            case R.id.aboutUs: {
                Intent intent = new Intent(this, AboutUs.class);
                startActivity(intent);
            }
            break;
            case R.id.outApp:
                // 判断是否登录  如果没有登录就跳转到登录界面
                if (!CacheCenter.getInstance().isLogin()) {
                    Toast.makeText(getBaseContext(), "暂未登录", Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.class);
                    return;
                } else {
                    MyDialog.Builder builder = new MyDialog.Builder(this);
                    builder.setMessage("确定要退出吗");
                    //    builder.setTitle("提示");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            CacheCenter.getInstance().loginOutResetUser();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    builder.setNegativeButton("取消",
                            new android.content.DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    builder.create().show();
                }


                break;
        }
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {

        userModel = new UserModel(this);
        Intent intent = getIntent();
        gooddetails = intent.getStringExtra("GOODDETAILS");
        mMenu = (SlidingMenu) view.findViewById(R.id.activity_main);
        startcrhua = (ImageView) view.findViewById(R.id.startcehua);
        bianji = (TextView) view.findViewById(R.id.bianji);
        srarch = (ImageView) view.findViewById(R.id.srarch);

        cehuaitem1 = (LinearLayout) view.findViewById(R.id.cehuaitem1);
        cehuaitem1image = (ImageView) view.findViewById(R.id.cehuaitem1image);
        cehuaitem1text = (TextView) view.findViewById(R.id.cehuaitem1text);
        cehuaitem1xian = (TextView) view.findViewById(R.id.cehuaitem1xian);

        cehuaitem2 = (LinearLayout) view.findViewById(R.id.cehuaitem2);
        cehuaitem2image = (ImageView) view.findViewById(R.id.cehuaitem2image);
        cehuaitem2text = (TextView) view.findViewById(R.id.cehuaitem2text);
        cehuaitem2xian = (TextView) view.findViewById(R.id.cehuaitem2xian);

        cehuaitem3 = (LinearLayout) view.findViewById(R.id.cehuaitem3);
        cehuaitem3image = (ImageView) view.findViewById(R.id.cehuaitem3image);
        cehuaitem3text = (TextView) view.findViewById(R.id.cehuaitem3text);
        cehuaitem3xian = (TextView) view.findViewById(R.id.cehuaitem3xian);

        cehuaitem4 = (LinearLayout) view.findViewById(R.id.cehuaitem4);
        cehuaitem4image = (ImageView) view.findViewById(R.id.cehuaitem4image);
        cehuaitem4text = (TextView) view.findViewById(R.id.cehuaitem4text);
        cehuaitem4xian = (TextView) view.findViewById(R.id.cehuaitem4xian);

        /**
         * 设置
         */

        setting = (LinearLayout) view.findViewById(R.id.setting);
        aboutUs = (LinearLayout) view.findViewById(R.id.aboutUs);
        outApp = (LinearLayout) view.findViewById(R.id.outApp);

        /**
         * 动态改变title
         */
        titleDrawable = (TextView) view.findViewById(R.id.title_drawable);

        /**
         * 动态改变title栏的背景
         */
        titltBackContron = (RelativeLayout) view.findViewById(R.id.titlt_back_contron);
        /**
         * 主布局
         */
        change_type_fragment = (FrameLayout) view.findViewById(R.id.change_type_fragment);
        //购物车监听

    }

    @Override
    public void setListener() {
        mMenu.setOnClickListener(this);
        startcrhua.setOnClickListener(this);
        cehuaitem1.setOnClickListener(this);
        cehuaitem2.setOnClickListener(this);
        cehuaitem3.setOnClickListener(this);
        cehuaitem4.setOnClickListener(this);
        setting.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        outApp.setOnClickListener(this);
        bianji.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

        HomeFragment homeFragment = new HomeFragment();
        shoppingFragment = new ShoppingFragment();
        PersonCenterFragment personCenterFragment = new PersonCenterFragment();
        MessageFragment messageFragment = new MessageFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(shoppingFragment);
        fragmentList.add(personCenterFragment);
        fragmentList.add(messageFragment);
        if ("2".equals(gooddetails)) {

            cehuaitem2image.setImageResource(R.mipmap.gouwuchexuanzhong);
            cehuaitem2text.setTextColor(this.getResources().getColor(R.color.zhuticolor));
            cehuaitem2xian.setVisibility(View.VISIBLE);

            cehuaitem1image.setImageResource(R.mipmap.shouyeweixuan);
            cehuaitem1text.setTextColor(this.getResources().getColor(R.color.write));
            cehuaitem1xian.setVisibility(View.INVISIBLE);

            cehuaitem3image.setImageResource(R.mipmap.personweixun);
            cehuaitem3text.setTextColor(this.getResources().getColor(R.color.write));
            cehuaitem3xian.setVisibility(View.INVISIBLE);

            cehuaitem4image.setImageResource(R.mipmap.messageweixuan);
            cehuaitem4text.setTextColor(this.getResources().getColor(R.color.write));
            // cehuaitem4xian.setVisibility(View.INVISIBLE);
            change(fragmentList.get(1));
            titleDrawable.setText("购物车");
            srarch.setVisibility(View.INVISIBLE);
            bianji.setVisibility(View.VISIBLE);
            flag = 2;
        } else {
            change(fragmentList.get(0));
        }
    }

    public void change(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.change_type_fragment, f)
                .commit();
    }

    /**
     * 当购物车没有商品时切换到首页选择商品进行购买
     */

    public void changeFragment() {
        cehuaitem2image.setImageResource(R.mipmap.gouwucheweixuan);
        cehuaitem2text.setTextColor(this.getResources().getColor(R.color.write));
        cehuaitem2xian.setVisibility(View.INVISIBLE);
        cehuaitem1image.setImageResource(R.mipmap.shouyexuanzhong);
        cehuaitem1text.setTextColor(this.getResources().getColor(R.color.zhuticolor));
        cehuaitem1xian.setVisibility(View.VISIBLE);

        cehuaitem3image.setImageResource(R.mipmap.personweixun);
        cehuaitem3text.setTextColor(this.getResources().getColor(R.color.write));
        cehuaitem3xian.setVisibility(View.INVISIBLE);

        cehuaitem4image.setImageResource(R.mipmap.messageweixuan);
        cehuaitem4text.setTextColor(this.getResources().getColor(R.color.write));
        change(fragmentList.get(0));


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
                    UserInfo userInfo = new UserInfo();
                    userInfo.avatarUrl = url;
                    userModel.getUpdateUser(userInfo, new Callback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getBaseContext(),"更新成功，等待审核",Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(int resultCode, String message) {

                        }
                    });
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
                //调用上传
                Intent intent = new Intent(MainActivity.this, UpLoadFileActivity.class);
                intent.putExtra(UpLoadFileActivity.KEY_OSSAUTH, ossAuth);
                intent.putExtra(UpLoadFileActivity.KEY_FILEPATH, filePath);
                intent.putExtra(UpLoadFileActivity.KEY_SHOW_UPLOAD_PROGRESS, false);
                startActivityForResult(intent, MConstants.REQUESTCODE_UPLOAD);
            }
            @Override
            public void onFailure(int resultCode, String message) {
                Toast.makeText(getBaseContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
