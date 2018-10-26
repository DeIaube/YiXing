package yixing.dawn.zju.edu.yixing.ui.login;

import android.text.TextUtils;

import yixing.dawn.zju.edu.yixing.R;
import yixing.dawn.zju.edu.yixing.base.App;
import yixing.dawn.zju.edu.yixing.base.BasePresenter;

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
