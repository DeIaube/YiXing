package arouter.dawn.zju.edu.module_account.ui.reset_password;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户忘记(重置)密码页面
 */
@Route(path = Constants.AROUTER_ACCOUNT_RESET_PASSWORD)
public class ResetPasswordActivity extends BaseActivity<ResetPasswordContract.Presenter> implements ResetPasswordContract.View {

    EditText phoneInputEt;
    EditText verificationCodeEt;
    EditText passwordInputEt;
    EditText repasswordInputEt;
    Button getCodeBtn;

    @Override
    protected void initView() {
        phoneInputEt = findViewById(R.id.reset_password_phone_number);
        passwordInputEt = findViewById(R.id.reset_password_password);
        verificationCodeEt = findViewById(R.id.reset_password_verification_code);
        repasswordInputEt = findViewById(R.id.reset_password_repassword);
        getCodeBtn = findViewById(R.id.reset_password_get_verification_code);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    public void onViewClicked(View v) {
        int id = v.getId();
        if (id == R.id.reset_password_get_verification_code) {
            mPresenter.getCode(phoneInputEt.getText().toString());
        } else if (id == R.id.reset_password_submit) {
            mPresenter.verificationCode(phoneInputEt.getText().toString(),
                    verificationCodeEt.getText().toString(),
                    passwordInputEt.getText().toString(),
                    repasswordInputEt.getText().toString());
        }
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ResetPasswordPresenter();
    }

    @Override
    public void updateCoundDown(int count) {
        if (count == 0) {
            getCodeBtn.setClickable(true);
            getCodeBtn.setBackgroundResource(R.color.colorPrimary);
            getCodeBtn.setText(getText(R.string.register_get_verification_code));
            return;
        }
        getCodeBtn.setClickable(false);
        getCodeBtn.setBackgroundResource(R.color.colorPrimaryDark);
        getCodeBtn.setText(String.format("%ds", count));
    }
}
