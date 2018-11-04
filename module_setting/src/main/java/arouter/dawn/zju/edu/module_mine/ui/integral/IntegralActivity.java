package arouter.dawn.zju.edu.module_mine.ui.integral;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.base.BaseActivity;

public class IntegralActivity extends BaseActivity<IntegralContract.Presenter> implements IntegralContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
