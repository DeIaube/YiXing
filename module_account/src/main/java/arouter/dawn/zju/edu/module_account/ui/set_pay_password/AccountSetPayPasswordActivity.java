package arouter.dawn.zju.edu.module_account.ui.set_pay_password;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethanco.lib.PasswordInput;

import java.util.concurrent.TimeUnit;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

@Route(path = Constants.AROUTER_ACCOUNT_SET_PAY_PASSWORD)
public class AccountSetPayPasswordActivity extends BaseActivity<AccountSetPayPasswordContract.Presenter> implements AccountSetPayPasswordContract.View, PasswordInput.TextLenChangeListener {

    PasswordInput setPayPasswordInput;
    TextView setPayPasswordTipTv;

    private String mPayPassword;

    @Override
    protected void initView() {
        setPayPasswordInput = findViewById(R.id.set_pay_password_input);
        setPayPasswordTipTv = findViewById(R.id.set_pay_password_tip);

        setPayPasswordTipTv.setText(getString(R.string.set_pay_password_input_pay_password));

        setPayPasswordInput.setTextLenChangeListener(this);
        inputGetFocusable();
    }

    private void inputGetFocusable() {
        setPayPasswordInput.setFocusable(true);
        setPayPasswordInput.setFocusableInTouchMode(true);
        setPayPasswordInput.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(setPayPasswordInput,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_set_pay_password;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new AccountSetPayPasswordPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onTextLenChange(CharSequence charSequence, int i) {
        if (i != 6) {
            return;
        }
        final String password = charSequence.toString();
        if (TextUtils.isEmpty(mPayPassword)) {
            setPayPasswordTipTv.setText(getString(R.string.set_pay_password_confirm_pay_password));
            mPayPassword = password;
            resetPasswordLayout();
        } else {
            if (password.equals(mPayPassword)) {
                // ojbk
                showMessage("ojbk");
            } else {
                setPayPasswordTipTv.setText(getString(R.string.set_pay_password_input_pay_password));
                showMessage(getString(R.string.set_pay_password_pay_password_diff));
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
                        setPayPasswordInput.setPassword("");
                    }
                });
    }
}
