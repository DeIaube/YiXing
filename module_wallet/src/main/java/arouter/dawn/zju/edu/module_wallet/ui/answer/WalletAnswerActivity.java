package arouter.dawn.zju.edu.module_wallet.ui.answer;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_ANSWER)
public class WalletAnswerActivity extends BaseActivity<WalletAnswerContract.Presenter> implements
        WalletAnswerContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_answer;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletAnswerPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
