package arouter.dawn.zju.edu.module_account.ui.login;

import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.LogInCallback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.util.VerificationUtil;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    @Override
    public void login(String phoneNumber, String password) {
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)) {
            mView.showMessage(App.getContext().getString(R.string.login_account_password_empty));
            return;
        }
        if (VerificationUtil.checkPhoneNumber(phoneNumber)) {
            mView.showMessage(App.getContext().getString(R.string.login_invalid_phone_number));
            return;
        }
        mView.showLoading();
        User.loginByMobilePhoneNumberInBackground(phoneNumber, password, new LogInCallback<User>() {
            @Override
            public void done(User avUser, AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "loginByMobilePhone");
                    mView.showMessage(App.getContext().getString(R.string.login_success));
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
                mView.loginRsult(e == null);
            }
        }, User.class);
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
