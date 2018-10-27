package arouter.dawn.zju.edu.module_account.register;

import baselib.base.BaseContract;

public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void updateCoundDown(int count);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void register(final String phoneNumber, final String code);
        void getCode();
    }

}
