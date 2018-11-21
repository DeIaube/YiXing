package arouter.dawn.zju.edu.module_wallet.ui.bank_card;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_BANK_CARD)
public class WalletBankCardActivity extends BaseActivity<WalletBankCardContract.Presenter> implements WalletBankCardContract.View, View.OnClickListener {

    @Override
    protected void initView() {
        findViewById(R.id.wallet_bank_card_add_bank_card).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_bank_card;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletBankCardPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.wallet_bank_card_add_bank_card) {
            
        }
    }
}
