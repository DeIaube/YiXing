package yixing.dawn.zju.edu.yixing;

import com.alibaba.android.arouter.facade.annotation.Route;

import baselib.base.BaseActivity;
import baselib.config.Constant;

@Route(path = Constant.AROUTER_MAIN)
public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindPresenter() {

    }

}
