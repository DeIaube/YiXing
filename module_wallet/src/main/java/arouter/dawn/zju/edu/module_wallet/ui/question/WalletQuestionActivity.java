package arouter.dawn.zju.edu.module_wallet.ui.question;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_QUESTION)
public class WalletQuestionActivity extends BaseActivity<WalletQuestionContract.Presenter> implements
        WalletQuestionContract.View, View.OnClickListener {

    @Override
    protected void initView() {
        findViewById(R.id.wallet_question_what_is_the_balance_layout).setOnClickListener(this);
        findViewById(R.id.wallet_question_how_to_recharge_layoout).setOnClickListener(this);
        findViewById(R.id.wallet_question_how_to_present_layoout).setOnClickListener(this);
        findViewById(R.id.wallet_question_how_to_add_bank_card_layout).setOnClickListener(this);
        findViewById(R.id.wallet_question_how_to_delete_bank_card_layout).setOnClickListener(this);
        findViewById(R.id.wallet_question_function_of_wallet_layout).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.wallet_question_what_is_the_balance_layout) {
            // 什么是余额
        } else if (id == R.id.wallet_question_how_to_recharge_layoout) {
            // 如何充值
        } else if (id == R.id.wallet_question_how_to_present_layoout) {
            // 如何提现
        } else if (id == R.id.wallet_question_how_to_add_bank_card_layout) {
            // 如何添加银行卡
        } else if (id == R.id.wallet_question_how_to_delete_bank_card_layout) {
            // 如何删除银行卡
        } else if (id == R.id.wallet_question_function_of_wallet_layout) {
            // 钱包的功能
        }
        ARouter.getInstance().build(Constants.AROUTER_WALLET_ANSWER).navigation();
    }
}
