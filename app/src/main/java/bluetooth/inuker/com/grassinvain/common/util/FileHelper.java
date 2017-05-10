package bluetooth.inuker.com.grassinvain.common.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;

/**
 * 文件操作相关
 *
 * @author chenliang
 * @date 2012-11-30
 */
public class FileHelper {

    public static String APP_NAME = "turancao";
    public static String Cache = "cache";

    /**
     * 得到app项目目录，没有就创建，前提是sdcard可用
     *
     * @return
     */
    public static String getAppRootPath() {
        String appSDPath = "";
        if (SDCardTools.isSDCardExist()) {
            appSDPath = SDCardTools.getSDPath() + "/" + APP_NAME;
            File appSDPathFile = new File(appSDPath);
            if (!appSDPathFile.exists()) {
                appSDPathFile.mkdirs();
            }
            return appSDPath;
        } else {
            return appSDPath;
        }
    }

    /**
     * 得到项目缓存文件夹路径
     *
     * @return
     */
    public static String getAppCachePath() {
        String appCachePath = "";
        String appSDPath = getAppRootPath();
        if (!TextUtils.isEmpty(appSDPath)) {
            appCachePath = appSDPath + "/" + Cache;
            File appCachePathFile = new File(appCachePath);
            if (!appCachePathFile.exists()) {
                appCachePathFile.mkdirs();
            }
            return appCachePath;
        } else {
            return appCachePath;
        }
    }

    public static String getRealFilePath(final Context context, final Uri uri ) {
        if (null == uri) return "";
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
