package arouter.dawn.zju.edu.module_account.ui.set_password;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.util.VerificationUtil;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class SetPasswordPresenter extends BasePresenter<SetPasswordContract.View> implements SetPasswordContract.Presenter {

    String TAG = "SetPasswordPresenter";

    @Override
    public void resetPassword(String phoneNumber, String password, String repassword) {
        if (!VerificationUtil.checkPasswordCorrect(mView, password, repassword)) {
            return;
        }
        User user = new User();
        user.setUsername(phoneNumber);
        user.setPassword(password);
        user.setMobilePhoneNumber(phoneNumber);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    LogUtil.i(TAG, "signUp");
                    mView.showMessage(App.getContext().getString(R.string.set_password_sign_success));
                    mView.setPasswordSuccess();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }
}
