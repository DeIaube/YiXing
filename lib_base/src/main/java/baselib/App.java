package baselib;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.Feedback;
import arouter.dawn.zju.edu.lib_net.bean.Goods;
import arouter.dawn.zju.edu.lib_net.bean.Order;
import baselib.config.Constants;
import baselib.util.SPUtil;
import io.realm.Realm;


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
        AVObject.registerSubclass(Goods.class);
        AVObject.registerSubclass(Order.class);
        AVObject.registerSubclass(Feedback.class);
        AVOSCloud.initialize(this,Constants.CLOUD_APPLICATION_ID,Constants.CLOUD_CLIENT_LEY);
        Realm.init(this);
        ARouter.init(this);
        SPUtil.init(this);
    }
}
