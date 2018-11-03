package arouter.dawn.zju.edu.module_account.ui.reset_password;

import baselib.base.BaseContract;

public interface ResetPasswordContract {

    interface View extends BaseContract.BaseView {
        void updateCoundDown(int count);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void verificationCode(String phoneNumber, String code, String password, String repassword);
        void getCode(String phoneNumber);
    }

}
