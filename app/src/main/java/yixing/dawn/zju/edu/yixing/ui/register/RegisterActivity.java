package yixing.dawn.zju.edu.yixing.ui.register;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import yixing.dawn.zju.edu.yixing.R;
import yixing.dawn.zju.edu.yixing.base.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View{

    @BindView(R.id.register_verification_code)
    EditText verificationCodeEt;
    @BindView(R.id.register_phone_number)
    EditText phoneNumberEt;
    @BindView(R.id.register_get_verification_code)
    Button getCodeBtn;

    @Override
    protected void initView() {

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

    @OnClick({R.id.register_submit, R.id.register_get_verification_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_submit:
                mPresenter.register(phoneNumberEt.getText().toString(), verificationCodeEt.getText().toString());
                break;
            case R.id.register_get_verification_code:
                mPresenter.getCode();
                break;
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
}
