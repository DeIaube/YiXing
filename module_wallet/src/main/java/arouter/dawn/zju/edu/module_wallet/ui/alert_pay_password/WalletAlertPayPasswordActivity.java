package arouter.dawn.zju.edu.module_wallet.ui.alert_pay_password;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_ALERT_PAY_PASSWORD)
public class WalletAlertPayPasswordActivity extends BaseActivity<WalletAlertPayPasswordContract.Presenter> implements WalletAlertPayPasswordContract.View{

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_alert_pay_password;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
