package arouter.dawn.zju.edu.module_wallet.ui.non_secret_payment;


import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_NON_SECRET_PAYMENT)
public class WalletNonSecretPaymentActivity extends BaseActivity<WalletNonSecretPaymentContract.Presenter> implements WalletNonSecretPaymentContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final int QUOTA_NULL = 0x0;
    private static final int QUOTA_50 = 0x1;
    private static final int QUOTA_200 = 0x2;
    private static final int QUOTA_500 = 0x3;
    private static final int QUOTA_1000 = 0x4;

    SwitchCompat walletNonSecretPaymentSwitch;
    TextView quota50Tv;
    TextView quota200Tv;
    TextView quota500Tv;
    TextView quota1000Tv;

    private int mCurrentQuota;

    @Override
    protected void initView() {
        walletNonSecretPaymentSwitch = findViewById(R.id.wallet_non_secret_payment);
        quota50Tv = findViewById(R.id.wallet_non_secret_50);
        quota200Tv = findViewById(R.id.wallet_non_secret_200);
        quota500Tv = findViewById(R.id.wallet_non_secret_500);
        quota1000Tv = findViewById(R.id.wallet_non_secret_1000);

        quota50Tv.setOnClickListener(this);
        quota200Tv.setOnClickListener(this);
        quota500Tv.setOnClickListener(this);
        quota1000Tv.setOnClickListener(this);
        walletNonSecretPaymentSwitch.setOnCheckedChangeListener(this);
        findViewById(R.id.wallet_non_secret_protocol).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_non_secret_payment;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletNonSeretPaymentPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.wallet_non_secret_protocol) {
            // 跳转到免密协议
            ARouter.getInstance().build(Constants.AROUTER_PAY_PROTOTOL).navigation();
        } else if (id == R.id.wallet_non_secret_50) {
            mCurrentQuota = QUOTA_50;
            resetQuota();
            walletNonSecretPaymentSwitch.setChecked(true);
        } else if (id == R.id.wallet_non_secret_200) {
            mCurrentQuota = QUOTA_200;
            resetQuota();
            walletNonSecretPaymentSwitch.setChecked(true);
        } else if (id == R.id.wallet_non_secret_500) {
            mCurrentQuota = QUOTA_500;
            resetQuota();
            walletNonSecretPaymentSwitch.setChecked(true);
        } else if (id == R.id.wallet_non_secret_1000) {
            mCurrentQuota = QUOTA_1000;
            resetQuota();
            walletNonSecretPaymentSwitch.setChecked(true);
        }
    }

    private void resetQuota() {
        quota50Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_unselect_bg);
        quota200Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_unselect_bg);
        quota500Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_unselect_bg);
        quota1000Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_unselect_bg);

        quota50Tv.setTextColor(getResources().getColor(R.color.bec2c9));
        quota200Tv.setTextColor(getResources().getColor(R.color.bec2c9));
        quota500Tv.setTextColor(getResources().getColor(R.color.bec2c9));
        quota1000Tv.setTextColor(getResources().getColor(R.color.bec2c9));

        if (mCurrentQuota == QUOTA_50) {
            quota50Tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            quota50Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_select_bg);
        } else if (mCurrentQuota == QUOTA_200) {
            quota200Tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            quota200Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_select_bg);
        } else if (mCurrentQuota == QUOTA_500) {
            quota500Tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            quota500Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_select_bg);
        } else if (mCurrentQuota == QUOTA_1000) {
            quota1000Tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            quota1000Tv.setBackgroundResource(R.drawable.wallet_non_secret_payment_quota_select_bg);
        }
        if (mCurrentQuota == QUOTA_50) {
            mPresenter.updateSecretPayment(50);
        } else if (mCurrentQuota == QUOTA_200) {
            mPresenter.updateSecretPayment(200);
        } else if (mCurrentQuota == QUOTA_500) {
            mPresenter.updateSecretPayment(500);
        } else if (mCurrentQuota == QUOTA_1000) {
            mPresenter.updateSecretPayment(1000);
        } else {
            mPresenter.updateSecretPayment(0);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (mCurrentQuota == QUOTA_NULL) {
                mCurrentQuota = QUOTA_50;
            }
        } else {
            mCurrentQuota = QUOTA_NULL;
        }
        resetQuota();
    }
}
