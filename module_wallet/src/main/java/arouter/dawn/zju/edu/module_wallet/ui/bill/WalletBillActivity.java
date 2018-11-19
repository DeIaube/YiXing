package arouter.dawn.zju.edu.module_wallet.ui.bill;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_BILL)
public class WalletBillActivity extends BaseActivity<WalletBillContract.Presenter> implements
        WalletBillContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_bill;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletBillPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
