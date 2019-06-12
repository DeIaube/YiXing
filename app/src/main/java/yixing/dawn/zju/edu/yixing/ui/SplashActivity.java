package yixing.dawn.zju.edu.yixing.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.PushService;
import com.tbruyelle.rxpermissions2.RxPermissions;

import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;
import io.reactivex.functions.Consumer;
import yixing.dawn.zju.edu.yixing.R;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 初始化数据以及权限
 * 跳转至主页面
 */
public class SplashActivity extends BaseActivity {

    static final String TAG = "SplashActivity";

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        // 设置默认打开的 Activity
        PushService.setDefaultPushCallback(this, SplashActivity.class);
        PushService.subscribe(this, "public", SplashActivity.class);
        requestPermission();
    }

    /**
     * 动态获取权限
     */
    @SuppressLint("CheckResult")
    private void requestPermission() {
        new RxPermissions(SplashActivity.this).request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            //所有权限都开启aBoolean才为true，否则为false
                            Log.i(TAG, "权限已开启");
                            ARouter.getInstance().build(RouteConstants.AROUTER_APP_MAIN).navigation();
                        } else {
                            Log.i(TAG, "权限被拒绝，请在设置里面开启相应权限，若无相应权限会影响使用");
                        }
                        finish();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void bindPresenter() {

    }

}
