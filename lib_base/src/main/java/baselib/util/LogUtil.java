package baselib.util;

import android.util.Log;

import baselib.config.Constant;

public class LogUtil {
    public static void i(String key, String value) {
        if (Constant.isDebug) {
            Log.i(key, value);
        }
    }

    public static void e(String key, String value) {
        if (Constant.isDebug) {
            Log.e(key, value);
        }
    }

    public static void d(String key, String value) {
        if (Constant.isDebug) {
            Log.d(key, value);
        }
    }

    public static void v(String key, String value) {
        if (Constant.isDebug) {
            Log.v(key, value);
        }
    }

    public static void w(String key, String value) {
        if (Constant.isDebug) {
            Log.w(key, value);
        }
    }
}
