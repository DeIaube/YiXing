package arouter.dawn.zju.edu.module_pay.ui.protocol;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_pay.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_PAY_PROTOTOL)
public class PayProtocolActivity extends BaseActivity<PayProtocolContract.Presenter> implements PayProtocolContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_protocol;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new PayProtocolPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
