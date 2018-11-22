package arouter.dawn.zju.edu.module_account.ui.register;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void updateCoundDown(int count);
        void verificationSuccessCallback();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void verificationCode(final String phoneNumber, final String code);
        void getCode(String phoneNumber);
    }

}
