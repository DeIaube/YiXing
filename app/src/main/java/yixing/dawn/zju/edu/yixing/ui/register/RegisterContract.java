package yixing.dawn.zju.edu.yixing.ui.register;

import yixing.dawn.zju.edu.yixing.base.BaseContract;

public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void updateCoundDown(int count);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void register(final String phoneNumber, final String code);
        void getCode();
    }

}
