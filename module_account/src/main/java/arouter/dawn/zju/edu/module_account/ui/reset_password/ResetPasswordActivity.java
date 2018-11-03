package arouter.dawn.zju.edu.module_account.ui.reset_password;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ACCOUNT_RESET_PASSWORD)
public class ResetPasswordActivity extends BaseActivity<ResetPasswordContract.Presenter> implements ResetPasswordContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ResetPasswordPresenter();
    }
}
