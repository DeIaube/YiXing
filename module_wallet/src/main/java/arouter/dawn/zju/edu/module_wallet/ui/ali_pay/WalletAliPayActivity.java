package arouter.dawn.zju.edu.module_wallet.ui.ali_pay;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_ALI_PAY)
public class WalletAliPayActivity extends BaseActivity<WalletAliPayContract.Presenter> implements WalletAliPayContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_ali_pay;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletAliPayPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
