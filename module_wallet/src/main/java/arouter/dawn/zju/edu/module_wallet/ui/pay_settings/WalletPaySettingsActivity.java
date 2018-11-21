package arouter.dawn.zju.edu.module_wallet.ui.pay_settings;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_PAY_SETTINGS)
public class WalletPaySettingsActivity extends BaseActivity<WalletPaySettingsContract.Presenter> implements WalletPaySettingsContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_pay_settings;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletPaySettingsPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
