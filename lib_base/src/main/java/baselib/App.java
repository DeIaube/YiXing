package baselib;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVOSCloud;

import baselib.config.Constants;
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
        if (Constants.isDebug) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        AVOSCloud.initialize(this,"otc2yunsXXUGetz84g9NM9eX-gzGzoHsz","woIpcGUxFiOUevOnNXfiadcG");
        ARouter.init(this);
        SPUtil.init(this);
    }
}
