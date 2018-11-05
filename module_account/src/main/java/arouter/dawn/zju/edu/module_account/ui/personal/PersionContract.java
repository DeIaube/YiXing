package arouter.dawn.zju.edu.module_account.ui.personal;

import java.util.Date;

import baselib.base.BaseContract;

public interface PersionContract {

    interface View extends BaseContract.BaseView {
        void refreshUserPortrait(String url);
        void refreshUserBirth(Date date);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateUserPortrait(String path);
        void updateUserBirth(int year, int month, int day);
    }

}
