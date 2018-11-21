package arouter.dawn.zju.edu.module_account.ui.set_pay_password;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ACCOUNT_SET_PAY_PASSWORD)
public class AccountSetPayPasswordActivity extends BaseActivity<AccountSetPayPasswordContract.Presenter> implements AccountSetPayPasswordContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_set_pay_password;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new AccountSetPayPasswordPresenter();
    }

}
