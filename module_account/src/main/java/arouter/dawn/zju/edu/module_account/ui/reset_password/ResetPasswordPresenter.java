package arouter.dawn.zju.edu.module_account.ui.set_password;

import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

public class SetPasswordPresenter extends BasePresenter<SetPasswordContract.View> implements SetPasswordContract.Presenter {

    String TAG = "SetPasswordPresenter";

    @Override
    public void resetPassword(String phoneNumber, String password, String repassword) {
        if (!checkPasswordCorrect(password, repassword)) {
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

    /**
     * 验证用户传入的密码是否合法
     * @param password 密码
     * @param repassword 确认密码
     * @return 合法性
     */
    private boolean checkPasswordCorrect(String password, String repassword) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            mView.showMessage(App.getContext().
                    getString(R.string.set_password_password_not_null));
            return false;
        }
        if (!password.equals(repassword)) {
            mView.showMessage(App.getContext().
                    getString(R.string.set_password_password_diff));
            return false;
        }
        if (password.length() < 8) {
            mView.showMessage(App.getContext().
                    getString(R.string.set_password_password_too_short));
            return false;
        }
        if (password.length() > 32) {
            mView.showMessage(App.getContext().
                    getString(R.string.set_password_password_too_long));
            return false;
        }
        if (!checkPasswordComplexity(password)) {
            mView.showMessage(App.getContext().
                    getString(R.string.set_password_password_too_simple));
            return false;
        }
        return true;
    }

    /**
     * 检验密码复杂度
     * @param password 密码
     * @return 如果含有数字、字母或者符号两种以上则返回true
     */
    private boolean checkPasswordComplexity(String password) {
        boolean hasNumber = false;
        boolean hasChar = false;
        boolean hasSymbol = false;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (Character.isLowerCase(ch) || Character.isUpperCase(ch)) {
                hasChar = true;
            } else {
                hasSymbol = true;
            }
            if ((hasNumber && hasChar) || (hasNumber && hasSymbol) || (hasChar && hasSymbol)) {
                return true;
            }
        }
        return false;
    }

}
