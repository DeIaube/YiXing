package arouter.dawn.zju.edu.module_account.ui.reset_password;

import baselib.base.BaseContract;

public interface ResetPasswordContract {

    interface View extends BaseContract.BaseView {
        void updateCoundDown(int count);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void verificationCode(final String phoneNumber, final String code);
        void getCode(String phoneNumber);
    }

}
