package baselib;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.PushService;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.lib_net.bean.Feedback;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumCollection;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumFollow;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPostLike;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPostReport;
import arouter.dawn.zju.edu.lib_net.bean.goods.Goods;
import arouter.dawn.zju.edu.lib_net.bean.goods.GoodsCollection;
import arouter.dawn.zju.edu.lib_net.bean.goods.GoodsEvaluate;
import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.lib_net.bean.wallet.Bill;
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
        AVObject.registerSubclass(GoodsCollection.class);
        AVObject.registerSubclass(GoodsEvaluate.class);
        AVObject.registerSubclass(ForumPost.class);
        AVObject.registerSubclass(ForumFollow.class);
        AVObject.registerSubclass(ForumComment.class);
        AVObject.registerSubclass(ForumPostLike.class);
        AVObject.registerSubclass(ForumCollection.class);
        AVObject.registerSubclass(ForumPostReport.class);
        AVObject.registerSubclass(UserCashCoupon.class);
        AVObject.registerSubclass(CashCoupon.class);
        AVObject.registerSubclass(Order.class);
        AVObject.registerSubclass(Bill.class);
        AVObject.registerSubclass(Feedback.class);
        PushService.setDefaultChannelId(this, "public");
        AVOSCloud.initialize(this,Constants.CLOUD_APPLICATION_ID,Constants.CLOUD_CLIENT_LEY);
        AVInstallation.getCurrentInstallation().saveInBackground();
    }
}
