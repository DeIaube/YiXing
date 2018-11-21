package arouter.dawn.zju.edu.module_wallet.ui.pay_settings;

import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_PAY_SETTINGS)
public class WalletPaySettingsActivity extends BaseActivity<WalletPaySettingsContract.Presenter> implements WalletPaySettingsContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    SwitchCompat walletSettingsFingerprintPaymentSwitch;
    TextView walletSettingsNonSecretPaymentState;

    @Override
    protected void initView() {
        walletSettingsFingerprintPaymentSwitch = findViewById(R.id.wallet_settings_fingerprint_payment);
        walletSettingsNonSecretPaymentState = findViewById(R.id.wallet_settings_non_secret_payment_state);

        findViewById(R.id.wallet_settings_alert_pay_password).setOnClickListener(this);
        findViewById(R.id.wallet_settings_reset_pay_password).setOnClickListener(this);
        findViewById(R.id.wallet_settings_non_secret_payment).setOnClickListener(this);
        walletSettingsFingerprintPaymentSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_pay_settings;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletPaySettingsPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.wallet_settings_alert_pay_password) {
            // 修改支付密码
            ARouter.getInstance().build(Constants.AROUTER_WALLET_ALERT_PAY_PASSWORD).navigation();
        } else if (id == R.id.wallet_settings_reset_pay_password) {
            // 忘记(重置)支付密码
        } else if (id == R.id.wallet_settings_non_secret_payment) {
            // 小额免密
            ARouter.getInstance().build(Constants.AROUTER_WALLET_NON_SECRET_PAYMENT).navigation();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
