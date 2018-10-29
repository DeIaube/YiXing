package arouter.dawn.zju.edu.module_account.ui.login;

import android.text.TextUtils;

import arouter.dawn.zju.edu.module_account.R;
import baselib.App;
import baselib.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(String account, String password) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            mView.showMessage(App.getContext().getString(R.string.login_account_password_empty));
            mView.loginFailed();
        }
    }

    @Override
    public void loginByQQ() {

    }

    @Override
    public void loginByWeibo() {

    }

    @Override
    public void loginByWechat() {

    }
}
