package arouter.dawn.zju.edu.module_account.register;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constant;

@Route(path = Constant.AROUTER_REGISTER)
public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View{

    EditText verificationCodeEt;
    EditText phoneNumberEt;
    Button getCodeBtn;

    private String mPhoneNumber;

    @Override
    protected void initView() {
        verificationCodeEt = findViewById(R.id.register_verification_code);
        phoneNumberEt = findViewById(R.id.register_phone_number);
        getCodeBtn = findViewById(R.id.register_get_verification_code);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new RegisterPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.register_submit) {
            mPhoneNumber = phoneNumberEt.getText().toString();
            mPresenter.verificationCode(mPhoneNumber, verificationCodeEt.getText().toString());
        } else if (id == R.id.register_get_verification_code) {
            mPresenter.getCode();
        }
    }

    @SuppressLint("DefaultLocale")
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

    /**
     * 验证码验证回调
     * @param result 验证码验证结果
     */
    @Override
    public void verificationCodeCallback(boolean result) {
        if (result) {
            // 验证码验证成功 进入设置密码页面
            ARouter.getInstance().build(Constant.AROUTER_RESET_PASSWORD).
                    withString(Constant.REGISTER_PHONE_NUMBER, mPhoneNumber).navigation();
            return;
        }
        // 验证失败
    }
}
