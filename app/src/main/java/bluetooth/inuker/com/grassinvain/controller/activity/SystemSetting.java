package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.base.BaseActivity;

public class SystemSetting extends BaseActivity {

    private ImageView back;
    private RelativeLayout yijianfankui;
    private RelativeLayout banbenxinxi;

    @Override
    public void widgetClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.yijanfankui:
                startActivity(Feedback.class);
                break;
            case R.id.banbenxinxi:
               /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View inflate = LayoutInflater.from(this).inflate(R.layout.the_verson, null);
                builder.setView(inflate);
                builder.show();*/
                startActivity(TheVerSon.class);
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
        return R.layout.activity_system_setting;
    }

    @Override
    public void initView(View view) {
        back = (ImageView) view.findViewById(R.id.back);
        yijianfankui = (RelativeLayout) view.findViewById(R.id.yijanfankui);
        banbenxinxi = (RelativeLayout) view.findViewById(R.id.banbenxinxi);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        yijianfankui.setOnClickListener(this);
        banbenxinxi.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    /**
     * 获取当前版本号
     */

    public void getVersion() {

            PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;


    }
}
