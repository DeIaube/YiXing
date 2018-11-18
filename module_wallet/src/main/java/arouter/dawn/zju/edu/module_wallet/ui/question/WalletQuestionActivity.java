package arouter.dawn.zju.edu.module_wallet.ui.question;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_QUESTION)
public class WalletQuestionActivity extends BaseActivity<WalletQuestionContract.Presenter> implements
        WalletQuestionContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_question;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletQuestionPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
