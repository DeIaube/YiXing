package arouter.dawn.zju.edu.module_wallet.ui.alert_pay_password;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethanco.lib.PasswordInput;

import java.util.concurrent.TimeUnit;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 更改支付密码页面
 */
@Route(path = Constants.AROUTER_WALLET_ALERT_PAY_PASSWORD)
public class WalletAlertPayPasswordActivity extends BaseActivity<WalletAlertPayPasswordContract.Presenter> implements WalletAlertPayPasswordContract.View, PasswordInput.TextLenChangeListener {

    TextView walletAlertPayPasswordTipTv;
    PasswordInput walletAlertPayPasswordInput;

    private boolean mPayPasswordChecked;
    private String mPayPassword;

    @Override
    protected void initView() {
        walletAlertPayPasswordTipTv = findViewById(R.id.wallet_alert_pay_password_tip);
        walletAlertPayPasswordInput = findViewById(R.id.wallet_alert_pay_password_input);
        walletAlertPayPasswordTipTv.setText(getString(R.string.alert_pay_password_please_input_previous_pay_password));
        walletAlertPayPasswordInput.setTextLenChangeListener(this);

        mPayPasswordChecked = false;

        inputGetFocusable();
    }

    private void inputGetFocusable() {
        walletAlertPayPasswordInput.setFocusable(true);
        walletAlertPayPasswordInput.setFocusableInTouchMode(true);
        walletAlertPayPasswordInput.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(walletAlertPayPasswordInput,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_alert_pay_password;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletAlertPayPasswordPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onTextLenChange(CharSequence charSequence, int i) {
        if (i != 6) {
            return;
        }
        String password = charSequence.toString();
        if (!mPayPasswordChecked) {
            if (User.getCurrentUser(User.class).getPayPassword().equals(password)) {
                mPayPasswordChecked = true;
                walletAlertPayPasswordTipTv.setText(getString(R.string.alert_pay_password_please_input_alert_pay_password));
            } else {
                showMessage(getString(R.string.alert_pay_password_please_input_previous_pay_password_error));
            }
            resetPasswordLayout();
            return;
        }
        if (TextUtils.isEmpty(mPayPassword)) {
            walletAlertPayPasswordTipTv.setText(getString(R.string.alert_pay_password_please_confirm_alert_pay_password));
            mPayPassword = password;
            resetPasswordLayout();
        } else {
            if (password.equals(mPayPassword)) {
                mPresenter.alertPayPassword(password);
            } else {
                walletAlertPayPasswordTipTv.setText(getString(R.string.alert_pay_password_please_input_alert_pay_password));
                showMessage(getString(R.string.alert_pay_password_please_input_alert_pay_password_diff));
                mPayPassword = null;
                resetPasswordLayout();
            }
        }
    }

    @SuppressLint("CheckResult")
    private void resetPasswordLayout() {
        Observable.interval(800, 0, TimeUnit.MILLISECONDS)
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        walletAlertPayPasswordInput.setPassword("");
                    }
                });
    }
}
