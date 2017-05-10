package bluetooth.inuker.com.grassinvain.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.shaojun.utils.log.Logger;

/**
 * 持久化工具类SharedPreferences
 */
public class XmlDB {
    public final static String TAG = "XmlDB";
    public static final String Pref_Name = "XmlDB_sp";
    private static XmlDB sInstance;
    public static SharedPreferences sp = null;
    private Context context;

    public static XmlDB getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new XmlDB(context);
            sInstance.open();
        }
        return sInstance;
    }

    public XmlDB(Context context) {
        this.context = context;
    }

    /**
     * Get a sharePreference instance.
     */
    public void open() {
        sp = context.getSharedPreferences(Pref_Name, 0);
    }

    public void close() {
        sInstance = null;
        sp = null;
    }


    /**
     * 储存一个对象到share preferences
     */
    public static <T> void saveObject2XmlDB(String key, T object) {
        if (sp == null) {
            Logger.e(TAG, "sp is null");
        } else {
            if (object != null && key != null) {

                SharedPreferences.Editor editor = sp.edit();
                Gson gson = new Gson();
                String objStr = gson.toJson(object);
                Logger.d(TAG, "add to sp json: key:" + key + " value:");
                Logger.json(objStr);
                editor.putString(key, objStr);
                editor.apply();
            }
        }
    }

    /**
     * 从share preferences获取一个对象   cls:UserInfo.class
     *
     * @param key
     * @param cls 该对象的类  例如：UserInfo.class
     * @return
     */
    public static <T> T getObject4XmlDB(String key, Class<T> cls) {
        if (sp == null) {
            Logger.e(TAG, "sp is null");
        } else {
            String jsonStr = sp.getString(key, "");
            Logger.d(TAG, "json from sp: key:" + key + " value:");
            Logger.json(jsonStr);
            if (jsonStr.equals("")) {
                return null;
            } else {
                return new Gson().fromJson(jsonStr, cls);
            }
        }
        return null;
    }

    public static void saveString(String key, String value) {
//        JLog.d("XmlDB saveString key:" + key + ",value:" + value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key, String defaultValue) {
//        JLog.d("XmlDB getString key:" + key + ",value:" + sp.getString(key, defaultValue));
        return sp.getString(key, defaultValue);
    }

    public static void saveInt(String key, int value) {
//        JLog.d("XmlDB saveInt key:" + key + ",value:" + value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key, int defaultValue) {
//        JLog.d("XmlDB getInt key:" + key + ",value:" + sp.getInt(key, defaultValue));
        return sp.getInt(key, defaultValue);
    }

    public static void saveLong(String key, long value) {
//        JLog.d("XmlDB saveLong key:" + key + ",value:" + value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLong(String key, long defaultValue) {
//        JLog.d("XmlDB getLong key:" + key + ",value:" + sp.getLong(key, defaultValue));
        return sp.getLong(key, defaultValue);
    }

    public static void saveFloat(String key, float value) {
//        JLog.d("XmlDB saveFloat key:" + key + ",value:" + value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float getFloat(String key, float defaultValue) {
//        JLog.d("XmlDB getFloat key:" + key + ",value:" + sp.getFloat(key, defaultValue));
        return sp.getFloat(key, defaultValue);
    }

    public static void saveBoolean(String key, boolean value) {
//        JLog.d("XmlDB saveBoolean key:" + key + ",value:" + value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
//        JLog.d("XmlDB getBoolean key:" + key + ",value:" + sp.getBoolean(key, defaultValue));
        return sp.getBoolean(key, defaultValue);
    }

    @Override
    protected void finalize() throws Throwable {
        sInstance.close();
        super.finalize();
    }

    public static void removeKey(String key) {
        Logger.d("XmlDB removeKey key:" + key);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }
}
