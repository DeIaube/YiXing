package baselib.util;

import android.util.Log;

import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class LogUtil {
    public static void i(String key, String value) {
        if (Constants.isDebug) {
            Log.i(key, value);
        }
    }

    public static void e(String key, String value) {
        if (Constants.isDebug) {
            Log.e(key, value);
        }
    }

    public static void d(String key, String value) {
        if (Constants.isDebug) {
            Log.d(key, value);
        }
    }

    public static void v(String key, String value) {
        if (Constants.isDebug) {
            Log.v(key, value);
        }
    }

    public static void w(String key, String value) {
        if (Constants.isDebug) {
            Log.w(key, value);
        }
    }
}
