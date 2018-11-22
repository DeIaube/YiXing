package arouter.dawn.zju.edu.module_account.ui.set_password;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.util.VerificationUtil;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class SetPasswordPresenter extends BasePresenter<SetPasswordContract.View> implements SetPasswordContract.Presenter {

    String TAG = "SetPasswordPresenter";

    @Override
    public void setPassword(String phoneNumber, String payPassword, String password, String repassword) {
        if (!VerificationUtil.checkPasswordCorrect(mView, password, repassword)) {
            return;
        }
        User user = new User();
        user.setUsername(phoneNumber);
        user.setPassword(password);
        user.setBirth(new Date());
        user.setPayPassword(payPassword);
        user.setMobilePhoneNumber(phoneNumber);
        mView.showLoading();
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
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
