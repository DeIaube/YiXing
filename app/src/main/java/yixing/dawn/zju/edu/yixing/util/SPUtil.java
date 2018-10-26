package yixing.dawn.zju.edu.yixing.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {

    private static SharedPreferences sp;

    public static void init(Context context) {
        if (sp != null) {
            return;
        }
        if (context == null) {
            throw new NullPointerException("SPUtil : context is null");
        }
        sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static void put(final String key, final String value) {
        sp.edit().putString(key, value).apply();
    }

    public static void put(final String key, final int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static void put(final String key, final float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public static String getString(final String key, final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static int getInt(final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public static float getFloat(final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }
}
