package baselib.util;

import android.util.Log;

import baselib.constants.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:日志工具
 */
public class LogUtil {

    private final static boolean ENABLE_LOG = Constants.DEBUG;
    private final static String TAG = "LOG";

    public static int i(Object o, String msg) {
        if (ENABLE_LOG) {
            String tag = getTag(o);
            msg = generateLogPrefix(tag) + msg;
            return Log.i(tag, msg);
        }
        return 0;
    }

    public static int v(Object o, String msg) {
        if (ENABLE_LOG) {
            String tag = getTag(o);
            msg = generateLogPrefix(tag) + msg;
            return Log.v(tag, msg);
        }
        return 0;
    }

    public static int d(Object o, String msg) {
        if (ENABLE_LOG) {
            String tag = getTag(o);
            msg = generateLogPrefix(tag) + msg;
            return Log.d(tag, msg);
        }
        return 0;
    }

    public static int w(Object o, String msg) {
        if (ENABLE_LOG) {
            String tag = getTag(o);
            msg = generateLogPrefix(tag) + msg;
            return Log.w(tag, msg);
        }
        return 0;
    }

    public static int e(Object o, String msg) {
        if (ENABLE_LOG) {
            String tag = getTag(o);
            msg = generateLogPrefix(tag) + msg;
            return Log.e(tag, msg);
        }
        return 0;
    }

    public static int e(Object o, String msg, Throwable tr) {
        if (ENABLE_LOG) {
            String tag = getTag(o);
            msg = generateLogPrefix(tag) + msg;
            return Log.e(tag, msg, tr);
        }
        return 0;
    }

    private static String getTag(Object o) {
        if (o == null) {
            return TAG;
        }
        if (o instanceof String) {
            return (String) o;
        }
        return o.getClass().getSimpleName();
    }

    /**
     * 生成Log日志的前缀信息。如下格式：
     * 当前线程名+文件名+行号+方法名
     */
    private static String generateLogPrefix(String simpleClassName) {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().endsWith(simpleClassName)) {
                return "[" + Thread.currentThread().getName() + "][" + st.getFileName() + ":" + st.getLineNumber() + "][" + st.getMethodName() + "] ";
            }
        }
        return "";
    }
}