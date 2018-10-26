package yixing.dawn.zju.edu.yixing.base;

import android.app.Application;

import yixing.dawn.zju.edu.yixing.util.SPUtil;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SPUtil.init(this);
    }
}
