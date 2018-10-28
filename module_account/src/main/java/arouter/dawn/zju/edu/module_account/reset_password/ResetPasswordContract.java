package arouter.dawn.zju.edu.module_account.reset_password;

import baselib.base.BaseContract;

public interface ResetPasswordContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void resetPassword(final String phoneNumber, final String password, final String repassword);
    }

}