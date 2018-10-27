package arouter.dawn.zju.edu.module_account.reset_password;

import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.register.RegisterPresenter;
import baselib.base.BaseActivity;
import baselib.config.Constant;

@Route(path = Constant.AROUTER_RESET_PASSWORD)
public class ResetPasswordActivity extends BaseActivity<RegisterPresenter> implements ResetPasswordContract.View {

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

    }

    public void onViewClicked(View view) {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
