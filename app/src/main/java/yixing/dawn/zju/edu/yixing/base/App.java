package yixing.dawn.zju.edu.yixing.base;

import android.app.Application;
import android.content.Context;

import yixing.dawn.zju.edu.yixing.util.SPUtil;

public class App extends Application {

    private static Context appContext;

    public static Context getContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        SPUtil.init(this);
    }
}
