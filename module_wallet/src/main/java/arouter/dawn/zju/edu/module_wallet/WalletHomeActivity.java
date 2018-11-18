package arouter.dawn.zju.edu.module_wallet;

import com.alibaba.android.arouter.facade.annotation.Route;

import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_HOME)
public class WalletHomeActivity extends BaseActivity<WalletHomeContract.Presenter> implements
        WalletHomeContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_home;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletHomePresenter();
    }
}
