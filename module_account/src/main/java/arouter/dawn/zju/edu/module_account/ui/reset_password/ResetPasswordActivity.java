package arouter.dawn.zju.edu.module_account.ui.reset_password;

import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ACCOUNT_RESET_PASSWORD)
public class ResetPasswordActivity extends BaseActivity<ResetPasswordPresenter> implements ResetPasswordContract.View {

    EditText passwordEt;
    EditText rePasswordEt;

    @Autowired
    String phoneNumber;

    @Override
    protected void initView() {
        passwordEt = findViewById(R.id.reset_password_password_et);
        rePasswordEt = findViewById(R.id.reset_password_repassword_et);
        showMessage(phoneNumber);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ResetPasswordPresenter();
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.reset_password_submit) {
            mPresenter.resetPassword(phoneNumber,
                    passwordEt.getText().toString(), rePasswordEt.getText().toString());
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
