package yixing.dawn.zju.edu.yixing;

import com.alibaba.android.arouter.launcher.ARouter;

import baselib.base.BaseActivity;
import baselib.config.Constant;

public class SplashActivity extends BaseActivity {

    @Override
    protected void initView() {
        ARouter.getInstance().build(Constant.AROUTER_LOGIN).navigation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void bindPresenter() {

    }

}
