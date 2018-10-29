package arouter.dawn.zju.edu.module_account.ui.register;

import baselib.base.BaseContract;

public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void updateCoundDown(int count);
        void verificationCodeCallback(boolean result);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void verificationCode(final String phoneNumber, final String code);
        void getCode();
    }

}
