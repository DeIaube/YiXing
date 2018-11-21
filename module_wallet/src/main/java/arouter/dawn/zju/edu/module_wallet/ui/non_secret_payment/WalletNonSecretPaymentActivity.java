package arouter.dawn.zju.edu.module_wallet.ui.non_secret_payment;


import android.support.v7.widget.SwitchCompat;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_NON_SECRET_PAYMENT)
public class WalletNonSecretPaymentActivity extends BaseActivity<WalletNonSecretPaymentContract.Presenter> implements WalletNonSecretPaymentContract.View {

    SwitchCompat walletNonSecretPaymentSwitch;

    @Override
    protected void initView() {
        walletNonSecretPaymentSwitch = findViewById(R.id.wallet_non_secret_payment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_non_secret_payment;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletNonSeretPaymentPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
