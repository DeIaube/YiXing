package baselib.util;

import android.os.Handler;
import android.os.Looper;

public class MainLooperUtil extends Handler {

    private static MainLooperUtil instance = new MainLooperUtil();

    public static MainLooperUtil getInstance() {
        return instance;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            getInstance().post(runnable);
        }
    }

    private MainLooperUtil() {
        super(Looper.getMainLooper());
    }

}
