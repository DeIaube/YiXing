package baselib;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.PushService;

import arouter.dawn.zju.edu.lib_net.bean.Feedback;
import arouter.dawn.zju.edu.lib_net.bean.Goods;
import arouter.dawn.zju.edu.lib_net.bean.Order;
import arouter.dawn.zju.edu.lib_net.bean.User;
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
        initLeanCloud();
        Realm.init(this);
        ARouter.init(this);
        SPUtil.init(this);
    }

    /**
     * 初始化LeanCloud相关模块
     */
    private void initLeanCloud() {
        AVObject.registerSubclass(User.class);
        AVObject.registerSubclass(Goods.class);
        AVObject.registerSubclass(Order.class);
        AVObject.registerSubclass(Feedback.class);
        PushService.setDefaultChannelId(this, "public");
        AVOSCloud.initialize(this,Constants.CLOUD_APPLICATION_ID,Constants.CLOUD_CLIENT_LEY);
        AVInstallation.getCurrentInstallation().saveInBackground();
    }
}
