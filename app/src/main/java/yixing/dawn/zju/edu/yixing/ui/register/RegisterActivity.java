package yixing.dawn.zju.edu.yixing.ui.register;

import yixing.dawn.zju.edu.yixing.R;
import yixing.dawn.zju.edu.yixing.base.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View{

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new RegisterPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
