package baselib.interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.config.Constants;


@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String group = postcard.getGroup();
        Log.e("aaaa", String.valueOf(User.getCurrentUser()));
        if (User.getCurrentUser() == null && !group.equals(Constants.AROUTER_ACCOUNT_GROP)) {
            ARouter.getInstance().build(Constants.AROUTER_ACCOUNT_LOGIN).navigation();
            return;
        }
        // 不拦截
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        Log.e("aaaa", "init");
    }
}
