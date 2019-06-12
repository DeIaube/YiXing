package baselib.interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 拦截器(拦截未用户登录)
 */
@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {

    private static final String TAG = "LoginInterceptor";

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.i(TAG, "LoginInterceptor process " + postcard.toString());
        String group = postcard.getGroup();
        if (User.getCurrentUser() == null && !group.equals(RouteConstants.AROUTER_ACCOUNT_GROP)) {
            Log.i(TAG, "Interceptor");
            ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_LOGIN).navigation();
            return;
        }
        // 不拦截
        Log.i(TAG, "UnInterceptor");
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        Log.i(TAG, "LoginInterceptor init");
    }
}
