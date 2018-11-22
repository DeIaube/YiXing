package arouter.dawn.zju.edu.module_account.ui.set_password;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface SetPasswordContract {

    interface View extends BaseContract.BaseView {
        void setPasswordSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void resetPassword(final String phoneNumber, final String password, final String repassword);
    }

}
