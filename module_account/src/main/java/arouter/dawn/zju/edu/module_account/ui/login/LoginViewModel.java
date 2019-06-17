package arouter.dawn.zju.edu.module_account.ui.login;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.LogInCallback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.config.Constants;
import arouter.dawn.zju.edu.module_account.util.VerificationUtil;
import baselib.base2.BaseViewModel;
import baselib.constants.RouteConstants;
import baselib.util.LogUtil;
import baselib.util.SPUtil;

public class LoginViewModel extends BaseViewModel<LoginActivity, LoginRepository> {

    private static final String TAG = "LoginViewModel";

    public MutableLiveData<String> usernameData = new MutableLiveData<>();
    public MutableLiveData<String> passwordData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application, LoginActivity view, LoginRepository repository) {
        super(application, view, repository);
    }

    public void login() {
        String phoneNumber = usernameData.getValue();
        String password = passwordData.getValue();
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)) {
            view.makeToast(getApplication().getString(R.string.login_account_password_empty));
            return;
        }
        if (VerificationUtil.checkPhoneNumber(phoneNumber)) {
            view.makeToast(getApplication().getString(R.string.login_invalid_phone_number));
            return;
        }
        view.showLoading("");
        User.loginByMobilePhoneNumberInBackground(phoneNumber, password, new LogInCallback<User>() {
            @Override
            public void done(User avUser, AVException e) {
                view.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "loginByMobilePhone");
                    view.makeToast(getApplication().getString(R.string.login_success));
                    // 保存用户信息
                    SPUtil.put(Constants.LOGIN_NUMBER, phoneNumber);
                    SPUtil.put(Constants.LOGIN_PASSWORD, password);
                    // 跳转
                    ARouter.getInstance().build(RouteConstants.AROUTER_APP_MAIN).navigation();
                    view.finish();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    view.makeToast(e.getLocalizedMessage());
                }
            }
        }, User.class);
    }

    public void register() {
        ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_REGISTER)
                .navigation();
    }

    public void forgetPassword() {
        ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_RESET_PASSWORD).navigation();
    }

    public void loadData() {
        usernameData.setValue(SPUtil.getString(arouter.dawn.zju.edu.module_account.config.Constants.LOGIN_NUMBER, ""));
        passwordData.setValue(SPUtil.getString(arouter.dawn.zju.edu.module_account.config.Constants.LOGIN_PASSWORD, ""));
    }

}
