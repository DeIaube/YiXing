package arouter.dawn.zju.edu.module_wallet.ui.forward;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import arouter.dawn.zju.edu.module_pay.pay.PayBuiled;
import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_FIRWARD)
public class WalletForwardActivity extends BaseActivity<WalletForwardContract.Presenter> implements
        WalletForwardContract.View, View.OnClickListener {

    Button walletSubmitBtn;
    EditText walletForwardAmountEt;


    @Override
    protected void initView() {
        walletSubmitBtn = findViewById(R.id.wallet_forward_submit);
        walletForwardAmountEt = findViewById(R.id.wallet_forward_amount);

        findViewById(R.id.wallet_forward_resouse_type).setOnClickListener(this);
        findViewById(R.id.wallet_forward_resouse_type_icon).setOnClickListener(this);
        walletSubmitBtn.setOnClickListener(this);

        walletForwardAmountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    walletSubmitBtn.setBackgroundResource(R.drawable.wallet_forward_submit_unable_bg);
                    walletSubmitBtn.setClickable(false);
                    return;
                }
                int amount = Integer.valueOf(s.toString());
                if (amount == 0 || amount > 10000) {
                    walletSubmitBtn.setBackgroundResource(R.drawable.wallet_forward_submit_unable_bg);
                    walletSubmitBtn.setClickable(false);
                } else {
                    walletSubmitBtn.setBackgroundResource(R.drawable.wallet_forward_submit_able_bg);
                    walletSubmitBtn.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_forward;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletForwardPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.wallet_forward_resouse_type || id == R.id.wallet_forward_resouse_type_icon) {
            // 选择充值来源
            showMessage("目前只支持支付宝");
        } else if (id == R.id.wallet_forward_submit) {
            final Double amount = Double.valueOf(walletForwardAmountEt.getText().toString());
            new PayBuiled(this)
                    .setTitle("余额充值")
                    .setContent("余额充值")
                    .setPrice(amount)
                    .setPayCallback(new PayCallback() {
                        @Override
                        public void paySuccess() {
                            mPresenter.paySuccess(amount);
                        }

                        @Override
                        public void payFailed() {
                            mPresenter.pasFaile();
                        }
                    })
                    .builedAliPay()
                    .pay(v);
        }
    }
}
