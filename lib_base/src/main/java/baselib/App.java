package baselib;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

import baselib.config.Constant;
import baselib.util.SPUtil;


public class App extends Application {

    private static Context appContext;

    public static Context getContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        if (Constant.isDebug) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        Log.e("aaaa", "ARouter.init(this)");
        ARouter.init(this);
        SPUtil.init(this);
    }
}
