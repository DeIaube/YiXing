package arouter.dawn.zju.edu.module_account.reset_password;

import android.text.TextUtils;

import arouter.dawn.zju.edu.module_account.R;
import baselib.App;
import baselib.base.BasePresenter;

public class ResetPasswordPresenter extends BasePresenter<ResetPasswordContract.View> implements ResetPasswordContract.Presenter {

    @Override
    public void resetPassword(String phoneNumber, String password, String repassword) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            mView.showMessage(App.getContext().
                    getString(R.string.reset_password_password_not_null));
            return;
        }
        if (!password.equals(repassword)) {
            mView.showMessage(App.getContext().
                    getString(R.string.reset_password_password_diff));
            return;
        }
        if (password.length() < 8) {
            mView.showMessage(App.getContext().
                    getString(R.string.reset_password_password_too_short));
            return;
        }
        if (password.length() > 32) {
            mView.showMessage(App.getContext().
                    getString(R.string.reset_password_password_too_long));
            return;
        }
        if (!checkPasswordComplexity(password)) {
            mView.showMessage(App.getContext().
                    getString(R.string.reset_password_password_too_simple));
        }
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
