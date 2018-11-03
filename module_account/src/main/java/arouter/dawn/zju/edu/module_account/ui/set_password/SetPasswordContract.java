package arouter.dawn.zju.edu.module_account.ui.set_password;

import baselib.base.BaseContract;

public interface SetPasswordContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void resetPassword(final String phoneNumber, final String password, final String repassword);
    }

}
