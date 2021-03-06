package arouter.dawn.zju.edu.module_account.ui.set_pay_password;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ethanco.lib.PasswordInput;

import java.util.concurrent.TimeUnit;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户设置支付密码页面
 */
@Route(path = RouteConstants.AROUTER_ACCOUNT_SET_PAY_PASSWORD)
public class AccountSetPayPasswordActivity extends BaseActivity<AccountSetPayPasswordContract.Presenter> implements AccountSetPayPasswordContract.View, PasswordInput.TextLenChangeListener {

    PasswordInput setPayPasswordInput;
    TextView setPayPasswordTipTv;

    @Autowired(name = RouteConstants.ACCOUNT_PHONE_NUMBER)
    String phoneNumber;

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
                // 设置支付密码成功 进入设置密码页面
                ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_SET_PASSWORD).
                        withString(RouteConstants.ACCOUNT_PAY_PASSWORD, mPayPassword).
                        withString(RouteConstants.ACCOUNT_PHONE_NUMBER, phoneNumber).navigation();
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
