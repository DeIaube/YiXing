package arouter.dawn.zju.edu.module_account.ui.personal;

import baselib.base.BaseContract;

public interface PersionContract {

    interface View extends BaseContract.BaseView {
        void refreshUserPortrait(String url);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateUserPortrait(String path);
    }

}
