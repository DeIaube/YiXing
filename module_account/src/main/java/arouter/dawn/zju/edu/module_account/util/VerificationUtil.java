package arouter.dawn.zju.edu.module_account.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arouter.dawn.zju.edu.module_account.R;
import baselib.App;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 验证密码以及验证码的正确性
 */
public class VerificationUtil {

    /**
     * 通过正则表达式验证手机号合法性
     * @param mobiles 手机号
     * @return 合法性
     */
    public static boolean checkPhoneNumber(String mobiles){
    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    Matcher m = p.matcher(mobiles);
    return !m.matches();
    }

    /**
     * 验证用户传入的密码是否合法
     * @param password 密码
     * @param repassword 确认密码
     * @return 合法性
     */
    public static boolean checkPasswordCorrect(BaseContract.BaseView view, String password, String repassword) {
        if (view == null) {
            return false;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            view.showMessage(App.getAppalication().
                    getString(R.string.set_password_password_not_null));
            return false;
        }
        if (!password.equals(repassword)) {
            view.showMessage(App.getAppalication().
                    getString(R.string.set_password_password_diff));
            return false;
        }
        if (password.length() < 8) {
            view.showMessage(App.getAppalication().
                    getString(R.string.set_password_password_too_short));
            return false;
        }
        if (password.length() > 32) {
            view.showMessage(App.getAppalication().
                    getString(R.string.set_password_password_too_long));
            return false;
        }
        if (!checkPasswordComplexity(password)) {
            view.showMessage(App.getAppalication().
                    getString(R.string.set_password_password_too_simple));
            return false;
        }
        return true;
    }

    public static String checkPasswordCorrect(String password, String repassword) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            return App.getAppalication().getString(R.string.set_password_password_not_null);
        }
        if (!password.equals(repassword)) {
            return App.getAppalication().getString(R.string.set_password_password_diff);
        }
        if (password.length() < 8) {
            return App.getAppalication().getString(R.string.set_password_password_too_short);
        }
        if (password.length() > 32) {
            return App.getAppalication().getString(R.string.set_password_password_too_long);
        }
        if (!checkPasswordComplexity(password)) {
            return App.getAppalication().getString(R.string.set_password_password_too_simple);
        }
        return "";
    }

    public static boolean checkCodeCorrect(String code) {
        if (code == null || code.length() != 6) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(code).matches();
    }

    /**
     * 检验密码复杂度
     * @param password 密码
     * @return 如果含有数字、字母或者符号两种以上则返回true
     */
    private static boolean checkPasswordComplexity(String password) {
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
